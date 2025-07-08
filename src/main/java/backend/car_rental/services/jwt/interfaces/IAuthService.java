package backend.car_rental.services.jwt.interfaces;

import org.springframework.http.ResponseEntity;

import backend.car_rental.dto.jwt.AuthRequest;

public interface IAuthService {

    ResponseEntity<?> login(AuthRequest authRequest);

}
