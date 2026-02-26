package backend.car_rental.services.commons;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.stereotype.Service;

import backend.car_rental.services.commons.interfaces.IFormatDateService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FormatDateService implements IFormatDateService{
    
    @Override
    public String formatToSpanishDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "d 'de' MMMM 'de' yyyy",
                Locale.of("es", "ES")
        );
        
        return dateTime.format(formatter);
    }
    
}
