package backend.car_rental.services.rental;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import backend.car_rental.dto.rental.UpdateRentalDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.Client;
import backend.car_rental.entities.Rental;
import backend.car_rental.errors.Errors;
import backend.car_rental.mapper.ClientMapper;
import backend.car_rental.mapper.RentalMapper;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.client.interfaces.IClientExistsListByIdService;
import backend.car_rental.services.rental.interfaces.IRentalCheckAvaibilityService;
import backend.car_rental.services.rental.interfaces.IUpdateRentalService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateRentalService implements IUpdateRentalService {
    
    private final IRentalRepository rentalRepository;
    private final IRentalCheckAvaibilityService rentalCheckAvaibilityService;
    private final IClientExistsListByIdService clientExistsListByIdService;
    private final ICarRepository carRepository;

    @Override
    public ResponseEntity<?> update(UpdateRentalDto rentalDto, BindingResult result, Long id) {
        
        if (result.hasErrors()) {
            return Errors.returnSintaxErrors(result);
        }

        Optional<Car> optionalCar = carRepository.findById(rentalDto.getCarId());
        if (optionalCar.isEmpty()) {
            return Errors.returnError(
                "carId", 
                "Car not found", 
                404
            );
        }

        if (!rentalCheckAvaibilityService.isAvailableExceptId( id, rentalDto.getCarId() , rentalDto.getStart(), rentalDto.getEnd())) {
            return Errors.returnError(
                "CarId", 
                "Car is not available in that dates",
                409);
        }    

        if (!rentalRepository.existsById(id)) {
            return Errors.returnError(
                "id", 
                "Rental not found", 
                404
            );
        };

        if (!clientExistsListByIdService.existsAll(rentalDto.getClients())) {

            return Errors.returnError(                
            "clients", 
            "Clients not found", 
            404);
        }
        
        List<Client> clientsToSave = ClientMapper.toUpdateModelList(rentalDto.getClients());
        Rental rentalToSave = RentalMapper.toUpdateModel(id, rentalDto, optionalCar.get(), clientsToSave);

        return ResponseEntity.ok().body(RentalMapper.toDto(rentalRepository.save(rentalToSave)));
    }
    
}
