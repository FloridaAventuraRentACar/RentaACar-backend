package backend.car_rental.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String completeName;
    private Long dni;
    private String email;
    private Long phone;

    private LocalDate bornDate; //Formato: "yyyy-MM-dd"

    private boolean deleted; //True si el cliente fue eliminado
    
    public Client() {

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCompleteName() {
        return completeName;
    }
    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }
    public Long getDni() {
        return dni;
    }
    public void setDni(Long dni) {
        this.dni = dni;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }
   
   

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }




   

    
}
