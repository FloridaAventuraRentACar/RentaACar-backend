package backend.car_rental.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Entity
public class PriceAdjustment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate periodStart;
    @NotNull
    private LocalDate periodEnd;

    @Max(value = 5, message = "El multiplicador no puede ser mayor a 5")
    @Min(value = 0, message = "El multiplicador no puede ser menor a 0")
    private double multiplier; //Numero por el cual se multiplicaran los precios

    @Size(max = 300, message = "La razon no puede tener mas de 300 caracteres")
    private String reason; 

    @NotNull
    @Builder.Default
    private boolean active = true;
}
