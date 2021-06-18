package com.glroland.orders.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.glroland.orders.dto.SupplierQuote;
import com.glroland.orders.util.Constants;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SupplierQuoteService {
    private static final Logger log = LoggerFactory.getLogger(SupplierQuoteService.class);

    public void quoteProduct(SupplierQuote supplierQuote)
    {
        if (supplierQuote == null)
        {
            String msg = "Incoming supplierQuote is null.  Cannot quote product sale.";
            log.error(msg);
            throw new RuntimeException(msg);
        }
        if (supplierQuote.getQuantity() == null)
        {
            String msg = "Quantity for incoming supplier quote is null.  Cannot quote product sale.";
            supplierQuote.setStatus(Constants.SupplierRequestStatus.ERROR);
            log.error(msg);
            throw new RuntimeException(msg);
        }

        double costPerUnit = roundPennies(Math.random() * 100.0);
        supplierQuote.setUnitCost(costPerUnit);

        double subtotal = costPerUnit * supplierQuote.getQuantity();
        supplierQuote.setSubtotalCost(subtotal);

        supplierQuote.setDateQuoted(new Date());
    }

    public void calculateTax(SupplierQuote supplierQuote)
    {
        if (supplierQuote == null)
        {
            String msg = "Incoming supplierQuote is null";
            log.error(msg);
            throw new RuntimeException(msg);
        }
        if (supplierQuote.getSubtotalCost() == null)
        {
            String msg = "Subtotal Cost for supplier quote is null.  Cannot calculate tax.";
            supplierQuote.setStatus(Constants.SupplierRequestStatus.ERROR);
            log.error(msg);
            throw new RuntimeException(msg);
        }

        double tax = supplierQuote.getSubtotalCost() * (double)0.06;
        supplierQuote.setTax(roundPennies(tax));
    }

    public void calculateShipping(SupplierQuote supplierQuote)
    {
        if (supplierQuote == null)
        {
            String msg = "Incoming supplierQuote is null";
            log.error(msg);
            throw new RuntimeException(msg);
        }
        if (supplierQuote.getQuantity() == null)
        {
            String msg = "Quantity for incoming supplier quote is null";
            supplierQuote.setStatus(Constants.SupplierRequestStatus.ERROR);
            log.error(msg);
            throw new RuntimeException(msg);
        }

        double shippingPerUnit = Math.random() * 15.0;
        int quantity = supplierQuote.getQuantity().intValue();
        double shipping = shippingPerUnit * (double)quantity;

        supplierQuote.setShipping(roundPennies(shipping));
    }

    private double roundPennies(double amount)
    {
        return Math.round(amount * 100.0) / 100.0;
    }

    public void estimateShipDate(SupplierQuote supplierQuote)
    {
        if (supplierQuote == null)
        {
            String msg = "Incoming supplierQuote is null";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        int rand = (int)(Math.random() * 5.0);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, rand);
        supplierQuote.setEstimatedShipDate(cal.getTime());
    }

    public void finalizeQuote(SupplierQuote supplierQuote)
    {
        if (supplierQuote == null)
        {
            String msg = "Incoming supplierQuote is null.  Cannot finalize supplier quote.";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        double subtotal = 0;
        if (supplierQuote.getSubtotalCost() != null)
        {
            subtotal = supplierQuote.getSubtotalCost();
        }

        double tax = 0;
        if (supplierQuote.getTax() != null)
        {
            tax = supplierQuote.getTax();
        }

        double shipping = 0;
        if (supplierQuote.getShipping() != null)
        {
            shipping = supplierQuote.getShipping();
        }

        double total = subtotal + tax + shipping;
        supplierQuote.setTotalCost(roundPennies(total));

        supplierQuote.setStatus(Constants.SupplierRequestStatus.APPROVED);
    }
}
