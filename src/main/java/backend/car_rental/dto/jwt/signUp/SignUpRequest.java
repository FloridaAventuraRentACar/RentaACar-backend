package backend.car_rental.dto.jwt.signUp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Size(min = 6, message = "Password must be at least 6 characters long")
    @NotBlank
    private String password;
}
