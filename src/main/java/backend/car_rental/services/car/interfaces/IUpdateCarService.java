package backend.car_rental.services.car.interfaces;

import backend.car_rental.dto.car.CreateCarDto;
import backend.car_rental.dto.car.ResponseCarDto;

public interface IUpdateCarService {
    
    ResponseCarDto updateCar(Long id, CreateCarDto carDto);
}
