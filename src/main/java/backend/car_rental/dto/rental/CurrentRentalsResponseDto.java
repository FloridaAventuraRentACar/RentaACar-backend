package backend.car_rental.dto.rental;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentRentalsResponseDto {

    private Long id;
    private String carName;
    private String clientName;
    private LocalDate start;
    private LocalDate end;
    private double totalPrice;
    
}
