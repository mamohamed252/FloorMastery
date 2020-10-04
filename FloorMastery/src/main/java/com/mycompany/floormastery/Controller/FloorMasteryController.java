/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import com.mycompany.floormastery.Controller.Service.FloorMasteryServiceLayer;
import com.mycompany.floormastery.Controller.ui.FloorMasteryView;
import com.mycompany.floormastery.Controller.ui.UserIO;
import com.mycompany.floormastery.Controller.ui.UserIOConsoleImpl;
import com.mycompany.floormastery.DAO.FloorMasteryDAO;
import com.mycompany.floormastery.DAO.FloorMasteryDAOException;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryController {

    public FloorMasteryController(FloorMasteryDAO dao, FloorMasteryView view) {
        this.dao = dao;
        this.view = view;
    }

    //private FloorMasteryServiceLayer service;
    private FloorMasteryDAO dao;
    private FloorMasteryView view;
    private UserIO io = new UserIOConsoleImpl();

    public void run(){
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrders();
                        break;
                    case 3:
                        //editOrder
                        break;
                    case 4:
                        //removeOrder
                        break;
                    case 5:
                        //ExportAllData
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }

        } catch (FloorMasteryDAOException e) {
            System.out.println(e.getMessage());
        }

    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() throws FloorMasteryDAOException {
        String date = view.getDisplayOrdersDate();
        List<OrderFile> orderList = dao.getAllOrders(date);
        view.displayOrdersList(orderList);

    }

    private void addOrders() throws FloorMasteryDAOException {
        view.firstLineMessage();
        OrderFile newOrderFile = view.getNewOrderInfo();
        dao.addOrder(newOrderFile.getOrderNumber(), newOrderFile);

    }

    private void editOrder() {

    }

    private void removeOrder() {
        view.displayRemoveOrderBanner();

    }

    private void ExportAllData() {

    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();

    }

}
