package com.glroland.orders.util;

public class Constants 
{
    public static final int SKU_MIN_LENGTH = 3;
    public static class IncomingOrderStatus
    {
        public static final String NEW = "NEW";
        public static final String INVALID = "INVALID";
        public static final String FRAUD = "FRAUD";
        public static final String READY = "READY";
        public static final String ERROR = "ERROR";
    }

    public static class SupplierTypes
    {
        public static final String AUCTION = "A";
        public static final String CONSULTING = "C";
        public static final String STANDARD = "S";
        public static final String TRAINING = "T";
    }

    public static class SupplierRequestStatus
    {
        public static final String NEW = "NEW";
        public static final String ERROR = "ERROR";
        public static final String APPROVED = "APPROVED";
    }
}
