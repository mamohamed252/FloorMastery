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
public class FloorMasteryProductsDaoException extends Exception {
        public FloorMasteryProductsDaoException(String message){
        super(message);
    }
    public FloorMasteryProductsDaoException(String message, Throwable cause){
        super(message, cause);
    } 
    
}
