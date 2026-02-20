package backend.car_rental.dto.priceAdjustment;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceAdjustmentResponseDto {
    
    private Long id;
    private LocalDate periodStart;
    private LocalDate periodEnd;
    private double multiplier;
    private String reason;
    private boolean active;
}
