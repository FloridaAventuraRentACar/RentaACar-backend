package backend.car_rental.mapper;

import backend.car_rental.dto.car.CreateCarDto;
import backend.car_rental.dto.car.PartialUpdateCarDto;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.entities.Car;
import java.util.List;

public class CarMapper {
    public static Car toEntity(CreateCarDto carDto) {
        return Car.builder()
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .year(carDto.getYear())
                .color(carDto.getColor())
                .showColorInName(carDto.isShowColorInName())
                .passengersAmount(carDto.getPassengersAmount())
                .suitcasesAmount(carDto.getSuitcasesAmount())
                .pricePerDay(carDto.getPricePerDay())
                .imageUrl(carDto.getImageUrl())
                .tankPrice(carDto.getTankPrice())
                .size(carDto.getSize())
                .build();
    }

    public static Car toPartialUpdateEntity(Car car, PartialUpdateCarDto carDto) {
        if (carDto.getBrand() != null)
            car.setBrand(carDto.getBrand());
        if (carDto.getModel() != null)
            car.setModel(carDto.getModel());
        if (carDto.getYear() != null)
            car.setYear(carDto.getYear());
        if (carDto.getColor() != null)
            car.setColor(carDto.getColor());
        if (carDto.getShowColorInName() != null)
            car.setShowColorInName(carDto.getShowColorInName());
        if (carDto.getPassengersAmount() != 0)
            car.setPassengersAmount(carDto.getPassengersAmount());
        if (carDto.getSuitcasesAmount() != 0)
            car.setSuitcasesAmount(carDto.getSuitcasesAmount());
        if (carDto.getPricePerDay() != null)
            car.setPricePerDay(carDto.getPricePerDay());
        if (carDto.getImageUrl() != null)
            car.setImageUrl(carDto.getImageUrl());
        if (carDto.getTankPrice() != null)
            car.setTankPrice(carDto.getTankPrice());
        if (carDto.getSize() != null)
            car.setSize(carDto.getSize());
        return car;
    }

    public static ResponseCarDto toDto(Car car) {
        return ResponseCarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .name(car.getBrand() + " " + car.getModel()
                        + (car.isShowColorInName() ? "(" + car.getColor() + ")" : ""))
                .showColorInName(car.isShowColorInName())
                .year(car.getYear())
                .color(car.getColor())
                .passengersAmount(car.getPassengersAmount())
                .suitcasesAmount(car.getSuitcasesAmount())
                .pricePerDay(car.getPricePerDay())
                .imageUrl(car.getImageUrl())
                .size(car.getSize())
                .tankPrice(car.getTankPrice())
                .build();
    }

    public static List<ResponseCarDto> toDtoList(List<Car> cars) {
        return cars.stream()
                .map(c -> toDto(c))
                .toList();
    }
}
