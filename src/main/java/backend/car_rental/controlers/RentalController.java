package backend.car_rental.controlers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.services.rental.interfaces.IFindRentalService;
import backend.car_rental.services.rental.interfaces.ISaveRentalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    
    @Autowired
    private ISaveRentalService saveRentalService;
    @Autowired
    private IFindRentalService findRentalService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateRentalDto rentalDto , BindingResult result){
        return saveRentalService.save(rentalDto, result);
    }

    @GetMapping("/current")
    public ResponseEntity<?> findCurrentRentals(){

        return findRentalService.findCurrentRentals(); 
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findRentalById(@PathVariable Long id){
        return findRentalService.findRentalById(id); 
    }

}
