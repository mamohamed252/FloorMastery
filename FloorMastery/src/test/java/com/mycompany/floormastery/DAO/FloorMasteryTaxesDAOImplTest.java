/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import com.mycompany.floormastery.Controller.DTO.Taxes;
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
public class FloorMasteryTaxesDAOImplTest {

    FloorMasteryTaxesDAO testTaxDao = new FloorMasteryTaxesDAOImpl();

    public FloorMasteryTaxesDAOImplTest() {
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
    public void testReadTaxFile() throws FloorMasteryTaxDAOException {
        //arrange
        Taxes newTax = new Taxes("TX", "Texas", new BigDecimal("4.45"));

        //act
        List<Taxes> readTax = testTaxDao.readTaxFile();
        //assert
        assertTrue(readTax.contains(newTax), "Expected to equal TX, Texas, 4.45");
        assertEquals(readTax.size(), 4, "Expected to equal four items in list");
    }

}
