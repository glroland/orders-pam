/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.glroland.orders.dto;

import java.util.ArrayList;
import java.util.Date;

public class IncomingOrder extends BaseDTO implements java.io.Serializable {

    static final long serialVersionUID = 1L;

    private Date orderDate;
    private Long orderNumber;
    private ArrayList<IncomingOrderLine> orderLines;
    private String orderStatus;
    private Double total;

    public IncomingOrder() {
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ArrayList<IncomingOrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(ArrayList<IncomingOrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
}