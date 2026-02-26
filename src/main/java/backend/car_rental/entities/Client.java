package backend.car_rental.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;
    @NotNull
    @Size(max = 50)
    private String surname;

    private Long phone;
    
    @Email
    private String email;
    private LocalDate bornDate; //Formato: "yyyy-MM-dd"

    private Long licenseNumber;
    private String licenseName;
    private String licenseAddress;
    private LocalDate licenseExpirationDate;

    private String flightNumber;

    private Boolean mainDriver; //True if the client is the main driver
 
    public String getCompleteName(){
        return this.name + " " + this.surname;
    }
}
