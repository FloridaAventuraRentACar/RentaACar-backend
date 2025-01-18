package backend.car_rental.dto.car;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

    public CreateCarDto(String plate, String brand, String model, int year,
            String color, int passengersAmount, boolean manual,
            int suitcasesAmount, @Min(1) double pricePerDay) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.passengersAmount = passengersAmount;
        this.manual = manual;
        this.suitcasesAmount = suitcasesAmount;
        this.pricePerDay = pricePerDay;
    } 

    
}
