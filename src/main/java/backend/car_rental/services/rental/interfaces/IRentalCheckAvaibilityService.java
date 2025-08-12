package backend.car_rental.services.rental.interfaces;

import java.time.LocalDateTime;

public interface IRentalCheckAvaibilityService {
    
    boolean isAvailable(Long carId, LocalDateTime startDate, LocalDateTime endDate);

    boolean isAvailableExceptId(Long rentalId, Long carId, LocalDateTime startDate, LocalDateTime endDate);
}
