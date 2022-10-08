package agency;

import java.util.function.Predicate;

public class MaxPriceCriterion implements Predicate {

    private int dailyRentalPrice;
    public MaxPriceCriterion (int dailyRentalPrice){
        this.dailyRentalPrice = dailyRentalPrice;
    }
    @Override
    public boolean test(Object o) {
        return false;
    }

    public boolean test(Vehicle vehicle) {
        if (vehicle.dailyRentalPrice()<= this.dailyRentalPrice){
            return true;
        }
        return false;
    }
}
