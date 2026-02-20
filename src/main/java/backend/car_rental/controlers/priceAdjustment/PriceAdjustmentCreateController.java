package backend.car_rental.controlers.priceAdjustment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.dto.priceAdjustment.PriceAdjustmentRequestDto;
import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentCreateService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/price-adjustment")
public class PriceAdjustmentCreateController {

    private final IPriceAdjustmentCreateService priceAdjustmentCreateService;

    @PostMapping
    public ResponseEntity<PriceAdjustmentResponseDto> createPriceAdjustment(@RequestBody PriceAdjustmentRequestDto priceAdjustmentRequestDto) {
        return ResponseEntity.ok(priceAdjustmentCreateService.createPriceAdjustment(priceAdjustmentRequestDto));
    }
}
