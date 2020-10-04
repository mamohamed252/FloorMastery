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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryDAOFileImpl implements FloorMasteryDAO {

    private static LocalDate date = LocalDate.now();
    public static String dateString = DateTimeFormatter.ofPattern("MMddyyyy").format(date);
    public static String ORDER_FILE = ("Orders_" + dateString + ".txt");
   public static File dir = new File( "C:\\Users\\Mohamed\\Desktop\\FloorMasterFiles\\FloorMasteryRepo\\FloorMastery\\Orders", ORDER_FILE);
    public static final String DELIMITER = ",";
    private Map<Integer, OrderFile> ordersMap = new HashMap<>();

    @Override
    public OrderFile addOrder(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        try {
            loadOrders();
        } catch (FloorMasteryDAOException e) {
// This is to create a file if it doesn't exist otherwise it would crash trying to load orders.
        }
        OrderFile newOrder = ordersMap.put(OrderNumber, orderFile);
        date = LocalDate.parse(orderFile.getDateinfo(), DateTimeFormatter.ofPattern("MMddyyyy"));
        writeOrders();
        return newOrder;
    }

    @Override
    public OrderFile editOrder(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderFile> getAllOrders(String date) throws FloorMasteryDAOException {
        loadOrders();
        return new ArrayList<OrderFile>(ordersMap.values());

    }

    @Override
    public OrderFile removeOrder(String orderNumber, String date) throws FloorMasteryDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String marshallOrderFile(OrderFile aOrderFile) {
        String orderFileAsText = aOrderFile.getOrderNumber() + DELIMITER;

        orderFileAsText += aOrderFile.getOrderNumber() + DELIMITER;
        orderFileAsText += aOrderFile.getCustomerName() + DELIMITER;
        orderFileAsText += aOrderFile.getState() + DELIMITER;
        orderFileAsText += aOrderFile.getTaxRate() + DELIMITER;
        orderFileAsText += aOrderFile.getProductType() + DELIMITER;
        orderFileAsText += aOrderFile.getArea() + DELIMITER;
        orderFileAsText += aOrderFile.getCostPerSquareFoot() + DELIMITER;
        orderFileAsText += aOrderFile.getLaborCostPerSquareFoot() + DELIMITER;
        orderFileAsText += aOrderFile.getMaterialCost() + DELIMITER;
        orderFileAsText += aOrderFile.getLaborCost() + DELIMITER;
        orderFileAsText += aOrderFile.getTax() + DELIMITER;
        orderFileAsText += aOrderFile.getTotal();

        return orderFileAsText;
    }

    private OrderFile unmarshallOrderFIlE(String orderFileAsText) {
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

    private void loadOrders() throws FloorMasteryDAOException {
        Scanner scanner;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException e) {
            throw new FloorMasteryDAOException(
                    "-_- Could not load order data into memory.", e);
        }
        String currentLine;
        OrderFile currentOrder;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentOrder = unmarshallOrderFIlE(currentLine);

            ordersMap.put(currentOrder.getOrderNumber(), currentOrder);
        }
        scanner.close();

    }

    private void writeOrders() throws FloorMasteryDAOException {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(dir));
        } catch (IOException e) {
            throw new FloorMasteryDAOException(
                    "Could not save order data", e);
        }
        String orderFileAsText;
        List<OrderFile> orderList = new ArrayList(ordersMap.values());
        for (OrderFile currentOrder : orderList) {
            orderFileAsText = marshallOrderFile(currentOrder);
            out.print(orderFileAsText);
            out.flush();
        }
        out.close();
    }
}
