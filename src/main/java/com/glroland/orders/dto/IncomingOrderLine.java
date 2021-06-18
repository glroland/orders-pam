package com.glroland.orders.dto;

public class IncomingOrderLine extends BaseDTO  implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    private Integer lineNumber;
    private String sku;
    private Integer quantity;
    private Double unitPrice;
    private String status;
    private String fulfillmentPartner;
    private String fulfillmentOrderNumber;
    private String fulfillmentDate;

    public Integer getLineNumber() {
        return lineNumber;
    }
    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFulfillmentPartner() {
        return fulfillmentPartner;
    }
    public void setFulfillmentPartner(String fulfillmentPartner) {
        this.fulfillmentPartner = fulfillmentPartner;
    }
    public String getFulfillmentOrderNumber() {
        return fulfillmentOrderNumber;
    }
    public void setFulfillmentOrderNumber(String fulfillmentOrderNumber) {
        this.fulfillmentOrderNumber = fulfillmentOrderNumber;
    }
    public String getFulfillmentDate() {
        return fulfillmentDate;
    }
    public void setFulfillmentDate(String fulfillmentDate) {
        this.fulfillmentDate = fulfillmentDate;
    }
    public Double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
