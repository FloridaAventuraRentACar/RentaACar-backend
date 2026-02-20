package backend.car_rental.controlers.priceAdjustment;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend.car_rental.dto.priceAdjustment.PriceAdjustmentResponseDto;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentFindAllService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/price-adjustment")
public class PriceAdjustmentFindAllController {

    private final IPriceAdjustmentFindAllService priceAdjustmentFindAllService;

    @GetMapping
    public ResponseEntity<List<PriceAdjustmentResponseDto>> findAllPriceAdjustments() {
        return ResponseEntity.ok(priceAdjustmentFindAllService.findAll());
    }
}
