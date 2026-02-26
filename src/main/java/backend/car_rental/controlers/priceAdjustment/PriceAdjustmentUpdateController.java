package backend.car_rental.controlers.priceAdjustment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.dto.priceAdjustment.PriceAdjustmentRequestDto;
import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentUpdateService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/price-adjustment")
public class PriceAdjustmentUpdateController {
    
    private final IPriceAdjustmentUpdateService priceAdjustmentUpdateService;

    @PutMapping("/{id}")
    public ResponseEntity<PriceAdjustmentResponseDto> updatePriceAdjustment(@PathVariable Long id, @RequestBody PriceAdjustmentRequestDto priceAdjustmentRequestDto) {
        return ResponseEntity.ok(priceAdjustmentUpdateService.updatePriceAdjustment(id, priceAdjustmentRequestDto));
    }
}
