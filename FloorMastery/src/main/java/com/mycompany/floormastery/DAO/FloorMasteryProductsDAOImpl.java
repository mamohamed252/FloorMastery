/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import com.mycompany.floormastery.Controller.DTO.Products;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryProductsDAOImpl implements FloorMasteryProductsDAO{
    private Map<String, Products> ProductMap = new HashMap<>();
    public static final String DELIMITER = ",";
    public static String Products_FILE = ("Data/Products.txt");

    @Override
    public List<Products> readProductFile() throws FloorMasteryProductsDaoException {
        loadProducts();
        return new ArrayList(ProductMap.values());
    }
    private Products unMarshallProductFile(String productsAsText) {
        String[] productTokens = productsAsText.split(DELIMITER);
        Products productsFromFile = new Products();
        productsFromFile.setProductType(productTokens[0]);
        productsFromFile.setCostPerSquareFoot(new BigDecimal (productTokens[1]));
        productsFromFile.setLaborCostPerSquareFoot(new BigDecimal (productTokens[2]));
        
        return productsFromFile;
    }

    private void loadProducts() throws FloorMasteryProductsDaoException {
        Scanner sc;
        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(Products_FILE)));
        } catch (FileNotFoundException e) {
            throw new FloorMasteryProductsDaoException("could not load products into file", e);
        }
        String currentLine;
        Products selectedFile;
        sc.nextLine();
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            selectedFile = unMarshallProductFile(currentLine);
            ProductMap.put(selectedFile.getProductType(), selectedFile);
        }
        sc.close();
    }
    
}
