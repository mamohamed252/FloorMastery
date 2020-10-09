/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller.app;

import com.mycompany.floormastery.Controller.FloorMasteryController;
import com.mycompany.floormastery.Controller.Service.FloorMasteryServiceLayer;
import com.mycompany.floormastery.Controller.Service.FloorMasteryServiceLayerImpl;
import com.mycompany.floormastery.Controller.ui.FloorMasteryView;
import com.mycompany.floormastery.Controller.ui.UserIO;
import com.mycompany.floormastery.Controller.ui.UserIOConsoleImpl;
import com.mycompany.floormastery.DAO.FloorMasteryDAO;
import com.mycompany.floormastery.DAO.FloorMasteryDAOFileImpl;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDAO;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDAOImpl;
import com.mycompany.floormastery.DAO.FloorMasteryTaxesDAO;
import com.mycompany.floormastery.DAO.FloorMasteryTaxesDAOImpl;

/**
 *
 * @author Mohamed
 */
public class app {

    public static void main(String[] args) {
     UserIO myIo = new UserIOConsoleImpl();  
     FloorMasteryDAO myDao = new FloorMasteryDAOFileImpl();
     FloorMasteryTaxesDAO myTaxDao = new FloorMasteryTaxesDAOImpl();
     FloorMasteryProductsDAO myProdDAO = new FloorMasteryProductsDAOImpl();

     
     FloorMasteryView myView = new FloorMasteryView(myIo);
     FloorMasteryServiceLayer myService = new FloorMasteryServiceLayerImpl(myDao, myTaxDao, myProdDAO);
     FloorMasteryController controller = new FloorMasteryController(myService, myView);
     controller.run();
    }

}
