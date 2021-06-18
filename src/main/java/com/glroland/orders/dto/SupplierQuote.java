package com.glroland.orders.dto;

import java.util.Date;

public class SupplierQuote extends BaseDTO implements java.io.Serializable
{
    static final long serialVersionUID = 1L;

    private Long orderNumber;
    private Integer lineNumber;
    private String supplierType;
    private String sku;
    private Integer quantity;
    private Double unitCost;
    private Double subtotalCost;
    private Double totalCost;
    private Double shipping;
    private Double tax;
    private Date dateQuoted;
    private Date estimatedShipDate;
    private String status;

    public Long getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
    public Integer getLineNumber() {
        return lineNumber;
    }
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }
    public String getSupplierType() {
        return supplierType;
    }
    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getUnitCost() {
        return unitCost;
    }
    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }
    public Double getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }
    public Double getShipping() {
        return shipping;
    }
    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }
    public Date getDateQuoted() {
        return dateQuoted;
    }
    public void setDateQuoted(Date dateQuoted) {
        this.dateQuoted = dateQuoted;
    }
    public Double getTax() {
        return tax;
    }
    public void setTax(Double tax) {
        this.tax = tax;
    }
    public Double getSubtotalCost() {
        return subtotalCost;
    }
    public void setSubtotalCost(Double subtotalCost) {
        this.subtotalCost = subtotalCost;
    }
    public Date getEstimatedShipDate() {
        return estimatedShipDate;
    }
    public void setEstimatedShipDate(Date estimatedShipDate) {
        this.estimatedShipDate = estimatedShipDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
