package backend.car_rental.services;

import java.util.*;



import backend.car_rental.entities.Car;


public interface ICarService {

    List<Car> findAll();
    List<Car> findAllDeleted();

    Optional<Car> findById(Long id);
    Car save(Car car);
    void delete(Car car);

    Optional<Car> findByPlate(String plate);
}
