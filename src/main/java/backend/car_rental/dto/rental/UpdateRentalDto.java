package backend.car_rental.dto.rental;

import java.time.LocalDateTime;
import java.util.List;
import backend.car_rental.dto.client.UpdateClientDto;
import backend.car_rental.enums.BabySeat;
import backend.car_rental.enums.GasTank;
import backend.car_rental.enums.Insurance;
import backend.car_rental.enums.Location;
import backend.car_rental.enums.TravelLocation;
import jakarta.validation.Valid;
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
    @Valid
    private List<UpdateClientDto> clients;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime end;

    @NotNull
    private Location pickupLocation;
    @NotNull
    private Location returnLocation;
    
    @NotNull
    private Insurance insurance;
    @NotNull
    private BabySeat babySeat;

    private TravelLocation travelLocation;
    @NotNull
    private GasTank gasTank;
}
