package backend.car_rental.dto.jwt.signUp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignUpRequest {
    
    @Email
    private String email;

    @Min(6)
    @NotBlank
    private String password;
}
