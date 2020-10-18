/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import com.mycompany.floormastery.Controller.DTO.Products;
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
public class FloorMasteryProductsDAOImplTest {
    
    FloorMasteryProductsDAO testProdDAO = new FloorMasteryProductsDAOImpl();
    public FloorMasteryProductsDAOImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testReadProd()throws FloorMasteryProductsDaoException {
        //arrange
        Products newProd = new Products("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
        //act
        List<Products> testProd = testProdDAO.readProductFile();
        //assert
        assertTrue(testProd.contains(newProd), "Expected Carpet, 2.25, 2.10");
        assertEquals(testProd.size(), 4, "Expected four products in file");
    }
    
}
