package backend.car_rental.services.priceAdjustment.interfaces;

import backend.car_rental.dto.priceAdjustment.PriceAdjustmentRequestDto;
import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;

public interface IPriceAdjustmentUpdateService {
    PriceAdjustmentResponseDto updatePriceAdjustment(Long id, PriceAdjustmentRequestDto priceAdjustmentRequestDto);
}
