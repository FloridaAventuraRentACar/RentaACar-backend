package backend.car_rental.enums;

public enum CarType {
    
    SMALL(40),
    MEDIUM(50),
    LARGE(70);

    private final int price;

    CarType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

