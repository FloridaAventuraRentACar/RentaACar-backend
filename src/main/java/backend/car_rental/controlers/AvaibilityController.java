package backend.car_rental.controlers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.services.car.interfaces.IFindCarService;

@RestController
@RequestMapping("/availability")
public class AvaibilityController {

    @Autowired
    private IFindCarService carService;

    @GetMapping
    public ResponseEntity<?> getAvailability(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime, 
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime){
    //El formato de LocalDateTime es: "2024-08-30T14:30:00"
        return carService.getAvailableCars(startDateTime, endDateTime);
    }
}
