package backend.car_rental.services.jwt;

import org.springframework.http.ResponseEntity;

import backend.car_rental.dto.jwt.AuthRequest;
import backend.car_rental.services.jwt.interfaces.IAuthService;

public class AuthService implements IAuthService{

    @Override
    public ResponseEntity<?> login(AuthRequest authRequest) {
        
    }
    
}
