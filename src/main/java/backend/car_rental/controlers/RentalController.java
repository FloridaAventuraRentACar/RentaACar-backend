package backend.car_rental.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.dto.rental.UpdateRentalDto;
import backend.car_rental.services.rental.interfaces.IFindRentalService;
import backend.car_rental.services.rental.interfaces.ISaveRentalService;
import backend.car_rental.services.rental.interfaces.IUpdateRentalService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/rentals")
@AllArgsConstructor
public class RentalController {
    
    private ISaveRentalService saveRentalService;
    private IFindRentalService findRentalService;
    private IUpdateRentalService updateRentalService;

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

    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateRentalDto rentalDto, BindingResult result , @PathVariable Long id){
        return updateRentalService.update(rentalDto, result, id);
    }
}
