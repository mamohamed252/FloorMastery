/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import com.mycompany.floormastery.Controller.DTO.OrderFile;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
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
public class FloorMasteryDAOFileImplTest {

    FloorMasteryDAO testDAO;

    public FloorMasteryDAOFileImplTest() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        String testFileLoc = "TestDAOFolder/Orders_10102020.txt";

        FileWriter fw = new FileWriter(testFileLoc);
        fw.append("1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06");
        fw.flush();
        fw.close();
        testDAO = new FloorMasterDAOMock(testFileLoc);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllOrder() throws FloorMasteryDAOException {
        //arrange
        String date = "10102020";
        OrderFile newOrder = new OrderFile(1, "test", "TX", new BigDecimal("4.45"), "Wood", new BigDecimal("100"), new BigDecimal("4.75"), new BigDecimal("4.75"), new BigDecimal("475.00"), new BigDecimal("475.00"), new BigDecimal("42.28"), new BigDecimal("992.28"));
        testDAO.addOrder(2, newOrder);
        //act
        List<OrderFile> allOrders = testDAO.getAllOrders(date);
        boolean doesContain = allOrders.contains(newOrder);

        //assert
        assertEquals(allOrders.size(), 2, "Expected 2 orders");
        assertTrue(doesContain, "Expected order 1 to be test");
    }

    @Test
    public void testRemoveOrder() throws FloorMasteryDAOException {
        //arrange
        String date = "10102020";
        OrderFile testOrder = new OrderFile(2, "Test", "CA", new BigDecimal("25.00"), "Tile", new BigDecimal("249.00"), new BigDecimal("3.50"), new BigDecimal("4.15"), new BigDecimal("871.50"), new BigDecimal("1033.35"), new BigDecimal("476.21"), new BigDecimal("2381.06"));
        testDAO.addOrder(2, testOrder);
        OrderFile removeOrder = testDAO.removeOrder(2, date);
        //act
        OrderFile removedOrder = testDAO.removeOrder(2, date);
        OrderFile newOrder = testDAO.getUserOrder(2, date);
        List<OrderFile> checkOrderRemoved = testDAO.getAllOrders(date);
        Boolean doesContain = checkOrderRemoved.contains(testOrder);

        //assert
        assertEquals(newOrder, removedOrder, "Expected to remove testOrder order");
        assertEquals(checkOrderRemoved.size(), 1, "Expected 1 orders left");
        //removed testOrder so it can not be in list.
        assertEquals(doesContain, false, "Expected 1 orders left");
    }

    @Test
    public void testEditOrder() throws FloorMasteryDAOException {
        //arrange
        String date = "10102020";
        OrderFile testEdit = new OrderFile(2, "Test", "CA", new BigDecimal("25.00"), "Tile", new BigDecimal("249.00"), new BigDecimal("3.50"), new BigDecimal("4.15"), new BigDecimal("871.50"), new BigDecimal("1033.35"), new BigDecimal("476.21"), new BigDecimal("2381.06"));
        OrderFile addEdit = new OrderFile(2, "Add", "CA", new BigDecimal("25.00"), "Tile", new BigDecimal("249.00"), new BigDecimal("3.50"), new BigDecimal("4.15"), new BigDecimal("871.50"), new BigDecimal("1033.35"), new BigDecimal("476.21"), new BigDecimal("2381.06"));

        testDAO.addOrder(2, addEdit);
        OrderFile editOrder = testDAO.editOrder(2, testEdit, date);
        //act

        List<OrderFile> checkEdit = testDAO.getAllOrders(date);
        Boolean doesContain = checkEdit.contains(testEdit);
        //assert
        assertEquals(testEdit, testDAO.getUserOrder(2, date), "Expected to edit order number 2");
        assertEquals(checkEdit.size(), 2, "Expected file to contain ");
        assertTrue(doesContain, "Expected order 2 to be add");
    }

    @Test
    public void testAddOrder() throws FloorMasteryDAOException {
        //arrange
        String date = "10102020";
        OrderFile add = new OrderFile(1, "test", "TX", new BigDecimal("4.45"), "Wood", new BigDecimal("100"), new BigDecimal("4.75"), new BigDecimal("4.75"), new BigDecimal("475.00"), new BigDecimal("475.00"), new BigDecimal("42.28"), new BigDecimal("992.28"));
        //act
        OrderFile testAdd = testDAO.addOrder(2, add);
        List<OrderFile> testList = testDAO.getAllOrders(date);
        Boolean doesContain = testList.contains(testAdd);

        //assert
        assertEquals(testAdd.getOrderNumber(), 2, "Expected order number to be 2");
        assertTrue(doesContain, "Expected file to contain to testAdd");

    }
}
