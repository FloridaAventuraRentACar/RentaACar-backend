package backend.car_rental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.car_rental.entities.Car;
import backend.car_rental.repositories.ICarRepository;

@Service
public class CarService implements ICarService{
    
    @Autowired
    private ICarRepository carRepository;
    @Override
    public List<Car> findAll() {
        return (List<Car>) carRepository.findAllActive();

    }

    @Override
    public List<Car> findAllDeleted() {
        return (List<Car>) carRepository.findAllDeleted();

    }

    @Override
    public Optional<Car> findById(Long id) {

        return carRepository.findActiveById(id);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void delete(Car car) { //Eliminado logico
        car.setDeleted(true);
        carRepository.save(car);
    }

    @Override
    public Optional<Car> findByPlate(String plate) {

        return carRepository.findByPlate(plate);
    }    
    
}
