package backend.car_rental.exceptions;

import java.util.List;
import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException {

    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST, List.of(message), "BAD_REQUEST");
    }

    public BadRequestException(String message, List<String> errors) {
        super(message, HttpStatus.BAD_REQUEST, errors, "BAD_REQUEST");
    }
}