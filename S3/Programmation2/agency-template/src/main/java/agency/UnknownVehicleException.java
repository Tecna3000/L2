package agency;

public class UnknownVehicleException extends RuntimeException{
    private Vehicle vehicle;
    public UnknownVehicleException(Vehicle vehicle){
        this.vehicle = vehicle;
    }
   public String getMessage(){
        return (toString(this.vehicle)+ "is not in the agency ");
   }

    private String toString(Vehicle vehicle) {
        return ""+ this.vehicle;
    }
}
