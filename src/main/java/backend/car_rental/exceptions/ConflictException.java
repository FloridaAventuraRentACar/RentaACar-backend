package backend.car_rental.exceptions;

import java.util.List;
import org.springframework.http.HttpStatus;

public class ConflictException extends CustomException {
    public ConflictException(String message, String code) {
        super(message, HttpStatus.CONFLICT, List.of(message), code);
    }

    public ConflictException(String message, List<String> errors, String code) {
        super(message, HttpStatus.CONFLICT, errors, code);
    }

    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT, List.of(message), "CONFLICT");
    }
}