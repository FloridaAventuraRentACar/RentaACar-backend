package backend.car_rental.services.rental;

import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.dto.rental.ResponseRentalDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.Client;
import backend.car_rental.entities.Rental;
import backend.car_rental.mapper.ClientMapper;
import backend.car_rental.mapper.RentalMapper;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.car.interfaces.ICarFindByIdService;
import backend.car_rental.services.rental.interfaces.IRentalCheckAvaibilityService;
import backend.car_rental.services.rental.interfaces.ISaveRentalService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaveRentalService implements ISaveRentalService {

    private IRentalRepository rentalRepository;
    private IRentalCheckAvaibilityService rentalCheckAvaibilityService;
    private ICarFindByIdService carFindByIdService;

    @Override
    @Transactional
    public ResponseRentalDto save(CreateRentalDto rentalDto) {
 
        Car car = carFindByIdService.findCarById(rentalDto.getCarId());

        rentalCheckAvaibilityService.isAvailable(rentalDto.getCarId() , rentalDto.getStart(), rentalDto.getEnd());
        
        List<Client> clientsToSave = ClientMapper.toEntityList(rentalDto.getClients());

        Rental rentalToSave = RentalMapper.toEntity(rentalDto, car , clientsToSave);

        return RentalMapper.toDto(rentalRepository.save(rentalToSave));
    }
    
}
