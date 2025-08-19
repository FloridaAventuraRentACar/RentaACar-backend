package backend.car_rental.exceptions;

import java.util.List;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED, List.of(message));
    }

    public UnauthorizedException(String message, List<String> errors) {
        super(message, HttpStatus.UNAUTHORIZED, errors);
    }
}