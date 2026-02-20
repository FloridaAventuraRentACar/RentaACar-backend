package backend.car_rental.services.rental;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.dto.rental.ResponseRentalDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.Client;
import backend.car_rental.entities.PriceAdjustment;
import backend.car_rental.entities.Rental;
import backend.car_rental.mapper.ClientMapper;
import backend.car_rental.mapper.RentalMapper;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.car.interfaces.ICarFindByIdService;
import backend.car_rental.services.commons.interfaces.ICalculateRentalDaysService;
import backend.car_rental.services.notifications.WhatsAppService;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentCalculateAveragePriceService;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentFindOverlappingPeriodService;
import backend.car_rental.services.rental.interfaces.IRentalCheckAvaibilityService;
import backend.car_rental.services.rental.interfaces.ISaveRentalService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaveRentalService implements ISaveRentalService {

    private IRentalRepository rentalRepository;
    private IRentalCheckAvaibilityService rentalCheckAvaibilityService;
    private ICarFindByIdService carFindByIdService;
    private WhatsAppService whatsappService;
    private final IPriceAdjustmentFindOverlappingPeriodService priceAdjustmentFindOverlappingPeriodService;
    private final ICalculateRentalDaysService calculateRentalDaysService;
    private final IPriceAdjustmentCalculateAveragePriceService priceAdjustmentCalculateAveragePriceService;

    @Override
    @Transactional
    public ResponseRentalDto save(CreateRentalDto rentalDto) {
 
        Car car = carFindByIdService.findCarById(rentalDto.getCarId());

        rentalCheckAvaibilityService.isAvailable(rentalDto.getCarId() , rentalDto.getStart(), rentalDto.getEnd());
        
        List<Client> clientsToSave = ClientMapper.toEntityList(rentalDto.getClients());

        Rental rentalToSave = RentalMapper.toEntity(rentalDto, car , clientsToSave);

        rentalToSave.calculateDaysRented();

        //Si totalPrice es null se calcula desde el back, sino se setea lo que llego del front
        if (rentalDto.getTotalPrice() == null) {
            
            //Chequeo si hay un ajuste de precios en las fechas de alquiler
            List<PriceAdjustment> priceAdjustments = priceAdjustmentFindOverlappingPeriodService.findOverlappingsPeriod(rentalDto.getStart().toLocalDate(), rentalDto.getEnd().toLocalDate());
            if (!priceAdjustments.isEmpty()) {
                //Si hay ajuste de precios, calculo el precio promedio por dia del auto
                long days = calculateRentalDaysService.calculateRentalDays(rentalDto.getStart(), rentalDto.getEnd());
                double pricePerDay = priceAdjustmentCalculateAveragePriceService.calculateAverageDailyPrice(car, rentalDto.getStart(), rentalDto.getEnd(), priceAdjustments, days);

                rentalToSave.calculateTotalPrice(pricePerDay);
            } else {
                rentalToSave.calculateTotalPrice();
            }

        }else{
            rentalToSave.setTotalPrice(rentalDto.getTotalPrice());
        }

        Rental savedRental = rentalRepository.save(rentalToSave);

        //Envio notificacion al admin de manera asincrona
        whatsappService.sendAdminNotification("Felipe Del Zoppo", "Volkswagen Tiguan", "2 de febrero de 2026", "10 de febrero de 2026", 3);

        return RentalMapper.toDto(savedRental);
    }
    
}
