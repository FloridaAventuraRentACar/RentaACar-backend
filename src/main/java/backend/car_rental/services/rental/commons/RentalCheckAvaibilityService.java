package backend.car_rental.services.rental.commons;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import backend.car_rental.exceptions.ConflictException;
import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.rental.interfaces.IRentalCheckAvaibilityService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalCheckAvaibilityService implements IRentalCheckAvaibilityService {

    private final IRentalRepository rentalRepository;

    @Override
    public void isAvailable(Long carId, LocalDateTime startDate, LocalDateTime endDate) {
        
        if (rentalRepository.existsByCarIdAndDates(startDate, endDate, carId)) {
            throw new ConflictException("Car with id " + carId + " is not available between " + startDate + " and " + endDate + " dates");
        };
    }

    @Override
    public void isAvailableExceptId( Long rentalId, Long carId, LocalDateTime startDate, LocalDateTime endDate) {
        
        if (rentalRepository.existsByCarIdAndDatesExceptId(startDate, endDate, carId , rentalId)) {
            throw new ConflictException("Car with id " + carId + " is not available between " + startDate + " and " + endDate + " dates");
        };
    }
    
    
}
