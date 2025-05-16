package backend.car_rental.dto.rental;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<Long> clientIds;

    @Future
    private LocalDateTime start;
    private LocalDateTime end;
}
