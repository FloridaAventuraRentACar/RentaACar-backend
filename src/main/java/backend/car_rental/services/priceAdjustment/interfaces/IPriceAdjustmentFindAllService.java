package backend.car_rental.services.priceAdjustment.interfaces;

import java.util.List;

import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;

public interface IPriceAdjustmentFindAllService {
    
    List<PriceAdjustmentResponseDto> findAll();
}
