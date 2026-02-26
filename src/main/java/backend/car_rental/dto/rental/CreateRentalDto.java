package backend.car_rental.dto.rental;

import java.time.LocalDateTime;
import java.util.List;

import backend.car_rental.dto.client.CreateClientDto;
import backend.car_rental.enums.BabySeat;
import backend.car_rental.enums.GasTank;
import backend.car_rental.enums.Insurance;
import backend.car_rental.enums.Location;
import backend.car_rental.enums.TravelLocation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalDto {
    
    @NotNull(message = "The id cannot be null")
    private Long carId;

    @NotEmpty
    @Valid
    private List<CreateClientDto> clients;

    @NotNull(message = "The start date cannot be null")
    private LocalDateTime start; //Se pueden registrar alquileres que empiecen en el pasado pero no alquileres que ya hayan terminado

    @Future(message = "The end date must be in the future")
    @NotNull(message = "The end date cannot be null")
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
    
    @Min(1)
    private Double totalPrice;

    @Size(max = 500, message = "Las notas no pueden tener mas de 500 caracteres")
    private String notes;

    @Builder.Default
    private boolean notifyAdmin = false; //Indica si el admin debe ser notificado por whatsapp
}
