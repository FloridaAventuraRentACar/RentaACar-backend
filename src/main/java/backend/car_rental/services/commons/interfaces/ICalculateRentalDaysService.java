package backend.car_rental.services.commons.interfaces;

import java.time.LocalDateTime;

public interface ICalculateRentalDaysService {
    
    long calculateRentalDays(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
