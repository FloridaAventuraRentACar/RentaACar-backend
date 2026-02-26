package backend.car_rental.services.commons;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import backend.car_rental.services.commons.interfaces.ICalculateRentalDaysService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CalculateRentalDaysService implements ICalculateRentalDaysService {

    @Override
    public long calculateRentalDays(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        long hours = Duration.between(startDateTime, endDateTime).toHours();

        long days = hours / 24;

        if (hours % 24 > 3) {
            days++;
        }

        return days;
    }
}
