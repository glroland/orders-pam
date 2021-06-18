package com.glroland.orders.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.ArrayList;

import com.glroland.orders.dto.IncomingOrder;
import com.glroland.orders.dto.IncomingOrderLine;
import com.glroland.orders.dto.SupplierQuote;
import com.glroland.orders.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class OrderSplitService 
{
    private static final Logger log = LoggerFactory.getLogger(OrderSplitService.class);

    @Inject
    private ProductService productService;

    public List<SupplierQuote> splitForSupplier(IncomingOrder order) 
    {
        if (order == null)
        {
            String msg = "Inbound order is null";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        ArrayList<SupplierQuote> requestList = new ArrayList<SupplierQuote>();

        if (order != null)
        {
            if (order.getOrderLines() != null)
            {
                for (IncomingOrderLine line : order.getOrderLines())
                {
                    String supplierType = productService.getSupplierTypeForProduct(line.getSku());
                    
                    SupplierQuote request = new SupplierQuote();
                    request.setOrderNumber(order.getOrderNumber());
                    request.setLineNumber(line.getLineNumber());
                    request.setSupplierType(supplierType);
                    request.setSku(line.getSku());
                    request.setQuantity(line.getQuantity());

                    requestList.add(request);
                }
            }
        }

        return requestList;
    }

    public boolean joinSupplierQuotes(IncomingOrder order, List supplierRequestsIn)
    {
        if (order == null)
        {
            String msg = "Incoming order is null";
            log.error(msg);
            throw new RuntimeException(msg);
        }
        if (supplierRequestsIn == null)
        {
            String msg = "Incoming supplier response list is null";
            log.error(msg);
            order.setOrderStatus(Constants.IncomingOrderStatus.ERROR);
            return false;
        }
        
        List<SupplierQuote> supplierResponses = (List<SupplierQuote>)supplierRequestsIn;
        if (supplierResponses.size() == 0)
        {
            String msg = "Supplier response list is empty";
            log.error(msg);
            order.setOrderStatus(Constants.IncomingOrderStatus.ERROR);
            return false;
        }

        for (SupplierQuote supplierResponse : supplierResponses)
        {
            if (!Constants.SupplierRequestStatus.APPROVED.equals(supplierResponse.getStatus()))
            {
                String msg = "Supplier response is not approved.  Cannot proceed with quote! Status=" + supplierResponse.getStatus();
                log.error(msg);
                order.setOrderStatus(Constants.IncomingOrderStatus.ERROR);
                return false;
            }

            IncomingOrderLine orderLine = getMatchingLine(order, supplierResponse);
            if (orderLine == null)
            {
                String msg = "Unable to match supplier response to a line in the order!";
                log.error(msg);
                order.setOrderStatus(Constants.IncomingOrderStatus.ERROR);
                return false;
            }
        }

        order.setOrderStatus(Constants.IncomingOrderStatus.READY);
        return true;
    } 

    private IncomingOrderLine getMatchingLine(IncomingOrder order, SupplierQuote supplierResponse)
    {
        // assuming null checks have already occurred.  we are marked as private

        // perform basic validation
        if ((order.getOrderNumber() == null) || !order.getOrderNumber().equals(supplierResponse.getOrderNumber()))
        {
            String msg = "getMatchingLine - order numbers do not match between order and supplier quote request";
            log.error(msg);
            throw new RuntimeException(msg);
        }
        if (supplierResponse.getLineNumber() == null)
        {
            String msg = "getMatchingLine - supplier request line number is null";
            log.error(msg);
            throw new RuntimeException(msg);
        }
        if ((order.getOrderLines() == null) || (order.getOrderLines().size() == 0))
        {
            String msg = "getMatchingLine - order has no lines but has supplier responses";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        // find the line
        for (IncomingOrderLine orderLine : order.getOrderLines())
        {
            if ((orderLine == null) || (orderLine.getLineNumber() == null))
            {
                String msg = "getMatchingLine - order line is null or has no line number";
                log.error(msg);
                throw new RuntimeException(msg);
            }
                
            if (orderLine.getLineNumber().equals(supplierResponse.getLineNumber()))
            {
                return orderLine;
            }
        }

        return null;
    }
}
