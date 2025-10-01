package backend.car_rental.services.jwt.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import backend.car_rental.dto.jwt.signUp.SignUpRequest;

public interface ISignUpService {
    
    ResponseEntity<?> signUp(SignUpRequest signUpRequest , BindingResult bindingResult);
}
