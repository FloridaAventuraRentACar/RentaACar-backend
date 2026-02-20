package backend.car_rental.controlers.priceAdjustment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentDeleteService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/price-adjustment")
public class PriceAdjustmentDeleteController {
    
    private final IPriceAdjustmentDeleteService priceAdjustmentDeleteService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriceAdjustment(@PathVariable Long id) {
        priceAdjustmentDeleteService.deletePriceAdjustment(id);
        return ResponseEntity.noContent().build();
    }
}
