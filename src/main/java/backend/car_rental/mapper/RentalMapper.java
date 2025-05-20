package backend.car_rental.mapper;

import java.util.List;

import backend.car_rental.dto.rental.CreateRentalDto;
import backend.car_rental.dto.rental.ResponseRentalDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.Client;
import backend.car_rental.entities.Rental;

public class RentalMapper {
    
    public static Rental toEntity(CreateRentalDto rentalDto, Car car,   List<Client> clients) {
        return Rental.builder()
            .car(car)
            .clients(clients)
            .start(rentalDto.getStart())
            .end(rentalDto.getEnd())
            .insurance(rentalDto.getInsurance())
            .babySeat(rentalDto.getBabySeat())
            .travelLocation(rentalDto.getTravelLocation())
            .gasTank(rentalDto.getGasTank())
            .build();
    }    

    public static ResponseRentalDto toDto(Rental rental) {
        return ResponseRentalDto.builder()
            .id(rental.getId())
            .start(rental.getStart())
            .end(rental.getEnd())
            .carName(rental.getCar().getBrand() + " " + rental.getCar().getModel())
            .clients(ClientMapper.toDtoList(rental.getClients()))
            .totalPrice(rental.getTotalPrice())
            .daysRented(rental.getDaysRented())
            .insurance(rental.getInsurance())
            .babySeat(rental.getBabySeat())
            .travelLocation(rental.getTravelLocation())
            .gasTank(rental.getGasTank())
            .build();
    }
}
