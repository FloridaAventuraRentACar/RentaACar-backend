package backend.car_rental.services.car;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.entities.Car;
import backend.car_rental.errors.Errors;
import backend.car_rental.mapper.CarMapper;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.services.car.interfaces.IFindCarService;

@Service
public class FindCarService implements IFindCarService{
    
    @Autowired
    private ICarRepository carRepository;

    @Override
    public ResponseEntity<List<ResponseCarDto>> findAll() {
        return ResponseEntity.ok(
            CarMapper.toDtoList( (List<Car>) carRepository.findAll() )
        );

    }

    @Override
    public ResponseEntity<?> findById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            return Errors.returnError(
                "id",
                "Car not found",
                404
            );
        }
        return ResponseEntity.ok(CarMapper.toDto(optionalCar.get()));
    }

    @Override
    public ResponseEntity<List<ResponseCarDto>> getAvailableCars(LocalDateTime startDateTime,
            LocalDateTime endDateTime) {

        List<Car> cars = carRepository.getAvailableCars(startDateTime.minusHours(2), endDateTime.plusHours(2)); 
        //Debe haber una ventana de 2 horas entre alquileres

        //Elimino de la lista los autos con el mismo nombre
        List<Car> result = cars.stream()
            .collect(Collectors.toMap(
                Car::getName,          // clave: name
                car -> car,            // valor: el objeto Car
                (car1, car2) ->        // conflicto: mismo name
                car1.getId() < car2.getId() ? car1 : car2
            ))
            .values()
            .stream()
            .sorted(Comparator.comparing(Car::getId))
            .toList();
        
        return ResponseEntity.ok(CarMapper.toDtoList(result));
    }
}
