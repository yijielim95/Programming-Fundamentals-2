package models;

public class SmartBand extends WearableDevices{
    private boolean heartRateMonitor;

    public SmartBand(String modelName, double price, Manufacturer manufacturer, String id, String material, String size,Boolean heartRateMonitor){
        super(modelName, price, manufacturer, id, material, size);
        this.heartRateMonitor = heartRateMonitor;
    }

    public boolean isHeartRateMonitor() {
        return heartRateMonitor;
    }

    public void setHeartRateMonitor(boolean heartRateMonitor) {
        this.heartRateMonitor = heartRateMonitor;
    }

    public double getInsurancePremium(){
        return getPrice() * 0.07;
    }


    public String connectToInternet(){
        return "Connects to the internet via Companion App";
    }

    @Override
    public String toString() {
        String WearableDevicesString = super.toString(); // Get string representation of superclass
        WearableDevicesString += "Heart Rate Monitor: " + heartRateMonitor + ", Insurance Premium: " + "€" + getInsurancePremium() +
                ", ConnectToInternet: " + connectToInternet();
        return WearableDevicesString;
    }
}
