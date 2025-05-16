package backend.car_rental.controlers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.services.rental.interfaces.ISaveRentalService;
import jakarta.validation.Valid;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/rentals")
public class RentalController {
    
    @Autowired
    private ISaveRentalService rentalService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateRentalDto rentalDto , BindingResult result){
        return rentalService.save(rentalDto, result);
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> delete(@PathVariable Long id){

    //     Optional<Rental> optionalRental = rentalService.findById(id);
    //     if (optionalRental.isPresent()) {
    //         Rental rental = optionalRental.get();
    //         rentalService.delete(rental);
    //         return ResponseEntity.ok().body(rental);
    //     }
    //     return ResponseEntity.notFound().build();
    // }



}
