package backend.car_rental.services.car.interfaces;


import org.springframework.http.ResponseEntity;

import backend.car_rental.dto.car.ResponseCarDto;

import java.util.List;


public interface IFindCarService {

    ResponseEntity<List<ResponseCarDto>> findAll();

    ResponseEntity<?> findById(Long id);
    
}
