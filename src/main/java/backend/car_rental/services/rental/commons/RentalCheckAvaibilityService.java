package backend.car_rental.services.rental.commons;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import backend.car_rental.repositories.IRentalRepository;
import backend.car_rental.services.rental.interfaces.IRentalCheckAvaibilityService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RentalCheckAvaibilityService implements IRentalCheckAvaibilityService {

    private final IRentalRepository rentalRepository;

    @Override
    public boolean isAvailable(Long carId, LocalDateTime startDate, LocalDateTime endDate) {
        
        return !rentalRepository.existsByCarIdAndDates(startDate, endDate, carId);
    }

    @Override
    public boolean isAvailableExceptId( Long rentalId, Long carId, LocalDateTime startDate, LocalDateTime endDate) {
        
        return !rentalRepository.existsByCarIdAndDatesExceptId(startDate, endDate, carId , rentalId);
    }
    
    
}
