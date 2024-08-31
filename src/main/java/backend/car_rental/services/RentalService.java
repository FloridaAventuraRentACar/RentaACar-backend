package backend.car_rental.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.car_rental.entities.Rental;
import backend.car_rental.repositories.IRentalRepository;

@Service
public class RentalService implements IRentalService {

    @Autowired
    private IRentalRepository rentalRepository;
    @Override
    public List<Rental> findAll() {
        return (List<Rental>)rentalRepository.findAll();
    }

    @Override
    public Rental findById(Long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isPresent()) {
            return optionalRental.get();
        }
        throw new RuntimeException("Rental not found");
    }

    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public void deleteById(Long id) {
        rentalRepository.findById(id).ifPresent(rental -> {
            rental.setDeleted(true);
        });
    }
    
}
