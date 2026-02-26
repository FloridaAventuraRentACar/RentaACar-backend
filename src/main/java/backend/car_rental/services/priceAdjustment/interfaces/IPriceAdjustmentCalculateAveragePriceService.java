package backend.car_rental.services.priceAdjustment.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import backend.car_rental.entities.Car;
import backend.car_rental.entities.PriceAdjustment;

public interface IPriceAdjustmentCalculateAveragePriceService {
    
    double calculateAverageDailyPrice(Car car, LocalDateTime startDateTime, LocalDateTime endDateTime, List<PriceAdjustment> adjustments, long days);
}
