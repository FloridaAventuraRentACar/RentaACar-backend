package backend.car_rental.enums;

public enum CarType {
    
    SMALL(50),
    MEDIUM(60),
    LARGE(85);

    private final int price;

    CarType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

