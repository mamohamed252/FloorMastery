/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller.Service;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import com.mycompany.floormastery.Controller.DTO.Products;
import com.mycompany.floormastery.Controller.DTO.Taxes;
import com.mycompany.floormastery.DAO.FloorMasteryDAOException;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDaoException;
import com.mycompany.floormastery.DAO.FloorMasteryTaxDAOException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public interface FloorMasteryServiceLayer {

    OrderFile addOrder(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException, FloorMasteryTaxDAOException, FloorMasteryProductsDaoException;

    OrderFile editOrder(int orderNumber, OrderFile orderFile, String userDate) throws FloorMasteryDAOException, FloorMasteryTaxDAOException, FloorMasteryProductsDaoException;

    List<OrderFile> getAllOrders(String date) throws FloorMasteryDAOException;

    OrderFile removeOrder(int orderNumber, String date) throws FloorMasteryDAOException;

    OrderFile getTaxRate(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException, FloorMasteryTaxDAOException;

    OrderFile getCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException, FloorMasteryProductsDaoException;

    public List<Taxes> readTaxFile() throws FloorMasteryTaxDAOException;

    public List<Products> readProductFile() throws FloorMasteryProductsDaoException;

    OrderFile getMaterialCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException;

    OrderFile getLaborCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException;

    OrderFile getTaxCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException;

    OrderFile getTotalCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException;

    public void exportOrders() throws FloorMasteryDAOException;

    public OrderFile getUserOrder(int orderNumber, String userDate) throws FloorMasteryDAOException;
}
