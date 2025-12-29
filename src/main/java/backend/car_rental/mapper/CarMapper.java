package backend.car_rental.mapper;

import backend.car_rental.dto.car.CreateCarDto;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.entities.Car;
import java.util.List;

public class CarMapper {
    public static Car toEntity(CreateCarDto carDto){
        return Car.builder()
            .brand(carDto.getBrand())
            .model(carDto.getModel())
            .year(carDto.getYear())
            .color(carDto.getColor())
            .showColorInName(carDto.isShowColorInName())
            .passengersAmount(carDto.getPassengersAmount())
            .isManual(carDto.isManual())
            .suitcasesAmount(carDto.getSuitcasesAmount())
            .pricePerDay(carDto.getPricePerDay())
            .imageUrl(carDto.getImageUrl())
            .type(carDto.getType())
            .build();
    }

    public static ResponseCarDto toDto(Car car){
        return ResponseCarDto.builder()
            .id(car.getId())
            .brand(car.getBrand())
            .model(car.getModel())
            .name(car.getBrand() + " " + car.getModel() + (car.isShowColorInName() ? "(" + car.getColor() + ")" : ""))
            .showColorInName(car.isShowColorInName())
            .year(car.getYear())
            .color(car.getColor())
            .passengersAmount(car.getPassengersAmount())
            .manual(car.isManual())
            .suitcasesAmount(car.getSuitcasesAmount())
            .pricePerDay(car.getPricePerDay())
            .imageUrl(car.getImageUrl())
            .type(car.getType())
            .build();
    }
    
    public static List<ResponseCarDto> toDtoList(List<Car> cars){
        return cars.stream()
            .map(c -> toDto(c))
            .toList();
    }    
}
