package models;

import utils.Utilities;

public abstract class Technology{
    private Manufacturer manufacturer;
    private double price = 20;
    private String id = "unknown";
    private String modelName;

    public Technology(String modelName, double price, Manufacturer manufacturer, String id){
        setManufacturer(manufacturer);
        setPrice(price);
        if(id != null){
            if (id.length() <= 10){
                this.id = id;
            }
        }
        this.modelName = Utilities.truncateString(modelName, 30);
    }
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price >= 20){
            this.price = price;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id.length() <= 10) {
            this.id = id;
        }
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if(Utilities.validStringlength(modelName,30)) {
            this.modelName = modelName;
        }
    }

    public abstract double getInsurancePremium();


    public abstract String connectToInternet();

    @Override
    public String toString() {
        return "Model: " + modelName + ", Price: " + "€" + price + ", Manufacturer Details: " + manufacturer.toString() + ", ID: " + id;
    }


}



