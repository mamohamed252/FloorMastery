/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public interface FloorMasteryDAO {
    OrderFile addOrder(int OrderNumber, OrderFile orderFile)throws FloorMasteryDAOException ;
    OrderFile editOrder(int OrderNumber, OrderFile orderFile)throws FloorMasteryDAOException ;
    List<OrderFile> getAllOrders(String date)throws FloorMasteryDAOException;
    OrderFile removeOrder(String orderNumber, String date)throws FloorMasteryDAOException ;
    
}
