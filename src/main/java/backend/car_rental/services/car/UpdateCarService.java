package backend.car_rental.services.car;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import backend.car_rental.dto.car.CreateCarDto;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.entities.Car;
import backend.car_rental.mapper.CarMapper;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.services.car.interfaces.ICarFindByIdService;
import backend.car_rental.services.car.interfaces.IUpdateCarService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UpdateCarService implements IUpdateCarService{
    
    private final ICarFindByIdService carFindByIdService;
    private final ICarRepository carRepository;

    @Override
    @Transactional
    public ResponseCarDto updateCar(Long id, CreateCarDto carDto){

        carFindByIdService.findCarById(id); //Lanza un 404 si no encuentra el auto con el id proporcionado

        Car carToSave = CarMapper.toEntity(carDto);
        carToSave.setId(id);

        return CarMapper.toDto(carRepository.save(carToSave));
    }
}
