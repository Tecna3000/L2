package agency;

public class MotorBike2 extends AbstractVehicle{
    private int cylinderCapacity;

    public MotorBike2(String brand, String model, int productionYear, int cylinderCapacity) {
        super(brand, model, productionYear);
        this.cylinderCapacity = cylinderCapacity;
    }

    @Override
    public double dailyRentalPrice() {
        return this.cylinderCapacity*0.25;
    }

    @Override
    protected String suffixString() {
        return "(" + this.cylinderCapacity + "cm³) "+ dailyRentalPrice() + "€";
    }

    @Override
    protected String getName() {
        return "MotorBike";
    }
}
