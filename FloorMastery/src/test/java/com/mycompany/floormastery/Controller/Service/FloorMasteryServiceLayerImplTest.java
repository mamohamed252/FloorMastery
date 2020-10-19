/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller.Service;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import com.mycompany.floormastery.DAO.FloorMasterDAOMock;
import com.mycompany.floormastery.DAO.FloorMasteryDAO;
import com.mycompany.floormastery.DAO.FloorMasteryDAOException;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDAO;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDAOImpl;
import com.mycompany.floormastery.DAO.FloorMasteryProductsDaoException;
import com.mycompany.floormastery.DAO.FloorMasteryTaxDAOException;
import com.mycompany.floormastery.DAO.FloorMasteryTaxesDAO;
import com.mycompany.floormastery.DAO.FloorMasteryTaxesDAOImpl;
import java.io.FileWriter;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryServiceLayerImplTest {

    String testFileLoc = "TestDAOFolder/Orders_10102020.txt";
    FloorMasteryDAO testDAO = new FloorMasterDAOMock(testFileLoc);
    FloorMasteryTaxesDAO taxDAO = new FloorMasteryTaxesDAOImpl();
    FloorMasteryProductsDAO prodDAO = new FloorMasteryProductsDAOImpl();
    FloorMasteryServiceLayer testService = new FloorMasteryServiceLayerImpl(testDAO, taxDAO, prodDAO);

    public FloorMasteryServiceLayerImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
       

        FileWriter fw = new FileWriter(testFileLoc);
        fw.append("1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06");
        fw.flush();
        fw.close();
      
        //2,name,TX,4.45,Wood,100,4.75,4.75,475.00,475.00,42.275000,992.275000
        //3,name2,TX,4.45,Wood,100,4.75,4.75,475.00,475.00,42.275000,992.275000
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testaddCalculation() throws Exception {
        OrderFile addCalc = new OrderFile(2, "name", "TX", "Wood", new BigDecimal("100"));

        OrderFile serviceAdd = testService.addOrder(2, addCalc);

        assertEquals(serviceAdd.getTaxRate(), new BigDecimal("4.45"), "Expected tax rate to be 4.45");
        assertEquals(serviceAdd.getCostPerSquareFoot(), new BigDecimal("4.75"), "Expected cost per square foot to be 4.75");
        assertEquals(serviceAdd.getLaborCostPerSquareFoot(), new BigDecimal("4.75"), "Expected cost per square foot to be 4.75");
        assertEquals(serviceAdd.getMaterialCost(), new BigDecimal("475.00"), "Expected material cost to be 4.75");
        assertEquals(serviceAdd.getLaborCost(), new BigDecimal("475.00"), "Expected laborcost to be 4.75");
        assertEquals(serviceAdd.getTax(), new BigDecimal("42.28"), "Expected tax to be 42.28");
        assertEquals(serviceAdd.getTotal(), new BigDecimal("992.28"), "Expected total to be 992.28");
    }

}
