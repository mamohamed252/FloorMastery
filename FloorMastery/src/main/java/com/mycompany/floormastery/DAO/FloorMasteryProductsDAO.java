/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import com.mycompany.floormastery.Controller.DTO.Products;
import java.util.List;

/**
 *
 * @author Mohamed
 */
public interface FloorMasteryProductsDAO {
    List<Products> readProductFile()throws FloorMasteryProductsDaoException;
    
}
