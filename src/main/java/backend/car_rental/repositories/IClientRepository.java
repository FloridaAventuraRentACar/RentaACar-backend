package backend.car_rental.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import backend.car_rental.entities.Client;

public interface IClientRepository extends CrudRepository<Client,Long> {
    
    @Query("select c from Client c where c.dni = ?1 and c.deleted = false")
    Optional<Client> findByDni(Long dni);

}
