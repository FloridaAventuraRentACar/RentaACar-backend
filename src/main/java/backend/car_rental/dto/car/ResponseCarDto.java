package backend.car_rental.dto.car;

import backend.car_rental.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseCarDto {
    private Long id;
    
    private String brand; 

    private String model; 

    private String name;

    private Boolean showColorInName;
    
    private int year; 

    private String color;

    @Builder.Default
    private int passengersAmount = 5; 

    private boolean manual; 

    private int suitcasesAmount; 

    private double pricePerDay; 

    private String imageUrl;

    private CarType type;
}
