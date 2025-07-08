package backend.car_rental.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import backend.car_rental.entities.User;

public interface IUserRepository extends CrudRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
}
