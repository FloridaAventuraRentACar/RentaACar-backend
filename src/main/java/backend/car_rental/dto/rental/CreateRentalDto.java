package backend.car_rental.dto.rental;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalDto {
    
    private Long carId;
    private Long clientId;

    @Future
    private LocalDateTime start;
    private LocalDateTime end;
}
