package backend.car_rental.entities;

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
    private String dni;
    private String email;
    private String phone;
    private String address;
    private String nationality;
    private String birthDate;
    private String sex;

    
    public Client() {
    }

    public Client(String completeName, String dni, String phone) {
        this.completeName = completeName;
        this.dni = dni;
        this.phone = phone;
    }

    public Client(String completeName, String dni, String email, String phone, String address, String nationality,
            String birthDate, String sex) {
        this.completeName = completeName;
        this.dni = dni;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.nationality = nationality;
        this.birthDate = birthDate;
        this.sex = sex;
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
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    
}
