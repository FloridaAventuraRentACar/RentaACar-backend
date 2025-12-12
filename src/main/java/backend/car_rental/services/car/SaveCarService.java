package backend.car_rental.services.car;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import backend.car_rental.dto.car.CreateCarDto;
import backend.car_rental.entities.Car;
import backend.car_rental.errors.Errors;
import backend.car_rental.mapper.CarMapper;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.services.car.interfaces.ISaveCarService;

@Service
public class SaveCarService implements ISaveCarService{

    @Autowired
    private ICarRepository carRepository;

    @Override
    public ResponseEntity<?> save(CreateCarDto carDto , BindingResult result) {

        if (result.hasErrors()) {
            return Errors.returnSintaxErrors(result);
        }
        
        Car carSaved = carRepository.save(CarMapper.toEntity(carDto));
        return ResponseEntity.ok(CarMapper.toDto(carSaved));
    }
    

}
