/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller.Service;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import com.mycompany.floormastery.Controller.DTO.Products;
import com.mycompany.floormastery.Controller.DTO.Taxes;
import com.mycompany.floormastery.DAO.FloorMasteryDAO;

import com.mycompany.floormastery.DAO.FloorMasteryDAOException;
import com.mycompany.floormastery.DAO.FloorMasteryExportDAO;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDAO;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDaoException;
import com.mycompany.floormastery.DAO.FloorMasteryTaxDAOException;
import com.mycompany.floormastery.DAO.FloorMasteryTaxesDAO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryServiceLayerImpl implements FloorMasteryServiceLayer {

    FloorMasteryDAO dao;
    FloorMasteryTaxesDAO taxDao;
    FloorMasteryProductsDAO prodDao;
    private FloorMasteryExportDAO exportDAO;

    public FloorMasteryServiceLayerImpl() {

    }

    public FloorMasteryServiceLayerImpl(FloorMasteryDAO dao, FloorMasteryTaxesDAO taxDao, FloorMasteryProductsDAO prodDao) {
        this.dao = dao;
        this.taxDao = taxDao;
        this.prodDao = prodDao;

    }

    @Override
    public OrderFile addOrder(int orderNumber, OrderFile orderFile) throws FloorMasteryDAOException, FloorMasteryTaxDAOException, FloorMasteryProductsDaoException {

        OrderFile taxInfo = getTaxRate(orderNumber, orderFile);
        OrderFile prodInfo = getCost(orderNumber, orderFile);
        OrderFile materialCostInfo = getMaterialCost(orderNumber, orderFile);
        OrderFile laborCostInfo = getLaborCost(orderNumber, orderFile);
        OrderFile taxCostInfo = getTaxCost(orderNumber, orderFile);
        OrderFile totalCostInfo = getTotalCost(orderNumber, orderFile);

        OrderFile addedOrder = dao.addOrder(orderNumber, orderFile);
        return addedOrder;
    }

    @Override
    public OrderFile editOrder(int orderNumber, OrderFile orderFile, String userDate) throws FloorMasteryDAOException, FloorMasteryTaxDAOException, FloorMasteryProductsDaoException {

        OrderFile taxInfo = getTaxRate(orderNumber, orderFile);
        OrderFile prodInfo = getCost(orderNumber, orderFile);
        OrderFile materialCostInfo = getMaterialCost(orderNumber, orderFile);
        OrderFile laborCostInfo = getLaborCost(orderNumber, orderFile);
        OrderFile taxCostInfo = getTaxCost(orderNumber, orderFile);
        OrderFile totalCostInfo = getTotalCost(orderNumber, orderFile);
        
        //  Collections.addAll(states, "TX", "WA", "KY", "CA");
        
        // used to call from controller
        //stateList(taxes, states);
        OrderFile editedOrder = dao.editOrder(orderNumber, orderFile, userDate);
        return editedOrder;
        //throw new FloorMasteryDAOException("Please enter valid state: STATES: TX, WA, KY, CA");
    }

    @Override
    public List<String> stateList() throws FloorMasteryDAOException, FloorMasteryTaxDAOException {
        List<Taxes> taxList = readTaxFile();
        List<String> stateList = new ArrayList<>();
        for (int i = 0; i < taxList.size(); i++) {
            //One tax object per loop
            Taxes indTax = taxList.get(i);
            stateList.add(indTax.getState());
        }
        return stateList;
    }
    @Override
    public List<String> prodList() throws FloorMasteryDAOException, FloorMasteryProductsDaoException {
        List<Products> prodList = readProductFile();
        List<String> userProdList = new ArrayList<>();
        for (int i = 0; i < prodList.size(); i++) {
            //One tax object per loop
            Products indprod = prodList.get(i);
            userProdList.add(indprod.getProductType());
        }
        return userProdList;
    }

    @Override
    public List<OrderFile> getAllOrders(String date) throws FloorMasteryDAOException {
        return dao.getAllOrders(date);
    }

    @Override
    public OrderFile removeOrder(int orderNumber, String date) throws FloorMasteryDAOException {
        OrderFile removeMyChoice = dao.removeOrder(orderNumber, date);
        return removeMyChoice;
    }

    @Override
    public void exportOrders() throws FloorMasteryDAOException {
        dao.exportOrders();
    }

    
    private OrderFile getTaxRate(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException, FloorMasteryTaxDAOException {
        String stateOne = readTaxFile().get(0).getState();
        String stateTwo = readTaxFile().get(1).getState();
        String stateThree = readTaxFile().get(2).getState();
        String stateFour = readTaxFile().get(3).getState();
        if (stateOne.equals(orderFile.getState())) {
            orderFile.setTaxRate(readTaxFile().get(0).getTaxRate());
        } else if (stateTwo.equals(orderFile.getState())) {
            orderFile.setTaxRate(readTaxFile().get(1).getTaxRate());
        } else if (stateThree.equals(orderFile.getState())) {
            orderFile.setTaxRate(readTaxFile().get(2).getTaxRate());
        } else if (stateFour.equals(orderFile.getState())) {
            orderFile.setTaxRate(readTaxFile().get(3).getTaxRate());
        }
        return orderFile;
    }

    @Override
    public List<Taxes> readTaxFile() throws FloorMasteryTaxDAOException {
        List<Taxes> newOrderTax = taxDao.readTaxFile();
        return newOrderTax;
    }

    @Override
    public List<Products> readProductFile() throws FloorMasteryProductsDaoException {
        List<Products> newProduct = prodDao.readProductFile();
        return newProduct;
    }

    
    private OrderFile getCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException, FloorMasteryProductsDaoException {
        List<Products> productOne = readProductFile();

        if (readProductFile().get(0).getProductType().equals(orderFile.getProductType())) {
            orderFile.setLaborCostPerSquareFoot(readProductFile().get(0).getLaborCostPerSquareFoot());
            orderFile.setCostPerSquareFoot(readProductFile().get(0).getLaborCostPerSquareFoot());
        } else if (readProductFile().get(1).getProductType().equals(orderFile.getProductType())) {
            orderFile.setLaborCostPerSquareFoot(readProductFile().get(1).getLaborCostPerSquareFoot());
            orderFile.setCostPerSquareFoot(readProductFile().get(1).getLaborCostPerSquareFoot());
        } else if (readProductFile().get(2).getProductType().equals(orderFile.getProductType())) {
            orderFile.setLaborCostPerSquareFoot(readProductFile().get(2).getLaborCostPerSquareFoot());
            orderFile.setCostPerSquareFoot(readProductFile().get(2).getLaborCostPerSquareFoot());
        } else if (readProductFile().get(3).getProductType().equals(orderFile.getProductType())) {
            orderFile.setLaborCostPerSquareFoot(readProductFile().get(3).getLaborCostPerSquareFoot());
            orderFile.setCostPerSquareFoot(readProductFile().get(3).getLaborCostPerSquareFoot());
        }
        return orderFile;
    }

    
    private OrderFile getMaterialCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        //MaterialCost = (Area * CostPerSquareFoot)
        BigDecimal area = orderFile.getArea();
        BigDecimal costPerSquareFoot = orderFile.getCostPerSquareFoot();
        BigDecimal materialCost = area.multiply(costPerSquareFoot).setScale(2, RoundingMode.CEILING);
        orderFile.setMaterialCost(materialCost);
        return orderFile;
    }

    
    private OrderFile getLaborCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        //LaborCost = (Area * LaborCostPerSquareFoot)
        BigDecimal area = orderFile.getArea();
        BigDecimal laborCostPersquareFoot = orderFile.getLaborCostPerSquareFoot();
        BigDecimal laborCost = area.multiply(laborCostPersquareFoot).setScale(2, RoundingMode.CEILING);
        orderFile.setLaborCost(laborCost);
        return orderFile;
    }

    
    private OrderFile getTaxCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        //Tax = (MaterialCost + LaborCost) * (TaxRate/100)
        //Tax rates are stored as whole numbers
        BigDecimal oneHundred = new BigDecimal("100");
        BigDecimal materialCost = orderFile.getMaterialCost();
        BigDecimal laborCost = orderFile.getLaborCost();
        BigDecimal taxRate = orderFile.getTaxRate();
        BigDecimal tax = ((materialCost.add(laborCost)).multiply(taxRate.divide(oneHundred))).setScale(2, RoundingMode.CEILING);
        orderFile.setTax(tax);
        return orderFile;
    }

    
    private OrderFile getTotalCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        // (MaterialCost + LaborCost + Tax)
        BigDecimal oneHundred = new BigDecimal("100");
        BigDecimal materialCost = orderFile.getMaterialCost();
        BigDecimal laborCost = orderFile.getLaborCost();
        BigDecimal taxRate = orderFile.getTaxRate();
        BigDecimal tax = (materialCost.add(laborCost).multiply(taxRate.divide(oneHundred)));
        BigDecimal total = (materialCost.add(laborCost).add(tax)).setScale(2, RoundingMode.CEILING);
        orderFile.setTotal(total);

        return orderFile;
    }

    @Override
    public OrderFile getUserOrder(int orderNumber, String useDdate) throws FloorMasteryDAOException {
        OrderFile getUser = dao.getUserOrder(orderNumber, useDdate);
        return getUser;
    }

}
