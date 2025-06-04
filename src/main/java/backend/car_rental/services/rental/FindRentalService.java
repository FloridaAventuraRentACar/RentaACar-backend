package backend.car_rental.services.rental;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.car_rental.entities.Rental;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.rental.interfaces.IFindRentalService;

@Service
public class FindRentalService implements IFindRentalService{

    @Autowired
    private IRentalRepository rentalRepository;

    @Override
    public ResponseEntity<?> findCurrentRentals() {
        
        List<Rental> rentals = rentalRepository.findRentalsAfterDate(LocalDateTime.now().minusDays(1)); 
        //Obtengo el dia de ayer en vez del dia de hoy por si existe una alquiler que finaliza hoy

        return ResponseEntity.ok(rentals);
    }
    
}
