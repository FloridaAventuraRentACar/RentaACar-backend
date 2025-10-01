package backend.car_rental.services.car.interfaces;

import backend.car_rental.entities.Car;

public interface ICarFindByIdService {
    
    Car findCarById(Long id);
}
