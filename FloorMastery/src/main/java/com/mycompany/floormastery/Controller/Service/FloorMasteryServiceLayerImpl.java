/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller.Service;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import com.mycompany.floormastery.DAO.FloorMasteryDAO;
import com.mycompany.floormastery.DAO.FloorMasteryDAOAuditDAO;
import com.mycompany.floormastery.DAO.FloorMasteryDAOException;
import static com.mycompany.floormastery.DAO.FloorMasteryDAOFileImpl.dateString;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDAO;
import com.mycompany.floormastery.DAO.FloorMasteryTaxDAOException;
import com.mycompany.floormastery.DAO.FloorMasteryTaxesDAO;
import java.math.BigDecimal;
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

    public FloorMasteryServiceLayerImpl(FloorMasteryDAO dao, FloorMasteryTaxesDAO taxDao, FloorMasteryProductsDAO prodDao) {
        this.dao = dao;
        this.taxDao = taxDao;
        this.prodDao = prodDao;

    }

    @Override
    public OrderFile addOrder(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderFile editOrder(int OrderNumber, OrderFile orderFile) throws FloorMasteryDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderFile> getAllOrders(String date) throws FloorMasteryDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderFile removeOrder(String orderNumber, String date) throws FloorMasteryDAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public BigDecimal getTaxRate() throws FloorMasteryTaxDAOException {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        taxDao.readTaxFile();
//        return BigDecimal;
    }
}
