package agency;

import java.util.function.Predicate;

public class BrandCriterion implements Predicate {
    private String brand;

    public BrandCriterion(String brand){
        this.brand  = brand;
    }

    public boolean test(Vehicle vehicle){
        if(vehicle.getBrand().equals(brand)){
            return true;
        }
        return false;
    }

    @Override
    public boolean test(Object o) {
        return false;
    }
}
