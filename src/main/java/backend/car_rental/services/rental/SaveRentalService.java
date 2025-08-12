package backend.car_rental.services.rental;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.Client;
import backend.car_rental.entities.Rental;
import backend.car_rental.errors.Errors;
import backend.car_rental.mapper.ClientMapper;
import backend.car_rental.mapper.RentalMapper;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.rental.interfaces.IRentalCheckAvaibilityService;
import backend.car_rental.services.rental.interfaces.ISaveRentalService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaveRentalService implements ISaveRentalService {

    private IRentalRepository rentalRepository;
    private ICarRepository carRepository;
    private IRentalCheckAvaibilityService rentalCheckAvaibilityService;

    @Override
    @Transactional
    public ResponseEntity<?> save(CreateRentalDto rentalDto, BindingResult result) {
        
        if (result.hasErrors()) {
            return Errors.returnSintaxErrors(result);
        }
 
        if (!rentalCheckAvaibilityService.isAvailable(rentalDto.getCarId() , rentalDto.getStart(), rentalDto.getEnd())) {
            return Errors.returnError(
                "CarId", 
                "Car is not available in that dates",
                409);
        }

        
        Optional<Car> optionalCar = carRepository.findById(rentalDto.getCarId());
        if (optionalCar.isEmpty()) {
            return Errors.returnError(
                "carId", 
                "Car not found", 
                404
                );
        }
            
        List<Client> clientsToSave = ClientMapper.toEntityList(rentalDto.getClients());

        Rental rentalToSave = RentalMapper.toEntity(rentalDto, optionalCar.get() , clientsToSave);

        return ResponseEntity.ok(RentalMapper.toDto(rentalRepository.save(rentalToSave)));
    }

    @Override
    public void delete(Rental rental) {
        // rental.setDeleted(true);
        // rentalRepository.save(rental);
    }
    
}
