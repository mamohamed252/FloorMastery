/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller.Service;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import com.mycompany.floormastery.DAO.FloorMasteryDAOException;
import com.mycompany.floormastery.DAO.FloorMasteryTaxDAOException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public interface FloorMasteryServiceLayer {
    
     OrderFile addOrder(int OrderNumber, OrderFile orderFile)throws FloorMasteryDAOException ;
    OrderFile editOrder(int OrderNumber, OrderFile orderFile)throws FloorMasteryDAOException ;
    List<OrderFile> getAllOrders(String date)throws FloorMasteryDAOException;
    OrderFile removeOrder(String orderNumber, String date)throws FloorMasteryDAOException;
     BigDecimal getTaxRate() throws FloorMasteryTaxDAOException;
//    OrderFile checkState
//    checkProductType
//    checkArea
//    getAllFields
}
