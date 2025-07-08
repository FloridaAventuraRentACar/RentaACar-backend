package backend.car_rental.services.jwt;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import backend.car_rental.dto.jwt.AuthRequest;
import backend.car_rental.entities.User;
import backend.car_rental.errors.Errors;
import backend.car_rental.mapper.AuthMapper;
import backend.car_rental.repositories.IUserRepository;
import backend.car_rental.services.jwt.interfaces.IAuthService;
import backend.car_rental.services.jwt.interfaces.IJwtService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService{

    private final IUserRepository userRepository;
    private final PasswordEncoder PasswordEncoder;
    private final IJwtService jwtService;

    @Override
    public ResponseEntity<?> login(AuthRequest authRequest , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return Errors.returnSintaxErrors(bindingResult);
            
        }

        Optional<User> optionalUser = userRepository.findByEmail(authRequest.getEmail());
        if (optionalUser.isEmpty()) {
            return Errors.returnError(
                "email",
                "User not found",
                404
            );
        }

        User user = optionalUser.get();
        if (!PasswordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            return Errors.returnError(
                "password",
                "Invalid password",
                401
            );
        }

        String jwt = jwtService.generateToken(user);

        return ResponseEntity.ok().body(AuthMapper.toLoginResponse(jwt, user.getEmail()));
        
    }
    
}
