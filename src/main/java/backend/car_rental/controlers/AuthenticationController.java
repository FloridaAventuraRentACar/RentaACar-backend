package backend.car_rental.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.dto.jwt.login.AuthRequest;
import backend.car_rental.dto.jwt.signUp.SignUpRequest;
import backend.car_rental.services.jwt.interfaces.IAuthService;
import backend.car_rental.services.jwt.interfaces.ISignUpService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final IAuthService authService;
    private final ISignUpService signUpService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest , BindingResult result){
        return authService.login(authRequest , result);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest , BindingResult result){
        return signUpService.signUp(signUpRequest , result);
    }

}