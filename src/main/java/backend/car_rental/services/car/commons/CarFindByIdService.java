package backend.car_rental.services.car.commons;

import org.springframework.stereotype.Service;

import backend.car_rental.entities.Car;
import backend.car_rental.exceptions.NotFoundException;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.services.car.interfaces.ICarFindByIdService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarFindByIdService implements ICarFindByIdService{
    
    private final ICarRepository carRepository;

    @Override
    public Car findCarById(Long id) {
        return carRepository.findById(id).orElseThrow(
            () -> new NotFoundException("Car not found with id: " + id)
        );
    }

    
}
