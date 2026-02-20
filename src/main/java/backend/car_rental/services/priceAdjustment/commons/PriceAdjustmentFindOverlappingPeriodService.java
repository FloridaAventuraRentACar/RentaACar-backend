package backend.car_rental.services.priceAdjustment.commons;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import backend.car_rental.entities.PriceAdjustment;
import backend.car_rental.repositories.IPriceAdjustmentRepository;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentFindOverlappingPeriodService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceAdjustmentFindOverlappingPeriodService implements IPriceAdjustmentFindOverlappingPeriodService {

    private final IPriceAdjustmentRepository priceAdjustmentRepository;

    @Override
    public List<PriceAdjustment> findOverlappingsPeriod(LocalDate periodStart, LocalDate periodEnd) {
        return priceAdjustmentRepository.findOverlappingsPeriod(periodStart, periodEnd);
    }
}
