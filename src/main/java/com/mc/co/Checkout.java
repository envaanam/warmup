package com.mc.co;

import java.util.List;

public class Checkout {

    private static Checkout INSTANCE;

    private Checkout() {
    }

    public static Checkout getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Checkout();
        }
        return INSTANCE;
    }

    public String checkout(List<String> items) {
        float val = 0.00f;
        if(!(items == null || items.isEmpty())) {
            long sumInPence = items.stream().map(String::toLowerCase).map(i -> Products.getInstance().getAllItems().get(i)).reduce(0, Integer::sum);
            val = (float) sumInPence / 100;
        }

        return String.format("£%.2f", val);
    }
}
