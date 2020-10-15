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
import com.mycompany.floormastery.DAO.FloorMasteryProductsDaoException;
import com.mycompany.floormastery.DAO.FloorMasteryTaxDAOException;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryController {

    public FloorMasteryController(FloorMasteryServiceLayer service, FloorMasteryView view) {
        this.service = service;
        this.view = view;

    }

    public FloorMasteryController(FloorMasteryDAO dao, FloorMasteryView view) {
        this.dao = dao;
        this.view = view;
    }

    private FloorMasteryServiceLayer service;
    private FloorMasteryDAO dao;
    private FloorMasteryView view;
    private UserIO io = new UserIOConsoleImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
      
            while (keepGoing) {
                 try {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrders();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        ExportAllData();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }catch (FloorMasteryDAOException | FloorMasteryTaxDAOException | FloorMasteryProductsDaoException e) {
            System.out.println(e.getMessage());

            }
        }
//
//
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() throws FloorMasteryDAOException {
        String date = view.getDisplayOrdersDate();
        List<OrderFile> orderList = service.getAllOrders(date);
        view.displayOrdersList(orderList);

    }

    private void addOrders() throws FloorMasteryDAOException, FloorMasteryTaxDAOException, FloorMasteryProductsDaoException {
        OrderFile newOrderFile = view.getNewOrderInfo();
        service.addOrder(newOrderFile.getOrderNumber(), newOrderFile);

    }

    private void editOrder() throws FloorMasteryDAOException, FloorMasteryTaxDAOException, FloorMasteryProductsDaoException {
        view.displayOrderEditBanner();
        String date = view.getDate();
        int orderNumber = view.getOrderNumber();
        OrderFile editOrder = view.getEditOrderInfo(orderNumber);
        service.editOrder(orderNumber, editOrder, date);
        view.displayOrderCreateSuccessEditBanner();

    }

    private void removeOrder() throws FloorMasteryDAOException {
        view.displayRemoveOrderBanner();
        String date = view.getDate();
        int getOrderNumber = view.getOrderNumber();
        //String confirmYesOrNo = view.printOrderandConfirmRemove(orderFile);

        OrderFile removedOrder = service.removeOrder(getOrderNumber, date);
        view.displayRemoveResult(removedOrder);

    }

    private void ExportAllData() throws FloorMasteryDAOException {
        service.exportOrders();

    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();

    }

}
