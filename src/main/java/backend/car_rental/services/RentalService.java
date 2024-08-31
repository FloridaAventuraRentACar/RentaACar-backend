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
    public List<Rental> findAllDeleted() {
        return (List<Rental>)rentalRepository.findAllDeleted();
    }


    @Override
    public Optional<Rental> findById(Long id) {
        return rentalRepository.findActiveById(id);
      
    }

    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public void delete(Rental rental) {
        rental.setDeleted(true);
        rentalRepository.save(rental);
    }
    
}
