package backend.car_rental.services.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.car_rental.entities.Car;
import backend.car_rental.errors.Errors;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.services.car.interfaces.IDeleteCarService;
import java.util.Optional;

@Service
public class DeleteCarService implements IDeleteCarService{

    @Autowired
    private ICarRepository carRepository;

    @Override
    public ResponseEntity<?> delete(Long id) {

        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            return Errors.returnError(
                "id", 
                "Car not found", 
                404
            );
        }
        //TO DO: Agregar la validacion de que no se puede eliminar un auto que este en algun alquiler
        carRepository.delete(optionalCar.get());

        return ResponseEntity.noContent().build();
    }    
}
