package backend.car_rental.mapper;

import backend.car_rental.dto.jwt.LoginResponse;

public class AuthMapper {
    
    public static LoginResponse toLoginResponse(String jwt, String email) {
        return LoginResponse
            .builder()
            .Jwt(jwt)
            .email(email)
            .build();
    }
    
}
