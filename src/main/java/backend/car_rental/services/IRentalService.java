package backend.car_rental.services;

import java.util.List;


import backend.car_rental.entities.Rental;

public interface IRentalService {
    List<Rental> findAll();

    Rental findById(Long id);

    Rental save(Rental rental);

    void deleteById(Long id);
}
