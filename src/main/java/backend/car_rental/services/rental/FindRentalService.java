package backend.car_rental.services.rental;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.car_rental.dto.rental.CurrentRentalsResponseDto;
import backend.car_rental.dto.rental.ResponseRentalDto;
import backend.car_rental.entities.Rental;
import backend.car_rental.errors.Errors;
import backend.car_rental.mapper.RentalMapper;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.rental.interfaces.IFindRentalService;

@Service
public class FindRentalService implements IFindRentalService{

    @Autowired
    private IRentalRepository rentalRepository;

    @Override
    public ResponseEntity<List<CurrentRentalsResponseDto>> findCurrentRentals() {
        
        List<Rental> rentals = rentalRepository.findRentalsAfterDate(LocalDateTime.now().minusDays(1)); 
        //Obtengo el dia de ayer en vez del dia de hoy por si existe una alquiler que finaliza hoy

        return ResponseEntity.ok(RentalMapper.toCurrentRentalDtoList(rentals));
    }
    
    @Override
    public ResponseEntity<?> findRentalById(Long id) {

        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isEmpty()) {
            return Errors.returnError(
                "id", 
                "Rental not found", 
                404
            );
        }

        Rental rental = optionalRental.get();
        return ResponseEntity.ok(RentalMapper.toDto(rental));
    }

    @Override
    public List<ResponseRentalDto> findAll() {
        
        return RentalMapper.toDtoList((List<Rental>) rentalRepository.findAll());
    }

    
}
