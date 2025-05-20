package backend.car_rental.dto.rental;

import java.time.LocalDateTime;
import java.util.List;

import backend.car_rental.dto.client.ResponseClientDto;
import backend.car_rental.enums.BabySeat;
import backend.car_rental.enums.GasTank;
import backend.car_rental.enums.Insurance;
import backend.car_rental.enums.Location;
import backend.car_rental.enums.TravelLocation;
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

    private Location pickupLocation;
    private Location returnLocation;
    
    private String carName;
    
    private double totalPrice;
    private long daysRented;
    
    private Insurance insurance;
    private BabySeat babySeat;
    private TravelLocation travelLocation;
    private GasTank gasTank;
    
    private List<ResponseClientDto> clients;
}
