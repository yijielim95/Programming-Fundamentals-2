package models;

import utils.Utilities;

public abstract class ComputingDevice extends Technology {
    private int storage;
    private String processor;
    public ComputingDevice(String modelName, double price, Manufacturer manufacturer, String id, String processor, int storage){
        super(modelName, price, manufacturer, id);
        setStorage(storage);
        this.processor = Utilities.truncateString(processor,20);
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        if(Utilities.validStringlength(processor,20)) {
            this.processor=processor;
        };
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        if(storage % 8 == 0){
            if(storage >= 8 && storage <= 128){
                this.storage = storage;
            }
        }
    }

    public abstract double getInsurancePremium();

    public abstract String connectToInternet();

    @Override
    public String toString() {
        String technologyString = super.toString(); // Get string representation of superclass
        technologyString +=  "Processor: " + processor + ", Storage: " + storage + "GB";
        return technologyString;
    }
}
