package backend.car_rental.dto.car;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCarDto {
    private Long id;

    private String plate; 
    
    private String brand; 

    private String model; 

    private String name;

    private int year; 

    private String color;

    @Builder.Default
    private int passengersAmount = 5; 

    private boolean manual; 

    private int suitcasesAmount; 

    private double pricePerDay; 

    private boolean hidden;
}
