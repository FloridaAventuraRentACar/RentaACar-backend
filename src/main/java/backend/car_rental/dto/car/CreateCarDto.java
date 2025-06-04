package backend.car_rental.dto.car;

import backend.car_rental.enums.CarType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateCarDto {

    @NotBlank(message = "The plate cannot be empty")
    private String plate; 
    
    @NotBlank(message = "The brand cannot be empty")
    private String brand; 

    @NotBlank(message = "The model cannot be empty")
    private String model; 

    @Min(2000)
    private int year;

    @NotBlank(message = "The color cannot be empty")
    private String color;

    @Builder.Default
    private int passengersAmount = 5; 

    private boolean manual; 

    private int suitcasesAmount; 

    @Min(1)
    private double pricePerDay;

    private String imageUrl;
    
    private CarType type;
}
