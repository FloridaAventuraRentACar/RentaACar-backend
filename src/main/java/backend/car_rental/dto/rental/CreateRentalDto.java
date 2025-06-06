package backend.car_rental.dto.rental;

import java.time.LocalDateTime;
import java.util.List;

import backend.car_rental.enums.BabySeat;
import backend.car_rental.enums.GasTank;
import backend.car_rental.enums.Insurance;
import backend.car_rental.enums.Location;
import backend.car_rental.enums.TravelLocation;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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

    @NotEmpty
    private List<Long> clientIds;

    @Future
    private LocalDateTime start;
    private LocalDateTime end;

    private Location pickupLocation;
    private Location returnLocation;
    
    private Insurance insurance;
    private BabySeat babySeat;
    private TravelLocation travelLocation;
    private GasTank gasTank;
}
