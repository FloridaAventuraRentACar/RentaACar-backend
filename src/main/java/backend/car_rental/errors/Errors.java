package backend.car_rental.errors;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class Errors {
    
      public static ResponseEntity<Map<String,String>> returnSintaxErrors(BindingResult result){
        Map<String,String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);    
    }


    public static ResponseEntity<Map<String,String>> returnError(String field, String message, int status){
        return ResponseEntity.status(status).body(Collections.singletonMap(field, message));
    }

}
