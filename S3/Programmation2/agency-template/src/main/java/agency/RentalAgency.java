package agency;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class RentalAgency {

private List<Vehicle> vehicles = new ArrayList<>();

    public RentalAgency(){
    }

    public  RentalAgency(List<Vehicle> vehicles){
        this.vehicles =vehicles;
    }
    public boolean add(Vehicle vehicle){
        if (contains(vehicle)){
            return false;
        }
        else{vehicles.add(vehicle);
        return true;}
    }
    public void remove(Vehicle vehicle){
        if (contains(vehicle)){
            vehicles.remove(vehicle);
        }
        else{throw new UnknownVehicleException(vehicle);}
    }
    public boolean contains(Vehicle vehicle){
        for(Vehicle vehicleF : vehicles){
            if (vehicleF.equals(vehicle)){
                return true;
            }
        }
        return false;
    }
    public List<Vehicle> getVehicles(){
        return this.vehicles;
    }
    public List<Vehicle> select(Predicate<Vehicle> criterion) {
  
    }
    public void printSelectedVehicles(Predicate<Vehicle> criterion){

    }
}
