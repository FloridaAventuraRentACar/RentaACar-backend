package backend.car_rental.dto.rental;

import java.time.LocalDateTime;
import java.util.List;

import backend.car_rental.dto.client.ResponseClientDto;
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
    private List<ResponseClientDto> clients;
    private double totalPrice;
    private long daysRented;
}
