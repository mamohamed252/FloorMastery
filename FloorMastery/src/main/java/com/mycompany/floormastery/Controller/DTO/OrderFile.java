/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller.DTO;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Mohamed
 */
public class OrderFile {

    private int orderNumber;
    private String customerName;
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costPerSquareFoot;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    private BigDecimal LaborCostPerSquareFoot;
    private String dateinfo;

   public OrderFile() {
    }
   
    public OrderFile(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderFile(int orderNumber, String dateInfo, String customerName, String state, String productType, BigDecimal area) {
        this.orderNumber = orderNumber;
        this.dateinfo = dateInfo;
        this.customerName = customerName;
        this.state = state;
        this.productType = productType;
        this.area = area;
    }
    
    public String getDateinfo() {
        return dateinfo;
    }

    public void setDateinfo(String dateinfo) {
        this.dateinfo = dateinfo;
    }


    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal LaborCostPerSquareFoot) {
        this.LaborCostPerSquareFoot = LaborCostPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.orderNumber;
        hash = 83 * hash + Objects.hashCode(this.customerName);
        hash = 83 * hash + Objects.hashCode(this.state);
        hash = 83 * hash + Objects.hashCode(this.taxRate);
        hash = 83 * hash + Objects.hashCode(this.productType);
        hash = 83 * hash + Objects.hashCode(this.area);
        hash = 83 * hash + Objects.hashCode(this.costPerSquareFoot);
        hash = 83 * hash + Objects.hashCode(this.materialCost);
        hash = 83 * hash + Objects.hashCode(this.laborCost);
        hash = 83 * hash + Objects.hashCode(this.tax);
        hash = 83 * hash + Objects.hashCode(this.total);
        hash = 83 * hash + Objects.hashCode(this.LaborCostPerSquareFoot);
        hash = 83 * hash + Objects.hashCode(this.dateinfo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderFile other = (OrderFile) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.productType, other.productType)) {
            return false;
        }
        if (!Objects.equals(this.dateinfo, other.dateinfo)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSquareFoot, other.costPerSquareFoot)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        if (!Objects.equals(this.LaborCostPerSquareFoot, other.LaborCostPerSquareFoot)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderFile{" + "orderNumber=" + orderNumber + ", customerName=" + customerName + ", state=" + state + ", taxRate=" + taxRate + ", productType=" + productType + ", area=" + area + ", costPerSquareFoot=" + costPerSquareFoot + ", materialCost=" + materialCost + ", laborCost=" + laborCost + ", tax=" + tax + ", total=" + total + ", LaborCostPerSquareFoot=" + LaborCostPerSquareFoot + ", dateinfo=" + dateinfo + '}';
    }
    
}
