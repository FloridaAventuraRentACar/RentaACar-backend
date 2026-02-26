package backend.car_rental.services.commons.interfaces;

import java.time.LocalDateTime;

public interface IFormatDateService {
    
    String formatToSpanishDate(LocalDateTime dateTime);
}
