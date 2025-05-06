package backend.car_rental.services.car;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.entities.Car;
import backend.car_rental.errors.Errors;
import backend.car_rental.mapper.CarMapper;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.services.car.interfaces.IFindCarService;

@Service
public class FindCarService implements IFindCarService{
    
    @Autowired
    private ICarRepository carRepository;

    @Override
    public ResponseEntity<List<ResponseCarDto>> findAll() {
        return ResponseEntity.ok(
            CarMapper.toDtoList( (List<Car>) carRepository.findAll() )
        );

    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            return Errors.returnError(
                "id",
                "Car not found",
                404
            );
        }
        return ResponseEntity.ok(CarMapper.toDto(optionalCar.get()));
    }

    @Override
    public ResponseEntity<List<ResponseCarDto>> getAvailableCars(LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        List<Car> cars = carRepository.getAvailableCars(startDateTime, endDateTime);
        return ResponseEntity.ok(CarMapper.toDtoList(cars));
    }
}
