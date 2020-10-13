/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author Mohamed
 */
public class FloorMasteryExportDAOFileImpl implements FloorMasteryExportDAO {

    public static final String BACKUP_FILE = "Backup/DataExport.txt";

    public void writeExportEntry(String entry, String date) throws FloorMasteryDAOException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(BACKUP_FILE, true));
        } catch (IOException e) {
            throw new FloorMasteryDAOException("Could not persist export information.", e);
        }
        out.println(entry + ", " + date);
        out.flush();
    }

}
