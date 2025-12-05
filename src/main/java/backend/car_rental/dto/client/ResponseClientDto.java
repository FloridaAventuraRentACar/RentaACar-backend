package backend.car_rental.dto.client;

import java.time.LocalDate;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseClientDto {

    private Long id;
    private String name;
    private String surname;

    private String email;
    private Long phone;
    private Long licenseNumber;
    private LocalDate bornDate;
    private String licenseName;
    private String licenseAddress;
    private LocalDate licenseExpirationDate;
    private String flightNumber;
    private Boolean mainDriver;

}
