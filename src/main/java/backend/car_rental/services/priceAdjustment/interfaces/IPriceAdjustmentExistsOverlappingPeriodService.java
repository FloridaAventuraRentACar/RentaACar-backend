package backend.car_rental.services.priceAdjustment.interfaces;

import java.time.LocalDate;

public interface IPriceAdjustmentExistsOverlappingPeriodService {
    void existsOverlappingPeriod(LocalDate periodStart, LocalDate periodEnd);

    void existsOverlappingPeriodExceptFromId(Long id, LocalDate periodStart, LocalDate periodEnd);
}
