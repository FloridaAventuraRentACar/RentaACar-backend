package backend.car_rental.services.rental.interfaces;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.entities.Rental;

public interface ISaveRentalService {

    ResponseEntity<?> save(CreateRentalDto rentalDto, BindingResult result);

    void delete(Rental rental);
}
