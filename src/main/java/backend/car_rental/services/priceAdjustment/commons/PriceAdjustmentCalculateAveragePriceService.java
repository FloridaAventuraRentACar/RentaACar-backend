package backend.car_rental.services.priceAdjustment.commons;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import backend.car_rental.entities.Car;
import backend.car_rental.entities.PriceAdjustment;
import backend.car_rental.services.priceAdjustment.interfaces.IPriceAdjustmentCalculateAveragePriceService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PriceAdjustmentCalculateAveragePriceService implements IPriceAdjustmentCalculateAveragePriceService {
    
    @Override
    public double calculateAverageDailyPrice(Car car, LocalDateTime startDateTime, LocalDateTime endDateTime, List<PriceAdjustment> adjustments, long days) {

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
                    break; // Rompemos el bucle asumiendo que solo se aplica un ajuste por día
                }
            }
            
            totalPrice += dailyPrice;
            currentDate = currentDate.plusDays(1); // Avanzar al siguiente día
        }

        // Retornar el promedio
        return totalPrice / days;
    }
}
