package com.glroland.orders.service;

import javax.enterprise.context.ApplicationScoped;

import com.glroland.orders.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ProductService 
{
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public String getSupplierTypeForProduct(String sku) 
    {
        if ((sku == null) || (sku.length() == 0))
        {
            String msg = "Invalid input SKU - empty or null";
            log.error(msg);
            throw new RuntimeException(msg);
        }
        if (sku.length() < Constants.SKU_MIN_LENGTH)
        {
            String msg = "Invalid input SKU - too short";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        // supplier type is denoted as the first character of the SKU for the purposes of this POC
        String supplierType = sku.substring(0, 1);
        if (!Constants.SupplierTypes.AUCTION.equals(supplierType)
            && !Constants.SupplierTypes.CONSULTING.equals(supplierType)
            && !Constants.SupplierTypes.STANDARD.equals(supplierType)
            && !Constants.SupplierTypes.TRAINING.equals(supplierType))
        {
            String msg = "SKU has an invalid supplier type!  SKU=" + sku + " SupplierType=" + supplierType;
            log.error(msg);
            throw new RuntimeException(msg);
        }

        return supplierType;
    }
}
