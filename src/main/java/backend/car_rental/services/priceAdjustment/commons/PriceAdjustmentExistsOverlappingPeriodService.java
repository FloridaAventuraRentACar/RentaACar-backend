package backend.car_rental.services.priceAdjustment.commons;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import backend.car_rental.repositories.IPriceAdjustmentRepository;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentExistsOverlappingPeriodService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceAdjustmentExistsOverlappingPeriodService implements IPriceAdjustmentExistsOverlappingPeriodService {

    private final IPriceAdjustmentRepository priceAdjustmentRepository;

    @Override
    public void existsOverlappingPeriod(LocalDate periodStart, LocalDate periodEnd) {
        if(priceAdjustmentRepository.existsOverlappingPeriod(periodStart, periodEnd)) {
            throw new RuntimeException("The period overlaps with an existing period");
        }
    }
}
