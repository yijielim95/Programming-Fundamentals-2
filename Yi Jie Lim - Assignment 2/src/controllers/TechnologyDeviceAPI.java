package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.*;
import utils.ISerializer;
import utils.Utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static utils.OperatingSystemUtility.isValidOperatingSystem;
import static utils.Utilities.isValidIndex;


//TODO - ensure that this class implements iSerializer
public class TechnologyDeviceAPI implements ISerializer {
    //TODO - create 2 fields
    private File file;
    private List<Technology> technologyList = new ArrayList<>();


    //TODO - create constructor
    public TechnologyDeviceAPI(File file) {
        this.file = file;
    }

    //TODO - CRUD Methods
    public boolean addTechnologyDevice(Technology technologyDevice) {
        if (isValidId(technologyDevice.getId())) {
            return false;
        }
        return technologyList.add(technologyDevice);
    }

    public Technology deleteTechnologyByIndex(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return technologyList.remove(indexToDelete);
        }
        return null;
    }

    public Technology deleteTechnologyById(String technologyDeviceId) {
        int index = retrieveTechnologyDeviceIndex(technologyDeviceId);
        if (index != -1) {
            return technologyList.remove(index);
        }
        return null;
    }

    public Technology getTechnologyByIndex(int indexToGet) {
        if (Utilities.isValidIndex(technologyList, indexToGet)) {
            return technologyList.get(indexToGet);
        }
        return null;
    }

    public Technology getTechnologyDeviceById(String technologyDeviceId) {
        int index = retrieveTechnologyDeviceIndex(technologyDeviceId);
        if (index != -1) {
            return technologyList.get(index);
        }
        return null;
    }

    //TODO - Reporting Methods
    public String listAllTechnologyDevices() {
        String str = "";

        for (Technology device : technologyList) {
            str += technologyList.indexOf(device) + ": " + device + "\n";
        }

        if (str.isEmpty()) {
            return "no technology devices";
        } else {
            return str;
        }
    }

    public String listAllSmartBands() {
        String listSmartBand = "";

        for (Technology device : technologyList) {
            if (device instanceof SmartBand) {
                listSmartBand += technologyList.indexOf(device) + ": " + device + "\n";
            }
        }

        if (listSmartBand.isEmpty()) {
            return "No Smart Band";
        } else {
            return listSmartBand;
        }
    }

    public String listAllSmartWatchs() {
        String listSmartWatch = "";

        for (Technology device : technologyList) {
            if (device instanceof SmartWatch) {
                listSmartWatch += technologyList.indexOf(device) + ": " + device + "\n";
            }
        }

        if (listSmartWatch.isEmpty()) {
            return "No Smart Watch";
        } else {
            return listSmartWatch;
        }
    }

    public String listAllTablets() {
        String listTablet = "";

        for (Technology device : technologyList) {
            if (device instanceof Tablet) {
                listTablet += technologyList.indexOf(device) + ": " + device + "\n";
            }
        }

        if (listTablet.isEmpty()) {
            return "No Tablet";
        } else {
            return listTablet;
        }
    }

    public String listAllTechnologyAbovePrice(double price) {
        if (technologyList.isEmpty()) {
            return "No Technology Devices";
        }
        StringBuilder str = new StringBuilder();
        for (Technology technologyDevice : technologyList) {
            if (technologyDevice.getPrice() >= price) {
                str.append(technologyList.indexOf(technologyDevice)).append(": ").append(technologyDevice).append("\n");
            }
        }
        if (str.length() == 0) {
            return "No technology more expensive than: " + price;
        } else {
            return str.toString();
        }
    }

    public String listAllTechnologyBelowPrice(double price) {
        if (technologyList.isEmpty()) {
            return "No Technology Devices";
        }
        StringBuilder str = new StringBuilder();
        for (Technology technologyDevice : technologyList) {
            if (technologyDevice.getPrice() <= price) {
                str.append(technologyList.indexOf(technologyDevice)).append(": ").append(technologyDevice).append("\n");
            }
        }
        if (str.length() == 0) {
            return "No technology devices are cheaper than: " + price;
        } else {
            return str.toString();
        }
    }

    public String listAllTechDevicesByChosenManufacturer(Manufacturer manufacturer) {
        if (technologyList.isEmpty()) {
            return "No Technology Devices";
        }
        StringBuilder str = new StringBuilder();
        for (Technology technologyDevice : technologyList) {
            if (technologyDevice.getManufacturer().equals(manufacturer)) {
                str.append(technologyList.indexOf(technologyDevice)).append(": ").append(technologyDevice).append("\n");
            }
        }
        if (str.length() == 0) {
            return "No technology devices manufactured by: " + manufacturer.getManufacturerName();
        } else {
            return str.toString();
        }
    }

    public String listAllTabletsByOperatingSystem(String operatingSystem) {
        if (!isValidOperatingSystem(operatingSystem)) {
            return "Invalid Operating System";
        }
        StringBuilder str = new StringBuilder();
        for (Technology technologyDevice : technologyList) {
            if (technologyDevice instanceof Tablet) {
                Tablet tablet = (Tablet) technologyDevice;
                if (tablet.getOperatingSystem().equalsIgnoreCase(operatingSystem)) {
                    str.append(technologyList.indexOf(tablet)).append(": ").append(tablet).append("\n");
                }
            }
        }
        if (str.length() == 0) {
            return "No tablet with the operating system " + operatingSystem;
        } else {
            return str.toString();
        }
    }
    //TODO - Number methods
    public int numberOfTechnologyDevices() {
        return technologyList.size();
    }

    public int numberOfTablets() {
        int number = 0;
        for (Technology technologyDevice : technologyList) {
            if (technologyDevice instanceof Tablet) {
                number++;
            }
        }
        return number;
    }

    public int numberOfSmartBands() {
        int number = 0;
        for (Technology technologyDevice : technologyList) {
            if (technologyDevice instanceof SmartBand) {
                number++;
            }
        }
        return number;
    }

    public int numberOfSmartWatch() {
        int number = 0;
        for (Technology technologyDevice : technologyList) {
            if (technologyDevice instanceof SmartWatch) {
                number++;
            }
        }
        return number;
    }

    public int numberOfTechnologyByChosenManufacturer(Manufacturer manufacturer){
        int number = 0;
        for (Technology technologyDevice : technologyList){
            if (technologyDevice.getManufacturer().equals(manufacturer)){
                number ++;
            }
        }
        return number;
    }
    // TODO UPDATE methods
    public boolean updateTablet(String id, Tablet updatedDetails){
        int foundIndex = retrieveTechnologyDeviceIndex(id);

        if((foundIndex != -1) && (technologyList.get(foundIndex) instanceof Tablet)){
            technologyList.set(foundIndex, updatedDetails);
            return true;
        }
        return false;
    }

    public boolean updateSmartWatch(String id, SmartWatch updatedDetails){
        int foundIndex = retrieveTechnologyDeviceIndex(id);

        if((foundIndex != -1) && (technologyList.get(foundIndex) instanceof SmartWatch)){
            technologyList.set(foundIndex, updatedDetails);
            return true;
        }
        return false;
    }

    public boolean updateSmartBand(String id, SmartBand updatedDetails){
        int foundIndex = retrieveTechnologyDeviceIndex(id);

        if((foundIndex != -1) && (technologyList.get(foundIndex) instanceof SmartBand)){
            technologyList.set(foundIndex, updatedDetails);
            return true;
        }
        return false;
    }
    //TODO - sort methods
    private void swapTechnology(List<Technology> technologyList, int i, int j){
        Technology temp = technologyList.get(i);
        technologyList.set(i, technologyList.get(j));
        technologyList.set(j, temp);
    }

    public void sortByPriceDescending(){
        int n = technologyList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (technologyList.get(j).getPrice() < technologyList.get(j + 1).getPrice()) {
                    swapTechnology(technologyList, j, j + 1);
                }
            }
        }
    }

    private void sortByPriceAscending(){
        int n = technologyList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (technologyList.get(j).getPrice() > technologyList.get(j + 1).getPrice()) {
                    swapTechnology(technologyList, j, j + 1);
                }
            }
        }
    }

    // TODO Validation methods
    public boolean isValidId(String technologyDeviceID){
        for (Technology technologyDevice : technologyList){
            if (technologyDevice.getId().equalsIgnoreCase(technologyDeviceID)){
                return true;
            }
        }
        return false;
    }

    public int retrieveTechnologyDeviceIndex(String technologyDeviceID){
        for (Technology technologyDevice : technologyList){
            if (isValidId(technologyDeviceID)){
                return technologyList.indexOf(technologyDevice);
            }
        }
        return -1;
    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < technologyList.size());
    }
    // TODO Persistence methods
    public String fileName(){
        return String.valueOf(file);
    }

    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(fileName()));
        out.writeObject(technologyList);
        out.close();
    }

    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{ComputingDevice.class, SmartBand.class, Manufacturer.class, SmartWatch.class, Tablet.class, Technology.class, WearableDevices.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(file));
        technologyList = (List<Technology>) in.readObject();
        in.close();
    }


}
