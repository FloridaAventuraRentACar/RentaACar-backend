package backend.car_rental.mapper;

import backend.car_rental.dto.car.CreateCarDto;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.entities.Car;
import java.util.List;

public class CarMapper {
    public Car toEntity(CreateCarDto carDto){
        return Car.builder()
            .plate(carDto.getPlate())
            .brand(carDto.getBrand())
            .model(carDto.getModel())
            .year(carDto.getYear())
            .color(carDto.getColor())
            .passengersAmount(carDto.getPassengersAmount())
            .manual(carDto.isManual())
            .suitcasesAmount(carDto.getSuitcasesAmount())
            .pricePerDay(carDto.getPricePerDay())
            .build();
    }

    public ResponseCarDto toDto(Car car){
        return ResponseCarDto.builder()
            .id(car.getId())
            .plate(car.getPlate())
            .brand(car.getBrand())
            .model(car.getModel())
            .year(car.getYear())
            .color(car.getColor())
            .passengersAmount(car.getPassengersAmount())
            .manual(car.isManual())
            .suitcasesAmount(car.getSuitcasesAmount())
            .pricePerDay(car.getPricePerDay())
            .hidden(car.isHidden())
            .build();
    }
    
    public List<ResponseCarDto> toDtoList(List<Car> cars){
        return cars.stream()
            .map(c -> toDto(c))
            .toList();
    }    
}
