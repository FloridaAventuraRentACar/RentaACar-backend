package backend.car_rental.dto.client;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateClientDto {
    
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;

    @Email
    private String email;
    @NotNull
    private Long phone;
    @NotNull
    private Long licenseNumber;
    @NotNull
    private LocalDate bornDate;
    @NotNull
    private String licenseName;
    @NotNull
    private String licenseAddress;
    @NotNull
    private LocalDate licenseExpirationDate;

    private String flightNumber;
    private Boolean mainDriver; //True if the client is the main driver of the car
}
