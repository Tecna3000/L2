package agency;

public interface Vehicle {
    public String getBrand();
    public String getModel();
    public int getProductionYear();
    public double dailyRentalPrice();
    public boolean equals(Object o);
    public String toString();
}
