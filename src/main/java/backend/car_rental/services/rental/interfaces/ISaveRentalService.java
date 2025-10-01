package backend.car_rental.services.rental.interfaces;



import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.dto.rental.ResponseRentalDto;

public interface ISaveRentalService {

    ResponseRentalDto save(CreateRentalDto rentalDto);

}
