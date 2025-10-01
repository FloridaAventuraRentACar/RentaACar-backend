package backend.car_rental.exceptions;

import java.util.List;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends CustomException {
    public ForbiddenException(String message, String code) {
        super(message, HttpStatus.FORBIDDEN, List.of(message), code);
    }

    public ForbiddenException(String message, List<String> errors, String code) {
        super(message, HttpStatus.FORBIDDEN, errors, code);
    }
}