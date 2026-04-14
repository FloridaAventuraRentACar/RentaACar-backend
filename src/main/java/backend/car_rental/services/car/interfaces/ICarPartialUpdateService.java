package backend.car_rental.services.car.interfaces;

import backend.car_rental.dto.car.PartialUpdateCarDto;
import backend.car_rental.dto.car.ResponseCarDto;

public interface ICarPartialUpdateService {
    
    ResponseCarDto partialUpdateCar(Long id, PartialUpdateCarDto carDto);
}
