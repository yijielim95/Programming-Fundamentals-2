package models;

import utils.DisplayTypeUtility;
import utils.OperatingSystemUtility;

public class SmartWatch extends WearableDevices{
    private String displayType = "LCD";

    public SmartWatch(String modelName, double price, Manufacturer manufacturer, String id, String material, String size, String displayType){
        super(modelName,price,manufacturer,id,material,size);
        setDisplayType(displayType);
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        if(displayType == ""){
            this.displayType = "LCD";
        }
        else {
            if (DisplayTypeUtility.isValidDisplayType(displayType)) {
                this.displayType=displayType;
            }
        }
    }

    public double getInsurancePremium(){
        return getPrice() * 0.06;
    }


    public String connectToInternet(){
        return "Connects to the internet via bluetooth";
    }

    @Override
    public String toString() {
        String WearableDevicesString = super.toString(); // Get string representation of superclass
        WearableDevicesString += "Display Type: " + displayType + ", Insurance Premium: " + "€" + getInsurancePremium() +
                ", ConnectToInternet: " + connectToInternet();
        return WearableDevicesString;
    }
}
