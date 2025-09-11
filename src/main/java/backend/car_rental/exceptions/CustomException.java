package backend.car_rental.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public abstract class CustomException extends RuntimeException {
    private final HttpStatus status;
    private final List<String> errors;
    private final String code;

    public CustomException(String message, HttpStatus status, List<String> errors, String code) {
        super(message);
        this.code = code;
        this.status = status;
        this.errors = errors;
    }
}
