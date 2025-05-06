package backend.car_rental.services.rental;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.Client;
import backend.car_rental.entities.Rental;
import backend.car_rental.errors.Errors;
import backend.car_rental.mapper.RentalMapper;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.repositories.IClientRepository;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.rental.interfaces.ISaveRentalService;

@Service
public class SaveRentalService implements ISaveRentalService {

    @Autowired
    private IRentalRepository rentalRepository;

    @Autowired
    private ICarRepository carRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Override
    public ResponseEntity<?> save(CreateRentalDto rentalDto, BindingResult result) {
        
        if (result.hasErrors()) {
            return Errors.returnSintaxErrors(result);
        }
        Optional<Car> optionalCar = carRepository.findById(rentalDto.getCarId());
        Optional<Client> optionalClient = clientRepository.findById(rentalDto.getClientId());
        if (optionalCar.isEmpty()) {
            return Errors.returnError(
                "carId", 
                "Car not found", 
                404
            );
        }
        if (optionalClient.isEmpty()) {
            return Errors.returnError(
                "clientId", 
                "Client not found", 
                404
            );
        }

        Rental rentalToSave = RentalMapper.toEntity(rentalDto, optionalCar.get() , optionalClient.get());

        return ResponseEntity.ok(RentalMapper.toDto(rentalRepository.save(rentalToSave)));
    }

    @Override
    public void delete(Rental rental) {
        // rental.setDeleted(true);
        // rentalRepository.save(rental);
    }
    
}
