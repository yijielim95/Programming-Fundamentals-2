package main;

import controllers.ManufacturerAPI;
import controllers.TechnologyDeviceAPI;

import models.*;
import utils.ScannerInput;
import utils.Utilities;

import java.io.File;

public class Driver {
    private TechnologyDeviceAPI techAPI;
    private ManufacturerAPI manufacturerAPI;

    public static void main(String[] args) throws Exception {
        new Driver().start();
    }

    public void start() {
        manufacturerAPI = new ManufacturerAPI(new File("manufacturers.xml"));
        techAPI = new TechnologyDeviceAPI(new File("technologyDevices.xml"));
        //TODO - construct fields
        //TODO - load all data once the serializers are set up
        runMainMenu();
    }

    private int mainMenu() {
        System.out.println("""
                 -------Technology Store---------
                |  1) Manufacturer CRUD MENU     |
                |  2) Technology  CRUD MENU      |
                |  3) Reports MENU               |
                |--------------------------------|
                |  4) Search Manufacturers       |
                |  5) Search Technology Devices  |  
                |--------------------------------|
                |  6) Save all                   |
                |  7) Load all                   |
                |--------------------------------|
                |  0) Exit                       |
                 --------------------------------""");
        return ScannerInput.readNextInt("==>> ");
    }

