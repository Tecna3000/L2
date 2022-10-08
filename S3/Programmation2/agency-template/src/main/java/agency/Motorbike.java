package agency;

import util.TimeProvider;

public class Motorbike implements Vehicle{

    private String brand;
    private String model;
    private int productionYear;
    private int cylinderCapacity;

    public Motorbike(String brand, String model, int productionYear, int cylinderCapacity){
        this.brand = brand;
        this.model =model;
        this.productionYear = productionYear;
        this.cylinderCapacity = cylinderCapacity;
        if (productionYear < 1900 || productionYear > currentYearValue()) {
            throw new IllegalArgumentException("production year must be greater than 1900 or less that the current year while the given year is " + productionYear);
        }
        if (cylinderCapacity < 50) {
            throw new IllegalArgumentException("Number of sites must be greater than 50 while the given number is" + cylinderCapacity);
        }

    }

    private static int currentYearValue() {
        return TimeProvider.currentYearValue();
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getProductionYear() {
        return this.productionYear;
    }

    @Override
    public double dailyRentalPrice() {
        return this.cylinderCapacity*0.25;
    }
    public String toString() {
        return prefixString() + suffixString();
    }

    private String suffixString() {
        return "(" + this.cylinderCapacity + "cm³) "+ dailyRentalPrice() + "€";
    }

    private String prefixString() {
        return getName() + this.brand + " " + this.model + " " + this.productionYear + " ";
    }
    private String getName() {
        return "Mototrbike";
    }



}
