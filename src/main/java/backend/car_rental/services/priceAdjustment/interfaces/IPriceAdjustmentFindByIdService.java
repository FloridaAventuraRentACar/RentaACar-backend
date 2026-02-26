package backend.car_rental.services.priceAdjustment.interfaces;

import backend.car_rental.entities.PriceAdjustment;

public interface IPriceAdjustmentFindByIdService {
    PriceAdjustment findById(Long id);
}
