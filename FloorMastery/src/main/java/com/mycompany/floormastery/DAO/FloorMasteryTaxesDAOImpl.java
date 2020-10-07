/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import com.mycompany.floormastery.Controller.DTO.Taxes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryTaxesDAOImpl implements FloorMasteryTaxesDAO {

    private Map<String, Taxes> taxRateMap = new HashMap<>();
    public static final String DELIMITER = ",";
    public static String Taxes_FILE = ("Data/Taxes.txt");

    @Override
    public List<Taxes> readTaxFile() throws FloorMasteryTaxDAOException {
        loadTaxes();
        return new ArrayList(taxRateMap.values());
    }

    private Taxes unMarshallTaxFile(String taxesAsText) {
        String[] taxTokens = taxesAsText.split(DELIMITER);
        Taxes taxesFromFile = new Taxes();
        taxesFromFile.setState(taxTokens[0]);
        taxesFromFile.setStateName(taxTokens[1]);
        taxesFromFile.setTaxRate(new BigDecimal(taxTokens[2]));

        return taxesFromFile;
    }

    private void loadTaxes() throws FloorMasteryTaxDAOException {
        Scanner sc;
        try {
            sc = new Scanner(
                    new BufferedReader(
                            new FileReader(Taxes_FILE)));
        } catch (FileNotFoundException e) {
            throw new FloorMasteryTaxDAOException("could not load taxes into file", e);
        }
        String currentLine;
        Taxes taxObject;
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            taxObject = unMarshallTaxFile(currentLine);
            taxRateMap.put(taxObject.getState(), taxObject);
        }
        sc.close();
    }

}
