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
    public void setUp()throws Exception {
        String testFileLoc = "FloorMasteryDOAFileTest.txt";
        
        FileWriter fw = new FileWriter(testFileLoc);
        fw.append("1,Ada Lovelace,CA,25.00,Tile,249.00,3.50,4.15,871.50,1033.35,476.21,2381.06");
        fw.flush();
        fw.close();
        testDAO = new FloorMasteryDAOFileImpl(testFileLoc);
    }
    
    @AfterEach
    public void tearDown() {
    }
    @Test
    public void testGetAllOrder() throws FloorMasteryDAOException{
         //arrange
      String date = "10082020";
        OrderFile newOrder = new OrderFile(1,"test","TX", new BigDecimal("4.45"),"Wood", new BigDecimal("100"), new BigDecimal("4.75"), new BigDecimal("4.75"), new BigDecimal("475.00"), new BigDecimal("475.00"), new BigDecimal("42.28"), new BigDecimal("992.28"));
         
        //act
         List<OrderFile> allOrders = testDAO.getAllOrders(date);
         boolean doesContain = allOrders.contains(newOrder);
        
        //assert
        assertEquals(allOrders.size(),6, "Expected 6 orders");
        assertTrue(doesContain, "Expected order 1 to be test");
    }
    @Test
    public void testRemoveOrder() {
         //arrange
        
        //act
        
        //assert
    }
     @Test
    public void testEditOrder() {
         //arrange
        
        //act
        
        //assert
    }
     @Test
    public void testAddOrder()throws FloorMasteryDAOException  {
         //arrange
        
        OrderFile add = new OrderFile(1,"test","TX", new BigDecimal("4.45"),"Wood", new BigDecimal("100"), new BigDecimal("4.75"), new BigDecimal("4.75"), new BigDecimal("475.00"), new BigDecimal("475.00"), new BigDecimal("42.28"), new BigDecimal("992.28"));
        //act
        OrderFile testAdd = testDAO.addOrder(1, add);
        //assert
        assertEquals(testAdd.getOrderNumber(),1, "Expected order number to be 1");
    }
}
