package backend.car_rental.services.car;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import backend.car_rental.dto.car.ResponseCarDto;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.PriceAdjustment;
import backend.car_rental.exceptions.ConflictException;
import backend.car_rental.mapper.CarMapper;
import backend.car_rental.repositories.ICarRepository;
import backend.car_rental.services.car.interfaces.ICarGetAvailableService;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentFindOverlappingPeriodService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarGetAvailableService implements ICarGetAvailableService {

    private final ICarRepository carRepository;
    private final IPriceAdjustmentFindOverlappingPeriodService priceAdjustmentFindOverlappingPeriodService;

    @Override
    public List<ResponseCarDto> getAvailableCars(LocalDateTime startDateTime,
            LocalDateTime endDateTime) {

        long days = daysCalculate(startDateTime, endDateTime);

        if (days < 3) {
            throw new ConflictException("El periodo minimo de alquiler es de 3 dias");
        }

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
        
        List<PriceAdjustment> priceAdjustments = priceAdjustmentFindOverlappingPeriodService.findOverlappingsPeriod(startDateTime.toLocalDate(), endDateTime.toLocalDate());

        if (!priceAdjustments.isEmpty()) {
            result.stream().forEach(car -> {
                car.setPricePerDay(calculateAverageDailyPrice(car, startDateTime, endDateTime, priceAdjustments, days));
            });
        }

        return CarMapper.toDtoList(result);
    }

    private double calculateAverageDailyPrice(Car car, LocalDateTime startDateTime, LocalDateTime endDateTime, List<PriceAdjustment> adjustments, long days) {

        LocalDate start = startDateTime.toLocalDate();
        LocalDate end = endDateTime.toLocalDate();

        //Si la diferencia de horas es menor o igual a 3, se resta un dia al final
        if (endDateTime.getHour() - startDateTime.getHour() <= 3 ) {
            end = end.minusDays(1);
        }
        
        double totalPrice = 0.0;
        LocalDate currentDate = start;

        // Iterar día por día desde la fecha de inicio hasta la fecha de fin
        while (currentDate.isBefore(end) || currentDate.isEqual(end)) {
            double dailyPrice = car.getPricePerDay(); // Precio base del auto
            
            // Buscar si el día actual se ve afectado por algún ajuste de precio
            for (PriceAdjustment adj : adjustments) {
                // Si la fecha actual está entre el inicio y fin del ajuste (inclusive)
                if (!currentDate.isBefore(adj.getPeriodStart()) && !currentDate.isAfter(adj.getPeriodEnd())) {
                    dailyPrice = dailyPrice * adj.getMultiplier();
                    System.out.println("Price adjustment applied: " + adj.getMultiplier() + " for date: " + currentDate + ". Daily price: " + dailyPrice);
                    break; // Rompemos el bucle asumiendo que solo se aplica un ajuste por día
                }
            }
            
            totalPrice += dailyPrice;
            currentDate = currentDate.plusDays(1); // Avanzar al siguiente día
        }

        // Retornar el promedio
        return totalPrice / days;
    }

    private long daysCalculate(LocalDateTime startDate, LocalDateTime endDate) {

        long hours = Duration.between(startDate, endDate).toHours();

        long days = hours / 24;

        if (hours % 24 > 3) {
            days++;
        }

        System.out.println("Days rented: " + days);
        return days;
    }

}
