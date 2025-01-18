package backend.car_rental.services.car.interfaces;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.car_rental.dto.car.ResponseCarDto;

import java.util.List;


public interface IFindCarService {

    ResponseEntity<List<ResponseCarDto>> findAll();

    ResponseEntity<?> findById(Long id);
    
}
