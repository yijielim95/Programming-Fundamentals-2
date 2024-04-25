package models;

import utils.Utilities;

public abstract class WearableDevices extends Technology{
    private String material;
    private String size;

    public WearableDevices(String modelName, double price, Manufacturer manufacturer, String id, String material, String size){
        super(modelName, price, manufacturer, id);
        this.material = Utilities.truncateString(material,20);
        setSize(size);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if(Utilities.validStringlength(material,20)) {
            this.material = material;
        }
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        if (size != null){
            if(size.length()<=10){
                this.size=size;
            }
        }
    }
    public abstract double getInsurancePremium();
    public abstract String connectToInternet();

    @Override
    public String toString() {
        String technologyString = super.toString(); // Get string representation of superclass
        technologyString +=  "Material: " + this.material + ", Size: " + this.size;
        return technologyString;
    }
}
