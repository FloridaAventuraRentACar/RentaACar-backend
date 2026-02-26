package backend.car_rental.services.car;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.PriceAdjustment;
import backend.car_rental.exceptions.ConflictException;
import backend.car_rental.mapper.CarMapper;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.services.car.interfaces.ICarGetAvailableService;
import backend.car_rental.services.commons.interfaces.ICalculateRentalDaysService;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentCalculateAveragePriceService;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentFindOverlappingPeriodService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarGetAvailableService implements ICarGetAvailableService {

    private final ICarRepository carRepository;
    private final IPriceAdjustmentFindOverlappingPeriodService priceAdjustmentFindOverlappingPeriodService;
    private final IPriceAdjustmentCalculateAveragePriceService priceAdjustmentCalculateAveragePriceService;
    private final ICalculateRentalDaysService calculateRentalDaysService;
    
    @Override
    public List<ResponseCarDto> getAvailableCars(LocalDateTime startDateTime,
            LocalDateTime endDateTime) {

        long days = calculateRentalDaysService.calculateRentalDays(startDateTime, endDateTime);

        if (days < 3) {
            throw new ConflictException("El periodo minimo de alquiler es de 3 dias");
        }

        List<Car> cars = carRepository.getAvailableCars(startDateTime.minusHours(2), endDateTime.plusHours(2)); 
        //Debe haber una ventana de 2 horas entre alquileres

        //Elimino de la lista los autos con el mismo nombre
        List<Car> result = cars.stream()
            .collect(Collectors.toMap(
                Car::getName,          // clave: name
                car -> car,            // valor: el objeto Car
                (car1, car2) ->        // conflicto: mismo name
                car1.getId() < car2.getId() ? car1 : car2
            ))
            .values()
            .stream()
            .sorted(Comparator.comparing(Car::getId))
            .toList();
        
        List<PriceAdjustment> priceAdjustments = priceAdjustmentFindOverlappingPeriodService.findOverlappingsPeriod(startDateTime.toLocalDate(), endDateTime.toLocalDate());

        if (!priceAdjustments.isEmpty()) {
            result.stream().forEach(car -> {
                double pricePerDay = priceAdjustmentCalculateAveragePriceService.calculateAverageDailyPrice(car, startDateTime, endDateTime, priceAdjustments, days);
                pricePerDay = Math.round(pricePerDay * 100.0) / 100.0; //redondeo a dos decimales
                car.setPricePerDay(pricePerDay);
            });
        }

        return CarMapper.toDtoList(result);
    }
}
