package com.mc.co;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
        assertEquals("£1.45", Checkout.getInstance().checkout(cartItems));
    }

    @Test
    void checkoutScenario2() {
        List<String> cartItems = Arrays.asList("orange", "orange", "orange", "apple", "apple");
        assertEquals("£1.10", Checkout.getInstance().checkout(cartItems));
    }

    @Test
    void checkoutScenarioWithOnlyOranges() {
        List<String> cartItems = Arrays.asList("orange", "orange", "orange");
        assertEquals("£0.50", Checkout.getInstance().checkout(cartItems));
    }

    @Test
    void checkoutScenarioWithOnlyApples() {
        List<String> cartItems = Arrays.asList("apple", "apple", "apple");
        assertEquals("£1.20", Checkout.getInstance().checkout(cartItems));
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

    @ParameterizedTest
    @CsvSource(value = {"0:0", "1:0", "2:1", "3:1", "51:25"}, delimiter = ':')
    void testNumberOfDiscountedItemsForBuy1Get1WithNItems(int input, int expected) {
        assertEquals(expected, Checkout.numberOfDiscountedItemsForBuy1Get1(input));
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0", "2:0", "3:1", "5:1", "10:3"}, delimiter = ':')
    void testNumberOfDiscountedItemsFor3For2WithNItems(int input, int expected) {
        assertEquals(expected, Checkout.numberOfDiscountedItemsFor3For2(input));
    }

}