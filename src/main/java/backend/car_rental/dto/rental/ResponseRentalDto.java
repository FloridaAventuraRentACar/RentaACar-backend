package backend.car_rental.dto.rental;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRentalDto {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String carName;
    private String clientName;
    private double totalPrice;
    private long daysRented;
}
