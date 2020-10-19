/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;


/**
 *
 * @author Mohamed
 */
public class FloorMasterDAOMock implements FloorMasteryDAO {

 
    //Hard coded date so multiple test files are not created.
    public static String dateNowString = "10102020";
    public static final String DELIMITER = ",";
    public static String ORDER_FILE;
    public static String BACKUP_FILE;
    public static String dateSubString;

    public FloorMasterDAOMock(){
        
    }
    public FloorMasterDAOMock(String file){
        ORDER_FILE = file;
    }
    
    private Map<Integer, OrderFile> ordersMap = new HashMap<>();

    @Override
    public OrderFile addOrder(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        Set<Integer> keyset = ordersMap.keySet();
        int maxOrderNumber;
        try {
            loadOrders(dateNowString);
            maxOrderNumber = Collections.max(keyset) + 1;
        } catch (FloorMasteryDAOException | NoSuchElementException e) {
// This is to create a file if it doesn't exist otherwise it would crash trying to load orders.
            maxOrderNumber = 1;
        }

        orderFile.setOrderNumber(maxOrderNumber);
        OrderFile newOrder = ordersMap.put(maxOrderNumber, orderFile);
        writeOrders(dateNowString);
        return orderFile;
    }

    @Override
    public OrderFile editOrder(int orderNumber, OrderFile orderFile, String userDate) throws FloorMasteryDAOException {
        loadOrders(userDate);
        if (ordersMap.containsKey(orderNumber)) {
            OrderFile editOrder = ordersMap.put(orderNumber, orderFile);
            writeOrders(userDate);

            return editOrder;
        } else {
            throw new FloorMasteryDAOException("order does not exist");
        }
    }

    @Override
    public List<OrderFile> getAllOrders(String userDate) throws FloorMasteryDAOException {
        loadOrders(userDate);
        //need to compare all orders to certain date and return orders of that date      
        return new ArrayList<OrderFile>(ordersMap.values());

    }

    @Override
    public OrderFile removeOrder(int orderNumber, String userDate) throws FloorMasteryDAOException {
        loadOrders(userDate);
        OrderFile removeOrder = ordersMap.remove(orderNumber);
        writeOrders(userDate);
        return removeOrder;
    }

    @Override
    public void exportOrders() throws FloorMasteryDAOException {
        exportLoadOrders();

    }
     @Override
    public OrderFile getUserOrder(int orderNumber, String userDate) throws FloorMasteryDAOException {
        loadOrders(userDate);
        return ordersMap.get(orderNumber);
    }
//write

    private String marshallOrderFile(OrderFile orderFile) {
        String orderFileAsText = orderFile.getOrderNumber() + DELIMITER;
        orderFileAsText += orderFile.getCustomerName() + DELIMITER;
        orderFileAsText += orderFile.getState() + DELIMITER;
        orderFileAsText += orderFile.getTaxRate() + DELIMITER;
        orderFileAsText += orderFile.getProductType() + DELIMITER;
        orderFileAsText += orderFile.getArea() + DELIMITER;
        orderFileAsText += orderFile.getCostPerSquareFoot() + DELIMITER;
        orderFileAsText += orderFile.getLaborCostPerSquareFoot() + DELIMITER;
        orderFileAsText += orderFile.getMaterialCost() + DELIMITER;
        orderFileAsText += orderFile.getLaborCost() + DELIMITER;
        orderFileAsText += orderFile.getTax() + DELIMITER;
        orderFileAsText += orderFile.getTotal();

        return orderFileAsText;
    }

    //reads
    private OrderFile unmarshallOrderFile(String orderFileAsText) {
        String[] orderTokens = orderFileAsText.split(DELIMITER);
        String orderNumber = orderTokens[0];
        OrderFile orderFromFile = new OrderFile(Integer.parseInt(orderNumber));
        orderFromFile.setCustomerName(orderTokens[1]);
        orderFromFile.setState(orderTokens[2]);
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[3]));
        orderFromFile.setProductType(orderTokens[4]);
        orderFromFile.setArea(new BigDecimal(orderTokens[5]));
        orderFromFile.setCostPerSquareFoot(new BigDecimal(orderTokens[6]));
        orderFromFile.setLaborCostPerSquareFoot(new BigDecimal(orderTokens[7]));
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]));
        orderFromFile.setLaborCost(new BigDecimal(orderTokens[9]));
        orderFromFile.setTax(new BigDecimal(orderTokens[10]));
        orderFromFile.setTotal(new BigDecimal(orderTokens[11]));

        return orderFromFile;
    }

    private void loadOrders(String date) throws FloorMasteryDAOException {
        //date takes in any date
        //find out how to read every file in orders

        ORDER_FILE = ("TestDAOFolder/Orders_" + date + ".txt");
        Scanner sc;
        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FloorMasteryDAOException("could not load orders into memory", e);
        }
        String currentLine;
        OrderFile selectedFile;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            selectedFile = unmarshallOrderFile(currentLine);
            ordersMap.put(selectedFile.getOrderNumber(), selectedFile);
        }
        sc.close();
    }

    private void writeOrders(String date) throws FloorMasteryDAOException {
        //date takes in any date
        // write to export
        ORDER_FILE = ("TestDAOFolder/Orders_" + date + ".txt");
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE));
        } catch (IOException e) {
            throw new FloorMasteryDAOException("could not save orders data", e);
        }
        String orderFileAsText;
        List<OrderFile> ordersList = new ArrayList(ordersMap.values());
        for (OrderFile selectedFile : ordersList) {
            orderFileAsText = marshallOrderFile(selectedFile);
            out.println(orderFileAsText);
            out.flush();
        }
        out.close();
    }

    private void exportLoadOrders() throws FloorMasteryDAOException {
        File[] folder = new File("Orders").listFiles();
        BACKUP_FILE = ("TestDAOFolder/DataExport.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(BACKUP_FILE));
        } catch (IOException e) {
            throw new FloorMasteryDAOException("could not save orders data", e);
        }
        for (File file : folder) {
            dateSubString = file.toString().substring(14, 22);

            Scanner s;
            try {
                s = new Scanner(
                        new BufferedReader(
                                new FileReader(file)));
            } catch (FileNotFoundException e) {
                throw new FloorMasteryDAOException("could not load orders into memory", e);
            }
            String currentLine;
            OrderFile orderFileObject;
            while (s.hasNextLine()) {
                currentLine = s.nextLine();
                orderFileObject = unmarshallOrderFile(currentLine);
                ordersMap.put(orderFileObject.getOrderNumber(), orderFileObject);
            }
            exportWriteOrder(dateSubString, out);
            ordersMap.clear();
            s.close();

        }
        // closes exportWriterOrder after its completed print out all export Orders. 
        out.close();
    }

    private void exportWriteOrder(String date, PrintWriter out) throws FloorMasteryDAOException {
        String ExportFileAsText;
        List<OrderFile> ExportList = new ArrayList(ordersMap.values());
        
        for (OrderFile file : ExportList) {
            ExportFileAsText = marshallOrderFile(file);
            LocalDate dates = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
            String dateStrings = dates.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
            
            out.println(ExportFileAsText + "," + dateStrings);
            out.flush();
        }
    }
}
