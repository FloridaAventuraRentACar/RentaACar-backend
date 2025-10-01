package backend.car_rental.services.rental.interfaces;


import backend.car_rental.dto.rental.ResponseRentalDto;
import backend.car_rental.dto.rental.UpdateRentalDto;

public interface IUpdateRentalService {
    
    ResponseRentalDto update(UpdateRentalDto rentalDto, Long id);

}
