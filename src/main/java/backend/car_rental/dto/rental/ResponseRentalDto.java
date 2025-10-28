package backend.car_rental.dto.rental;

import java.time.LocalDateTime;
import java.util.List;

import backend.car_rental.dto.client.ResponseClientDto;
import backend.car_rental.enums.BabySeat;
import backend.car_rental.enums.GasTank;
import backend.car_rental.enums.Insurance;
import backend.car_rental.enums.Location;
import backend.car_rental.enums.TravelLocation;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRentalDto {

    @NotNull(message = "The id cannot be null")
    private Long id;
    
    @NotNull(message = "The start date cannot be null")
    private LocalDateTime start;

    @Future(message = "The end date must be in the future")
    @NotNull(message = "The end date cannot be null")
    private LocalDateTime end;

    @NotNull(message = "The pickup location cannot be null")
    private Location pickupLocation;

    @NotNull(message = "The return location cannot be null")
    private Location returnLocation;
    
    private Long carId;
    private String carName;
    
    private double totalPrice;
    private long daysRented;
    
    private Insurance insurance;
    private BabySeat babySeat;
    private TravelLocation travelLocation;
    private GasTank gasTank;
    
    private List<ResponseClientDto> clients;
}
