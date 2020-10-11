/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import java.io.BufferedReader;
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
public class FloorMasteryDAOFileImpl implements FloorMasteryDAO {

    private static LocalDate dateNow = LocalDate.now();
    public static String dateNowString = DateTimeFormatter.ofPattern("MMddyyyy").format(dateNow);
    public static final String DELIMITER = ",";
    public static String ORDER_FILE;

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
        return newOrder;
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
//read

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

    //writes
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
        ORDER_FILE = ("Orders/Orders_" + date + ".txt");
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
        ORDER_FILE = ("Orders/Orders_" + date + ".txt");
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
}
