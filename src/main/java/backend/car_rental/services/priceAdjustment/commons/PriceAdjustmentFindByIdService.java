package backend.car_rental.services.priceAdjustment.commons;

import org.springframework.stereotype.Service;

import backend.car_rental.entities.PriceAdjustment;
import backend.car_rental.exceptions.NotFoundException;
import backend.car_rental.repositories.IPriceAdjustmentRepository;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentFindByIdService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceAdjustmentFindByIdService implements IPriceAdjustmentFindByIdService {

    private final IPriceAdjustmentRepository priceAdjustmentRepository;

    @SuppressWarnings("null")
    @Override
    public PriceAdjustment findById(Long id) {
        return priceAdjustmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Ajuste de precios no encontrado"));
    }
}
