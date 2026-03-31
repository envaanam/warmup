package com.mc.co;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    @BeforeAll
    static void setUp() {
        Map<String, Integer> testProducts = new HashMap<>();
        testProducts.put("apple", 60);
        testProducts.put("orange", 25);
        Products.getInstance().addAllProducts(testProducts);
    }

    @Test
    void checkout() {
        List<String> cartItems = Arrays.asList("orange", "apple", "apple", "apple");
        assertEquals("£2.05", Checkout.getInstance().checkout(cartItems));
    }

    @Test
    void checkoutScenario2() {
        List<String> cartItems = Arrays.asList("orange", "orange", "apple", "apple");
        assertEquals("£1.70", Checkout.getInstance().checkout(cartItems));
    }

    @Test
    void testEmpty() {
        List<String> cartItems = List.of();
        assertEquals("£0.00", Checkout.getInstance().checkout(cartItems));
    }

    @Test
    void testNull() {
        assertEquals("£0.00", Checkout.getInstance().checkout(null));
    }
}