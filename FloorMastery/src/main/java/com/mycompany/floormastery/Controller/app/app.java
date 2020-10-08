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

/**
 *
 * @author Mohamed
 */
public class app {

    public static void main(String[] args) {
     UserIO myIo = new UserIOConsoleImpl();  
     FloorMasteryDAO myDao = new FloorMasteryDAOFileImpl();
     FloorMasteryView myView = new FloorMasteryView(myIo);
     FloorMasteryServiceLayer myService = new FloorMasteryServiceLayerImpl();
     FloorMasteryController controller = new FloorMasteryController(myService, myView);
     controller.run();
    }

}