    //// search todo by different criteria i.e. look at the list methods and give options based on that.
// sort todo (and give a list of options - not a recurring menu thou)
    private void runMainMenu() {
        int option = mainMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runManufacturerMenu();
                case 2 -> runTechnologyMenu();
                case 3 -> runReportsMenu();
                case 4 -> runManufacturerSearchMenu();
                case 5 -> runTechnologySearchMenu();
                case 6 -> save();
                case 7 -> load();
                //TODO - Add options
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = mainMenu();
        }
        exitApp();
    }

    private void exitApp() {
        //TODO - save all the data entered
        System.out.println("Exiting....");
        System.exit(0);
    }


    //----------------------
    //  Manufacturer Menu Items
    //----------------------
    private int manufacturerMenu() {
        System.out.println("""
                 --------Manufacturer Menu---------
                |  1) Add a manufacturer           |
                |  2) Delete a manufacturer        |
                |  3) Update manufacturer details  |
                |  4) List all manufacturers       |
                |  5) Find a manufacturer          |
                |  0) Return to main menu          |
                 ----------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    private void runManufacturerMenu() {
        int option = manufacturerMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> addManufacturer();
                case 2 -> deleteManufacturer();
                case 3 -> updateManufacturer();
                case 4 -> System.out.println(manufacturerAPI.listManufacturers());
                case 5 -> findManufacturer();
                case 6 -> listByManufacturerName();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = manufacturerMenu();
        }
    }

    private void addManufacturer() {
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
        int manufacturerNumEmployees = ScannerInput.readNextInt("Please enter the number of employees: ");

        if (manufacturerAPI.addManufacturer(new Manufacturer(manufacturerName, manufacturerNumEmployees))) {
            System.out.println("Add successful");
        } else {
            System.out.println("Add not successful");
        }
    }

    private void deleteManufacturer() {
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer name: ");
        if (manufacturerAPI.removeManufacturerByName(manufacturerName) != null) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete not successful");
        }
    }

    private void updateManufacturer() {
        Manufacturer manufacturer = getManufacturerByName();
        if (manufacturer != null) {
            int numEmployees = ScannerInput.readNextInt("Please enter number of Employees: ");
            if (manufacturerAPI.updateManufacturer(manufacturer.getManufacturerName(), numEmployees))
                System.out.println("Number of Employees Updated");
            else
                System.out.println("Number of Employees NOT Updated");
        } else
            System.out.println("Manufacturer name is NOT valid");
    }

    private void findManufacturer() {
        Manufacturer developer = getManufacturerByName();
        if (developer == null) {
            System.out.println("No such manufacturer exists");
        } else {
            System.out.println(developer);
        }
    }

    private void listByManufacturerName() {
        String manufacturer = ScannerInput.readNextLine("Enter the manufacturer's name:  ");

        System.out.println(manufacturerAPI.listAllByManufacturerName(manufacturer));
    }


    //---------------------
    //  Tech Store Menu
    //---------------------

    private int techAPIMenu() {
        System.out.println("""
                 -----Technology Store Menu-----
                | 1) Add a Tech Device           |
                | 2) Delete a Tech Device        |
                | 3) List all Tech Devices       |
                | 4) Update Tech Device          |
                | 0) Return to main menu         |
                 ----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }
    private void runTechnologyMenu() {
        int option = techAPIMenu();
        while (option != 0) {
            switch (option) {
                case 1  -> addTechnologyDevice();
                case 2  -> deleteTechnologyDevice();
                case 3  -> showTechnologyDevices();
                case 4  -> updateTechnologyDevice();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = techAPIMenu();
        }
    }

    private void addTechnologyDevice(){

        boolean isAdded = false;

        int option = ScannerInput.readNextInt("""
                    ---------------------------
                    |   1) Add a Tablet       |
                    |   2) Add a Smart Watch  |
                    |   3) Add a Smart Band   |
                    ---------------------------
                    ==>> """);

        switch (option) {
            case 1 -> {
                String modelName = ScannerInput.readNextLine("Enter the Tablet Name: ");
                double price = ScannerInput.readNextDouble("Enter the Tablet Price: ");
                String manufacturerName = ScannerInput.readNextLine("Enter the Manufacturer Name: ");
                String id = ScannerInput.readNextLine("Enter the Tablet id: ");
                String processor = ScannerInput.readNextLine("Enter the Tablet Processor: ");
                int storage = ScannerInput.readNextInt("Enter the Tablet Storage: ");
                String operatingSystem = ScannerInput.readNextLine("Enter the Tablet Operating System: ");
                Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(manufacturerName);
                isAdded = techAPI.addTechnologyDevice(new Tablet(modelName,price,manufacturer,id,processor,storage,operatingSystem));
            }
            case 2 -> {
                String modelName = ScannerInput.readNextLine("Enter the Smart Watch Name: ");
                double price = ScannerInput.readNextDouble("Enter the Smart Watch Price: ");
                String manufacturerName = ScannerInput.readNextLine("Enter the Manufacturer Name: ");
                String id = ScannerInput.readNextLine("Enter the Smart Watch id: ");
                String material = ScannerInput.readNextLine("Enter the Smart Watch Material: ");
                String size = ScannerInput.readNextLine("Enter the Smart Watch Size: ");
                String displayType = ScannerInput.readNextLine("Enter the Smart Watch Display Type: ");
                Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(manufacturerName);
                isAdded = techAPI.addTechnologyDevice(new SmartWatch(modelName,price,manufacturer,id,material,size,displayType));
            }
            case 3 -> {
                String modelName = ScannerInput.readNextLine("Enter the Smart Band Name: ");
                double price = ScannerInput.readNextDouble("Enter the Smart Band Price: ");
                String manufacturerName = ScannerInput.readNextLine("Enter the Manufacturer Name: ");
                String id = ScannerInput.readNextLine("Enter the Smart Band id: ");
                String material = ScannerInput.readNextLine("Enter the Smart Band Material: ");
                String size = ScannerInput.readNextLine("Enter the Smart Band Size: ");
                char heartRateMonitorr = ScannerInput.readNextChar("Enter the Heart Rate Monitor(y/n): ");
                Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(manufacturerName);
                boolean heartRateMonitor = Utilities.YNtoBoolean(heartRateMonitorr);
                isAdded = techAPI.addTechnologyDevice(new SmartBand(modelName,price,manufacturer,id,material,size,heartRateMonitor));
            }
            default -> System.out.println("Invalid option entered: " + option);
        }

        if (isAdded){
            System.out.println("Device Added Successfully");
        }
        else{
            System.out.println("No Device Added");
        }
    }

    private void deleteTechnologyDevice(){
        showTechnologyDevices();
        if (techAPI.numberOfTechnologyDevices() > 0){
            String idToDelete = ScannerInput.readNextLine("Enter the index of the device to delete ==> ");
            Technology deviceToDelete = techAPI.deleteTechnologyById(idToDelete);
            if (deviceToDelete != null){
                System.out.println("Delete Successful");
            }
            else{
                System.out.println("Delete NOT Successful");
            }
        }
    }

    private void updateTechnologyDevice(){
        if (techAPI.numberOfTechnologyDevices() > 0) {
            boolean isUpdated = false;

            int option = ScannerInput.readNextInt("""
                    ---------------------------
                    |   1) Update a Tablet       |
                    |   2) Update a Smart Band   |
                    |   3) Update a Smart Watch  |
                    ---------------------------
                    ==>>""");

            switch (option) {
                case 1 -> {
                    showTablet();
                    if (techAPI.numberOfTablets() > 0) {
                        int tabletIndex = ScannerInput.readNextInt("Enter the index of the tablet to update ==> ");
                        if (techAPI.isValidIndex(tabletIndex)) {
                            String modelName = ScannerInput.readNextLine("Enter the Tablet Name: ");
                            double price = ScannerInput.readNextDouble("Enter the Tablet Price: ");
                            String manufacturerName = ScannerInput.readNextLine("Enter the Manufacturer Name: ");
                            String id = ScannerInput.readNextLine("Enter the Tablet id: ");
                            String processor = ScannerInput.readNextLine("Enter the Tablet Processor: ");
                            int storage = ScannerInput.readNextInt("Enter the Tablet Storage: ");
                            String operatingSystem = ScannerInput.readNextLine("Enter the Tablet Operating System: ");
                            Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(manufacturerName);
                            Tablet updatedDetails = new Tablet(modelName, price, manufacturer, id, processor, storage, operatingSystem);
                            isUpdated = techAPI.updateTablet(id, updatedDetails);
                        }
                    }
                }
                case 2 -> {
                    showSmartBand();
                    if (techAPI.numberOfSmartBands() > 0) {
                        int smartBandIndex = ScannerInput.readNextInt("Enter the index of the smart band to update ==> ");
                        if (techAPI.isValidIndex(smartBandIndex)) {
                            String modelName = ScannerInput.readNextLine("Enter the Smart Band Name: ");
                            double price = ScannerInput.readNextDouble("Enter the Smart Band Price: ");
                            String manufacturerName = ScannerInput.readNextLine("Enter the Manufacturer Name: ");
                            String id = ScannerInput.readNextLine("Enter the Smart Band id: ");
                            String material = ScannerInput.readNextLine("Enter the Smart Band Material: ");
                            String size = ScannerInput.readNextLine("Enter the Smart Band Size: ");
                            char heartRateMonitorr = ScannerInput.readNextChar("Enter the Heart Rate Monitor(y/n): ");
                            Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(manufacturerName);
                            boolean heartRateMonitor = Utilities.YNtoBoolean(heartRateMonitorr);
                            SmartBand updatedDetails = new SmartBand(modelName, price,manufacturer,id,material,size,heartRateMonitor);
                            isUpdated = techAPI.updateSmartBand(id, updatedDetails);
                        }
                    }
                }
                case 3 -> {
                    showSmartWatch();
                    if (techAPI.numberOfSmartWatch() > 0) {
                        int smartWatchIndex = ScannerInput.readNextInt("Enter the index of the smart watch to update ==> ");
                        if (techAPI.isValidIndex(smartWatchIndex)) {
                            String modelName = ScannerInput.readNextLine("Enter the Smart Watch Name: ");
                            double price = ScannerInput.readNextDouble("Enter the Smart Watch Price: ");
                            String manufacturerName = ScannerInput.readNextLine("Enter the Manufacturer Name: ");
                            String id = ScannerInput.readNextLine("Enter the Smart Watch id: ");
                            String material = ScannerInput.readNextLine("Enter the Smart Watch Material: ");
                            String size = ScannerInput.readNextLine("Enter the Smart Watch Size: ");
                            String displayType = ScannerInput.readNextLine("Enter the Smart Watch Display Type: ");
                            Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(manufacturerName);
                            SmartWatch updatedDetails = new SmartWatch(modelName, price,manufacturer,id,material,size,displayType);
                            isUpdated = techAPI.updateSmartWatch(id, updatedDetails);
                        }
                    }
                }
                default -> System.out.println("Invalid option entered: " + option);
            }

            if (isUpdated) {
                System.out.println("Device Updated Successfully");
            } else {
                System.out.println("No device Updated");
            }
        }
        else{
            System.out.println("No devices added yet");
        }
    }

    //---------------------
    //  Reports Menu
    //---------------------
    private int reportsMenu() {
        System.out.println("""
                 --------Reports Menu ---------
                | 1) Manufacturers Overview    |
                | 2) Technology Overview       |
                | 0) Return to main menu       |
                  -----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    public void runReportsMenu() {
        int option = reportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> runManufacturerReports();
                case 2 -> runTechnologyReports();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = reportsMenu();
        }
    }

    private int manufacturerReportsMenu() {
        System.out.println("""
                 ---------- Manufacturers Reports Menu  -------------
                | 1) List Manufacturers                              |
                | 2) List Manufacturers by a given name              |
                | 3) List Technology Device by a chosen manufacturer |
                | 0) Return to main menu                             |
                  ---------------------------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    public void runManufacturerReports() {
        int option = manufacturerReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> System.out.println(manufacturerAPI.listManufacturers());
                case 2 -> showManufacturerFromAGivenName();
                case 3 -> showDevicesByManufacturer();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = manufacturerReportsMenu();
        }
    }

    private void showManufacturerFromAGivenName(){
        System.out.println("Enter the manufacturer's name to search:");
        String name = ScannerInput.readNextLine("Manufacturer Name: ");
        Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(name);

        if (manufacturer != null) {
            System.out.println("Manufacturer details:");
            System.out.println(manufacturer);
        } else {
            System.out.println("No manufacturer found with the name: " + name);
        }
    }

    private void showDevicesByManufacturer() {
        System.out.println("Enter the manufacturer's name to list all devices from:");
        String manufacturerName = ScannerInput.readNextLine("Manufacturer Name: ");
        Manufacturer manufacturer = manufacturerAPI.getManufacturerByName(manufacturerName);

        if (manufacturer != null) {
            System.out.println("Listing all devices from manufacturer: " + manufacturer.getManufacturerName());
            String result = techAPI.listAllTechDevicesByChosenManufacturer(manufacturer);
            System.out.println(result);
        } else {
            System.out.println("No manufacturer found with the name: " + manufacturerName);
        }
    }

    private int technologyReportsMenu() {
        System.out.println("""
                 ---------- Technology Reports Menu  ----------------
                | 1) List all technology                             |
                | 2) List all SmartBands                             |
                | 3) List all Smart watch                            |
                | 4) List all Tablets                                |
                | 5) List all devices above a price                  |
                | 6) List all devices below a price                  |
                | 7) List all tablets by operating system            |
                | 0) Return to main menu                             |
                  ---------------------------------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    public void runTechnologyReports() {
        int option = technologyReportsMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> showTechnologyDevices();
                case 2 -> showSmartBand();
                case 3 -> showSmartWatch();
                case 4 -> showTablet();
                case 5 -> showTechnologyAbovePrice();
                case 6 -> showTechnologyBelowPrice();
                case 7 -> showTabletsByOperatingSystem();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = technologyReportsMenu();
        }
    }
    private void showTechnologyDevices(){
        System.out.println("List of All Devices are:");
        System.out.println(techAPI.listAllTechnologyDevices());
    }

    private void showTablet(){
        System.out.println("List of Tablets are:");
        System.out.println(techAPI.listAllTablets());
    }

    //print the photo posts in newsfeed i.e. array list.
    private void showSmartWatch(){
        System.out.println("List of Smart Watchs are:");
        System.out.println(techAPI.listAllSmartWatchs());
    }

    //print the photo posts in newsfeed i.e. array list.
    private void showSmartBand(){
        System.out.println("List of Smart Bands are:");
        System.out.println(techAPI.listAllSmartBands());
    }

    private void showTechnologyAbovePrice() {
        System.out.println("Enter the price: ");
        double price = ScannerInput.readNextDouble("Price: ");

        System.out.println("Listing all technology devices priced above " + price + ":");
        String result = techAPI.listAllTechnologyAbovePrice(price);
        System.out.println(result);
    }

    private void showTechnologyBelowPrice() {
        System.out.println("Enter the price: ");
        double price = ScannerInput.readNextDouble("Price: ");

        System.out.println("Listing all technology devices priced above " + price + ":");
        String result = techAPI.listAllTechnologyBelowPrice(price);
        System.out.println(result);
    }

    private void showTabletsByOperatingSystem() {
        System.out.println("Enter the operating system to filter by:");
        String operatingSystem = ScannerInput.readNextLine("Operating System: ");

        System.out.println("Listing all tablets with operating system: " + operatingSystem);
        String result = techAPI.listAllTabletsByOperatingSystem(operatingSystem);
        System.out.println(result);
    }

    //---------------------
    //  Search Menu
    //---------------------
    private int searchManuMenu() {
        System.out.println("""
                 -------- Manufacturer Search Menu ---------
                | 1) Search Manufacturer By Index           |
                | 2) Search Manufacturer By Name            |
                | 0) Return to main menu                    |
                  -----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }
    public void runManufacturerSearchMenu() {
        int option = searchManuMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> findManufacturerByIndex();
                case 2 -> findManufacturerByName();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = searchManuMenu();
        }
    }

    private void findManufacturerByIndex(){
        Manufacturer developer = getManufacturerByIndex();
        if(developer == null){
            System.out.println("No such manufacturer exists");
        }else{
            System.out.println(developer);
        }
    }

    private void findManufacturerByName() {
        Manufacturer developer = getManufacturerByName();
        if (developer == null) {
            System.out.println("No such manufacturer exists");
        } else {
            System.out.println(developer);
        }
    }

    private Manufacturer getManufacturerByName() {
        String manufacturerName = ScannerInput.readNextLine("Please enter the manufacturer's name: ");
        if (manufacturerAPI.isValidManufacturer(manufacturerName)) {
            return manufacturerAPI.getManufacturerByName(manufacturerName);
        } else {
            return null;
        }
    }

    private Manufacturer getManufacturerByIndex(){
        int manufacturerIndex = ScannerInput.readNextInt("Please enter the manufacturer's id: ");
        Manufacturer foundManufacturer = manufacturerAPI.getManufacturerByIndex(manufacturerIndex);

        if (foundManufacturer != null) {
            System.out.println("Found Manufacturer: " +  foundManufacturer.getManufacturerName());
        } else {
            System.out.println("There is no manufacturer found by this id");
        }
        return foundManufacturer;
    }

    private int searchTechMenu() {
        System.out.println("""
                 -------- Technology Search Menu ---------
                | 1) Search Device By Index               |
                | 2) Search Device By Id                  |
                | 0) Return to main menu                  |
                  -----------------------------""");
        return ScannerInput.readNextInt("==>>");
    }

    public void runTechnologySearchMenu() {
        int option = searchTechMenu();
        while (option != 0) {
            switch (option) {
                case 1 -> findDeviceByIndex();
                case 2 -> findDeviceByID();
                default -> System.out.println("Invalid option entered" + option);
            }
            ScannerInput.readNextLine("\n Press the enter key to continue");
            option = searchTechMenu();
        }
    }

    private void findDeviceByID(){
        Technology device = getDeviceByID();
        if(device == null){
            System.out.println("No such device exists");
        }else{
            System.out.println(device);
        }
    }
    private void findDeviceByIndex(){
        Technology device = getDevicesByIndex();
        if(device == null){
            System.out.println("No such device exists");
        }else{
            System.out.println(device);
        }
    }

    private Technology getDeviceByID() {
        String deviceID = ScannerInput.readNextLine("Please enter the device's id: ");
        if (techAPI.isValidId(deviceID)) {
            return techAPI.getTechnologyDeviceById(deviceID);
        } else {
            return null;
        }
    }

    private Technology getDevicesByIndex(){
        int technologyIndex = ScannerInput.readNextInt("Please enter the device's id: ");
        Technology foundDevices = techAPI.getTechnologyByIndex(technologyIndex);

        if (foundDevices != null) {
            System.out.println("Found Device: " +  foundDevices.getModelName());
        } else {
            System.out.println("There is no device found by this id");
        }
        return foundDevices;
    }
    //---------------------
    //  Persistence Menu
    //---------------------

    private void load(){
        try{
            manufacturerAPI.load();
            techAPI.load();
        }catch (Exception e){
            System.err.println("Error reading from file: " + e);
        }
    }

    private void save() {
        try {
            manufacturerAPI.save();
            techAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }
}