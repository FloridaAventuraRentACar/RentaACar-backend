package backend.car_rental.mapper;

import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.dto.rental.ResponseRentalDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.Client;
import backend.car_rental.entities.Rental;

public class RentalMapper {
    
    public static Rental toEntity(CreateRentalDto rentalDto, Car car, Client client) {
        return Rental.builder()
            .car(car)
            .client(client)
            .start(rentalDto.getStart())
            .end(rentalDto.getEnd())
            .build();
    }    

    public static ResponseRentalDto toDto(Rental rental) {
        return ResponseRentalDto.builder()
            .id(rental.getId())
            .start(rental.getStart())
            .end(rental.getEnd())
            .carName(rental.getCar().getBrand() + " " + rental.getCar().getModel())
            .clientName(rental.getClient().getName() + " " + rental.getClient().getSurname())
            .totalPrice(rental.getTotalPrice())
            .daysRented(rental.getDaysRented())
            .build();
    }
}
