package backend.car_rental.services.rental.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.car_rental.dto.rental.ResponseRentalDto;

public interface IFindRentalService {
    ResponseEntity<?> findCurrentRentals();
    ResponseEntity<?> findRentalById(Long id);

    List<ResponseRentalDto> findAll();
}
