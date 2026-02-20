package backend.car_rental.dto.priceAdjustment;

import java.time.LocalDate;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceAdjustmentRequestDto {
    
    @NotNull(message = "El periodo de inicio no puede ser nulo")
    @FutureOrPresent(message = "El periodo de inicio debe ser mayor o igual a la fecha actual")
    private LocalDate periodStart;

    @NotNull(message = "El periodo de fin no puede ser nulo")
    @Future(message = "El periodo de fin debe ser mayor a la fecha actual")
    private LocalDate periodEnd;

    private double multiplier; //Numero por el cual se multiplicaran los precios

    @Size(max = 300, message = "La razon no puede tener mas de 300 caracteres")
    private String reason; 

    @AssertTrue(message = "El multiplicador debe ser mayor a 0 y menor o igual a 5")
    private boolean isMultiplierValid() {
        return multiplier > 0 && multiplier <= 5;
    }
}
