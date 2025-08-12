package backend.car_rental.dto.client;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateClientDto {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;

    @Email
    private String email;
    private Long phone;
    private Long licenseNumber;
    private LocalDate bornDate;
    private String licenseName;
    private String licenseAddress;
    private LocalDate licenseExpirationDate;
    private Boolean mainDriver; //True if the client is the main driver of the car

}
