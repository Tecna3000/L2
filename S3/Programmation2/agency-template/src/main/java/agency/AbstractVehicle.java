package agency;

import util.TimeProvider;

import java.util.Objects;

public abstract class AbstractVehicle implements Vehicle {
    protected String brand;
    protected String model;
    protected int productionYear;

    public AbstractVehicle(String brand, String model, int productionYear) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }

    public static int currentYearValue() {
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
    public abstract double dailyRentalPrice();

    public String toString() {
        return prefixString() + suffixString();
    }

    protected abstract String suffixString();

    private String prefixString() {
        return getName() + this.brand + " " + this.model + " " + this.productionYear + " ";
    }

    protected abstract String getName();

    public boolean isNew() {
        if (TimeProvider.currentYearValue() - this.getProductionYear() == 5 || TimeProvider.currentYearValue() - this.getProductionYear() < 5) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractVehicle that = (AbstractVehicle) o;
        return productionYear == that.productionYear && Objects.equals(brand, that.brand) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, productionYear);
    }
}
