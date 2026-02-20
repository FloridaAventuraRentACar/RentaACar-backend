package backend.car_rental.services.priceAdjustment.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import backend.car_rental.entities.PriceAdjustment;

public interface IPriceAdjustmentFindOverlappingPeriodService {
    List<PriceAdjustment> findOverlappingsPeriod(LocalDate periodStart, LocalDate periodEnd);
}
