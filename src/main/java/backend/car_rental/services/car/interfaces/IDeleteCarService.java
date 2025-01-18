package backend.car_rental.services.car.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public interface IDeleteCarService {

    ResponseEntity<?> delete(Long id);

}
