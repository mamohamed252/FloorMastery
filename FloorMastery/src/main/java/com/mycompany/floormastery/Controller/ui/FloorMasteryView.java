/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller.ui;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import com.mycompany.floormastery.DAO.FloorMasteryDAO;
import com.mycompany.floormastery.DAO.FloorMasteryDAOException;
import com.mycompany.floormastery.DAO.FloorMasteryDAOFileImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryView {

    public FloorMasteryView(UserIO io) {
        this.io = io;
    }
    private UserIO io;

    public int printMenuAndGetSelection() {
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Quit");

        return io.readInt("Please select from the above choices", 1, 6);
    }

    public OrderFile getNewOrderInfo() throws FloorMasteryDAOException {
        OrderFile currentOrder;
        try {
            String name = io.readString("Please enter name");
            String state = io.readString("Please enter state abbreviation");
            String productType = io.readString("Please enter product type starting with capital letter");
            BigDecimal area = io.readBigDecimal("Please enter area in sqFT");
            //add counter for order number or global counter that checks highest number in file
            currentOrder = new OrderFile(1);
            //currentOrder.setDate(enteredDate);
            currentOrder.setCustomerName(name);
            currentOrder.setState(state.toUpperCase());
            currentOrder.setProductType(productType.substring(0,1).toUpperCase() + productType.substring(1).toLowerCase());
            currentOrder.setArea(area);

            if (currentOrder.getCustomerName().equals("")) {
                throw new FloorMasteryDAOException("Must enter a Character for name");
            } else if (currentOrder.getArea().compareTo(new BigDecimal("100")) == -1) {
                throw new FloorMasteryDAOException("Area sqFT must be 100 sqFT or more");
            } else {

            }
        } catch (NumberFormatException e) {
            throw new FloorMasteryDAOException("Please enter digit for Area sqFT");
        }
        return currentOrder;
    }

    public OrderFile getEditOrderInfo(int orderNumber) throws FloorMasteryDAOException {
        String name = io.readString("Please enter name");
        String state = io.readString("Please enter state abbreviation");
        String productType = io.readString("Please enter product type starting with capital letter");
        BigDecimal area = io.readBigDecimal("Please enter area in sqFt");

        System.out.println(name);
        System.out.println(state);
        System.out.println(productType);
        System.out.println(area);
        String confirmToEdit = io.readString("Would you like to confirm to edit? Y/N");
        if (confirmToEdit.equals("Y".toLowerCase()) || confirmToEdit.equals("yes".toLowerCase())) {
            OrderFile currentOrder = new OrderFile(orderNumber);
            //currentOrder.setDate(enteredDate);

            currentOrder.setCustomerName(name);
            currentOrder.setState(state.toUpperCase());
            currentOrder.setProductType(productType.substring(0,1).toUpperCase() + productType.substring(1).toLowerCase());
            currentOrder.setArea(area);
            System.out.println("Thank you for editing order");
            return currentOrder;
        } else {
            throw new FloorMasteryDAOException("Thank you, edit has not been made");
        }

    }
//add counter for order number or global counter that checks highest number in file

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayRemoveResult(OrderFile orderFileRecord) {
        if (orderFileRecord != null) {
            io.print("Order successfully removed.");
        } else {
            io.print("No such order.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveOrderBanner() {
        io.print("=== Remove Order ===");
    }

    public String getDisplayOrdersDate() {
        return io.readString("Please enter date: MMddyyyy format: ");

    }

    public void displayOrdersList(List<OrderFile> orderList) {
        for (OrderFile currentOrder : orderList) {
            String orderInfo = String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                    currentOrder.getOrderNumber(),
                    currentOrder.getCustomerName(),
                    currentOrder.getState(),
                    currentOrder.getTaxRate(),
                    currentOrder.getProductType(),
                    currentOrder.getArea(),
                    currentOrder.getCostPerSquareFoot(),
                    currentOrder.getLaborCostPerSquareFoot(),
                    currentOrder.getMaterialCost(),
                    currentOrder.getLaborCost(),
                    currentOrder.getTax(),
                    currentOrder.getTotal());
            io.print(orderInfo);
        }
        io.readString("Please hit enter to continue.");

    }

    public String getDate() {
        return io.readString("Please enter date of order.");
    }

    public int getOrderNumber() {
        return io.readInt("Please enter order number.");
    }

    public void displayOrderEditBanner() {
        io.print("=== Edit Order ===");
    }

    public void displayOrderCreateSuccessEditBanner() {
        io.print("Order successfully edited. Please hit enter to continue");
    }

    public String printOrderandConfirmRemove(OrderFile orderFile) {
        System.out.println(orderFile);
        String yesOrNo = io.readString("Would you like to confirm to remove order? Y/N");
        return yesOrNo;
    }

}
