package agency;

import util.TimeProvider;

import java.util.Objects;

public class Car extends AbstractVehicle {

    private int numberOfSeats;

    public Car(String brand, String model, int productionYear, int numberOfSeats) {
        super(brand, model, productionYear);
        this.numberOfSeats = numberOfSeats;
        if (productionYear < 1900 || productionYear > currentYearValue()) {
            throw new IllegalArgumentException("production year must be greater than 1900 or less that the current year while the given year is " + productionYear);
        }
        if (numberOfSeats < 1) {
            throw new IllegalArgumentException("Number of sites must be greater than 1 while the given number is" + numberOfSeats);
        }

    }

    @Override
    public double dailyRentalPrice() {
       if (!isNew()){
            return this.numberOfSeats*20;

        }
        return this.numberOfSeats*40;
    }

    @Override
    protected String suffixString() {
        return "(" + this.numberOfSeats + " seat" + (numberOfSeats == 1 ? "" : "s") + ") : " + this.dailyRentalPrice() + "€";
    }

    @Override
    protected String getName() {
        return "Car ";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return numberOfSeats == car.numberOfSeats&&super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfSeats);
    }
}
