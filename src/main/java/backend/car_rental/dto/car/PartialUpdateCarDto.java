package backend.car_rental.dto.car;

import backend.car_rental.enums.CarSize;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartialUpdateCarDto {
    
    private String brand; 

    private String model; 

    @Min(2000)
    private Integer year; 

    private String color;

    private Boolean showColorInName;
    
    private int passengersAmount; 

    private int suitcasesAmount; 

    @Min(1)
    private Double pricePerDay; 

    private String imageUrl;

    private CarSize size;

    @Min(0)
    @Max(200)
    private Integer tankPrice;
}
