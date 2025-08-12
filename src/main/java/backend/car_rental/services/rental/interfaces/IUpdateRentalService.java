package backend.car_rental.services.rental.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import backend.car_rental.dto.rental.UpdateRentalDto;

public interface IUpdateRentalService {
    
    ResponseEntity<?> update(UpdateRentalDto rentalDto, BindingResult result , Long id);

}
