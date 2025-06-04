package backend.car_rental.services.rental.interfaces;

import org.springframework.http.ResponseEntity;

public interface IFindRentalService {
    ResponseEntity<?> findCurrentRentals();
}
