package backend.car_rental.mapper;

import backend.car_rental.entities.User;

public class UserMapper {
    
    public static User toUser(String email , String password) {
        return User
            .builder()
            .email(email)
            .password(password)
            .build();
    }
}
