package backend.car_rental.exceptions;

import org.springframework.http.HttpStatus;
import java.util.Collections; 

public class InternalServerException extends CustomException {

    public InternalServerException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList(message), "INTERNAL_SERVER_ERROR");
    }

   
    public InternalServerException() {
        super("Unexpected error ocurred.", HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList("Unexpected error ocurred."), "INTERNAL_SERVER_ERROR");
    }
}