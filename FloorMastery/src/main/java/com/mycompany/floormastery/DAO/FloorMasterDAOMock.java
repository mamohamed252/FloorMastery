/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mohamed
 */
public class FloorMasterDAOMock implements FloorMasteryDAO{

    @Override
    public OrderFile addOrder(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        return orderFile;
        }

    @Override
    public OrderFile editOrder(int OrderNumber, OrderFile orderFile, String userDate) throws FloorMasteryDAOException {
    return orderFile;
    }

    @Override
    public List<OrderFile> getAllOrders(String date) throws FloorMasteryDAOException {
        Map<Integer, OrderFile> file = new HashMap<>();
        return new ArrayList(file.values());
        
       }

    @Override
    public OrderFile removeOrder(int orderNumber, String date) throws FloorMasteryDAOException {
        OrderFile nothing = new OrderFile();
        return nothing;
        }

    @Override
    public void exportOrders() throws FloorMasteryDAOException {
        
    }

    @Override
    public OrderFile getUserOrder(int orderNumber, String userDate) throws FloorMasteryDAOException {
        OrderFile nothing = new OrderFile();
        return nothing;
        }
    
}
