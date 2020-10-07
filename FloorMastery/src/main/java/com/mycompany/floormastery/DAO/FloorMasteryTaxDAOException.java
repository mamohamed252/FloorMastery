/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryTaxDAOException extends Exception {
       public FloorMasteryTaxDAOException(String message){
        super(message);
    }
    public FloorMasteryTaxDAOException(String message, Throwable cause){
        super(message, cause);
    }  
}

