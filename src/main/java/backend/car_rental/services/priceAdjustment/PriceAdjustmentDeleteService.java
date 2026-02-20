package backend.car_rental.services.priceAdjustment;

import org.springframework.stereotype.Service;

import backend.car_rental.entities.PriceAdjustment;
import backend.car_rental.repositories.IPriceAdjustmentRepository;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentDeleteService;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentFindByIdService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceAdjustmentDeleteService implements IPriceAdjustmentDeleteService {

    private final IPriceAdjustmentFindByIdService priceAdjustmentFindByIdService;
    private final IPriceAdjustmentRepository priceAdjustmentRepository;

    @SuppressWarnings("null")
    @Override
    public void deletePriceAdjustment(Long id) {
    
        PriceAdjustment priceAdjustment = priceAdjustmentFindByIdService.findById(id);
        
        priceAdjustment.setActive(false);

        priceAdjustmentRepository.save(priceAdjustment);
    }
}
