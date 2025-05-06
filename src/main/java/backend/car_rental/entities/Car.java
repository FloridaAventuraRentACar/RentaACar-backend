package backend.car_rental.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cars")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate; //Patente
    
    private String brand; //Marca

    private String model; //Modelo

    private int year; //Año de fabricacion

    private String color;

    private int passengersAmount; //Cantidad de pasajeros que entran en el auto

    private boolean manual; //True si es manual, false si es automatico

    private int suitcasesAmount; //Cantidad de maletas que puede llevar el auto

    private double pricePerDay; //Precio por dia

    private boolean hidden;//True si se quiere ocultar al auto

    private String imageUrl;

}
