package models;

import utils.OperatingSystemUtility;

public class Tablet extends ComputingDevice {
    private String operatingSystem = "Windows";

    public Tablet(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage, String operatingSystem) {
        super(modelName, price, manufacturer, id, processor, storage);
        setOperatingSystem(operatingSystem);
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        if (operatingSystem == null || operatingSystem.isEmpty()) {
            this.operatingSystem = "Windows";
        } else if (OperatingSystemUtility.isValidOperatingSystem(operatingSystem)) {
            this.operatingSystem = operatingSystem;
        }
    }
    public double getInsurancePremium(){
        return getPrice() * 0.01;
    }


    public String connectToInternet(){
        return "Connects to the internet via Wi-Fi";
    }
    @Override
    public String toString() {
        String ComputingDeviceString = super.toString(); // Get string representation of superclass
        ComputingDeviceString += ", Operating System: " + operatingSystem + ", Insurance Premium: €" + getInsurancePremium() + ", ConnectToInternet: " + connectToInternet();;
        return ComputingDeviceString;
    }
}