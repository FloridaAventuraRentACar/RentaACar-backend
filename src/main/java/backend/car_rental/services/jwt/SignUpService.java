package backend.car_rental.services.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import backend.car_rental.dto.jwt.signUp.SignUpRequest;
import backend.car_rental.entities.User;
import backend.car_rental.errors.Errors;
import backend.car_rental.mapper.AuthMapper;
import backend.car_rental.mapper.UserMapper;
import backend.car_rental.repositories.IUserRepository;
import backend.car_rental.services.jwt.interfaces.IJwtService;
import backend.car_rental.services.jwt.interfaces.ISignUpService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SignUpService implements ISignUpService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;

    @Override
    public ResponseEntity<?> signUp(SignUpRequest signUpRequest, BindingResult results) {

        if (results.hasFieldErrors()) {
            return Errors.returnSintaxErrors(results);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return Errors.returnError(
                "email",
                "Email already exists",
                409
            );
        }

        User userSaved = userRepository.save(UserMapper.toUser(signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword())));

        String jwt = jwtService.generateToken(userSaved);

        return ResponseEntity.ok().body(AuthMapper.toLoginResponse(jwt, userSaved.getEmail()));

    }
    

}
