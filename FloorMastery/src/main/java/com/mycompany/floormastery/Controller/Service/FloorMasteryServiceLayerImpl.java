/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller.Service;

import com.mycompany.floormastery.DAO.FloorMasteryDAO;
import com.mycompany.floormastery.DAO.FloorMasteryDAOAuditDAO;

/**
 *
 * @author Mohamed
 */
public class FloorMasteryServiceLayerImpl implements FloorMasteryServiceLayer{
    FloorMasteryDAO dao;
    private FloorMasteryDAOAuditDAO auditDAO;
    
    public FloorMasteryServiceLayerImpl(FloorMasteryDAO dao, FloorMasteryDAOAuditDAO auditDAO) {
        this.dao = dao;
        this.auditDAO = auditDAO;
    }
}
