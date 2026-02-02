package backend.car_rental.services.rental.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import backend.car_rental.dto.rental.CurrentRentalsResponseDto;
import backend.car_rental.dto.rental.ResponseRentalDto;

public interface IFindRentalService {
    ResponseEntity<List<CurrentRentalsResponseDto>> findCurrentRentals();
    ResponseEntity<?> findRentalById(Long id);

    List<ResponseRentalDto> findAll();
}
