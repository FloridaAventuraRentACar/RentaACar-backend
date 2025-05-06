package backend.car_rental.services.car.interfaces;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import backend.car_rental.dto.car.CreateCarDto;


@Service
public interface ISaveCarService {

    ResponseEntity<?> save(CreateCarDto carDto , BindingResult result);
    ResponseEntity<?> update(CreateCarDto carDto,BindingResult result ,Long id);
}
