package backend.car_rental.services.car;

import org.springframework.stereotype.Service;

import backend.car_rental.dto.car.PartialUpdateCarDto;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.entities.Car;
import backend.car_rental.mapper.CarMapper;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.services.car.interfaces.ICarFindByIdService;
import backend.car_rental.services.car.interfaces.ICarPartialUpdateService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarPartialUpdateService implements ICarPartialUpdateService{

    private final ICarFindByIdService carFindByIdService;
    private final ICarRepository carRepository;

    @Override
    public ResponseCarDto partialUpdateCar(Long id, PartialUpdateCarDto carDto) {
        
        Car car = carFindByIdService.findCarById(id);

        Car updatedCar = CarMapper.toPartialUpdateEntity(car, carDto);

        carRepository.save(updatedCar);
        
        ResponseCarDto responseCarDto = CarMapper.toDto(updatedCar);

        return responseCarDto;
    }
}
