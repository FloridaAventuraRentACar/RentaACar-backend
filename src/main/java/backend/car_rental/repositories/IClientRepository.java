package backend.car_rental.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import backend.car_rental.entities.Client;

public interface IClientRepository extends CrudRepository<Client,Long> {

    @Query("select c from Client c where c.deleted = false")
    List<Client> findAllActive();

    @Query("select c from Client c where c.deleted = true")
    List<Client> findAllDeleted();


    @Query("select c from Client c where c.id = ?1 and c.deleted = false")
    Optional<Client> findActiveById(Long id);
    
    @Query("select c from Client c where c.dni = ?1 and c.deleted = false")
    Optional<Client> findByDni(Long dni);

}
