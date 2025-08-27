package backend.car_rental.services.rental.interfaces;

import java.time.LocalDateTime;

public interface IRentalCheckAvaibilityService {
    
    void isAvailable(Long carId, LocalDateTime startDate, LocalDateTime endDate);

    void isAvailableExceptId(Long rentalId, Long carId, LocalDateTime startDate, LocalDateTime endDate);
}
