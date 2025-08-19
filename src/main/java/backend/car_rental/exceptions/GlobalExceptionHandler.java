package backend.car_rental.exceptions;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import backend.car_rental.response.BaseResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<Object>> handleCustomException(CustomException ex) {
        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .data(null)
                .message(ex.getMessage())
                .errors(ex.getErrors())
                .timestamp(getCurrentTimestamp())
                .build();

        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    private String getCurrentTimestamp() {
        return DateTimeFormatter.ISO_INSTANT.withZone(ZoneOffset.UTC).format(Instant.now());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {

        var errors = new java.util.HashMap<String, String>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        List<String> errorMessages = errors.entrySet().stream()
        .map(entry -> entry.getKey() + ": " + entry.getValue())
        .toList();
        
        BaseResponse<Object> response = BaseResponse.<Object>builder()
                .data(null)
                .message("Error de validación")
                .errors(errorMessages)
                .timestamp(getCurrentTimestamp())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
