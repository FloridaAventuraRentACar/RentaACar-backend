package backend.car_rental.dto.rental;

import java.time.LocalDateTime;
import java.util.List;

import backend.car_rental.enums.BabySeat;
import backend.car_rental.enums.GasTank;
import backend.car_rental.enums.Insurance;
import backend.car_rental.enums.TravelLocation;
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

    private Insurance insurance;
    private BabySeat babySeat;
    private TravelLocation travelLocation;
    private GasTank gasTank;
}
