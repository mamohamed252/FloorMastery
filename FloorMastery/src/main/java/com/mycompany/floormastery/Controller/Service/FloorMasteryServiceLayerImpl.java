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
import com.mycompany.floormastery.DAO.FloorMasteryDAOAuditDAO;
import com.mycompany.floormastery.DAO.FloorMasteryDAOException;
import static com.mycompany.floormastery.DAO.FloorMasteryDAOFileImpl.dateString;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDAO;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDaoException;
import com.mycompany.floormastery.DAO.FloorMasteryTaxDAOException;
import com.mycompany.floormastery.DAO.FloorMasteryTaxesDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryServiceLayerImpl implements FloorMasteryServiceLayer {

    FloorMasteryDAO dao;
    FloorMasteryTaxesDAO taxDao;
    FloorMasteryProductsDAO prodDao;
    private FloorMasteryDAOAuditDAO auditDAO;

      public FloorMasteryServiceLayerImpl() {
       
    }
      
    public FloorMasteryServiceLayerImpl(FloorMasteryDAO dao, FloorMasteryTaxesDAO taxDao, FloorMasteryProductsDAO prodDao) {
        this.dao = dao;
        this.taxDao = taxDao;
        this.prodDao = prodDao;

    }

    @Override
    public OrderFile addOrder(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException, FloorMasteryTaxDAOException, FloorMasteryProductsDaoException {
       OrderFile addedOrder = dao.addOrder(OrderNumber, orderFile);
        OrderFile taxInfo = getTaxRate(OrderNumber, orderFile);
        OrderFile   prodInfo = getCost(OrderNumber, orderFile);
        
        addedOrder.setTaxRate(taxInfo.getTaxRate());
        addedOrder.setCostPerSquareFoot(prodInfo.getCostPerSquareFoot());
        addedOrder.setLaborCostPerSquareFoot(prodInfo.getLaborCostPerSquareFoot());
        
        return addedOrder;
    }

    @Override
    public OrderFile editOrder(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderFile> getAllOrders(String date) throws FloorMasteryDAOException {
        return  dao.getAllOrders(date);
        }

    @Override
    public OrderFile removeOrder(String orderNumber, String date) throws FloorMasteryDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public OrderFile getTaxRate(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException, FloorMasteryTaxDAOException {

        readTaxFile();
        OrderFile addOrder = dao.addOrder(OrderNumber, orderFile);
        if (readTaxFile().get(0).getState().equals(addOrder.getState())) {
            addOrder.setTaxRate(readTaxFile().get(0).getTaxRate());
        } else if (readTaxFile().get(1).getState().equals(addOrder.getState())) {
            addOrder.setTaxRate(readTaxFile().get(1).getTaxRate());
        } else if (readTaxFile().get(2).getState().equals(addOrder.getState())) {
            addOrder.setTaxRate(readTaxFile().get(2).getTaxRate());
        } else if (readTaxFile().get(3).getState().equals(addOrder.getState())) {
            addOrder.setTaxRate(readTaxFile().get(3).getTaxRate());
        }
        return addOrder;
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

    @Override
    public OrderFile getCost(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException, FloorMasteryProductsDaoException {
        readProductFile();
        OrderFile addOrder = dao.addOrder(OrderNumber, orderFile);
        if (readProductFile().get(0).getProductType().equals(addOrder.getProductType())) {
            addOrder.setLaborCostPerSquareFoot(readProductFile().get(0).getLaborCostPerSquareFoot());
            addOrder.setCostPerSquareFoot(readProductFile().get(0).getLaborCostPerSquareFoot());
        } else if (readProductFile().get(1).getProductType().equals(addOrder.getProductType())) {
            addOrder.setLaborCostPerSquareFoot(readProductFile().get(1).getLaborCostPerSquareFoot());
            addOrder.setCostPerSquareFoot(readProductFile().get(1).getLaborCostPerSquareFoot());
        } else if (readProductFile().get(2).getProductType().equals(addOrder.getProductType())) {
            addOrder.setLaborCostPerSquareFoot(readProductFile().get(2).getLaborCostPerSquareFoot());
            addOrder.setCostPerSquareFoot(readProductFile().get(2).getLaborCostPerSquareFoot());
        } else if (readProductFile().get(3).getProductType().equals(addOrder.getProductType())) {
            addOrder.setLaborCostPerSquareFoot(readProductFile().get(3).getLaborCostPerSquareFoot());
            addOrder.setCostPerSquareFoot(readProductFile().get(3).getLaborCostPerSquareFoot());
        }
        return addOrder;
    }
}
