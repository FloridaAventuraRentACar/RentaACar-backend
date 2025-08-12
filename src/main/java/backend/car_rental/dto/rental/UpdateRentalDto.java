package backend.car_rental.dto.rental;

import java.time.LocalDateTime;
import java.util.List;
import backend.car_rental.dto.client.UpdateClientDto;
import backend.car_rental.enums.BabySeat;
import backend.car_rental.enums.GasTank;
import backend.car_rental.enums.Insurance;
import backend.car_rental.enums.Location;
import backend.car_rental.enums.TravelLocation;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UpdateRentalDto {
    
    @NotNull
    private Long carId;

    @NotEmpty
    private List<UpdateClientDto> clients;

    @Future
    @NotNull
    private LocalDateTime start;

    @Future
    @NotNull
    private LocalDateTime end;

    private Location pickupLocation;
    private Location returnLocation;
    
    private Insurance insurance;
    private BabySeat babySeat;
    private TravelLocation travelLocation;
    private GasTank gasTank;
}
