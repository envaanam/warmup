package com.mc.co;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductsTest {

    @BeforeEach
    void setUp() {
        // Clearing everytime
        Products.getInstance().getAllItems().keySet().forEach(k -> Products.getInstance().removeItem(k));
    }

    @Test
    void testAddAllProducts() {
        Map<String, Integer> products = new HashMap<>();
        products.put("apple", 20);
        products.put("grapes", 50);
        Products.getInstance().addAllProducts(products);
        Map<String, Integer> actual = Products.getInstance().getAllItems();
        assertEquals(products, actual);
    }

    @Test
    void testEmpty() {
        Map<String, Integer> actual = Products.getInstance().getAllItems();
        assertTrue(actual.isEmpty());
    }

    @Test
    void testSingleAdd() {
        Products.getInstance().addProduct("grapes", 10);
        Map<String, Integer> items = Products.getInstance().getAllItems();
        assertEquals(1, items.size());
        assertEquals(10, items.get("grapes"));
    }

    @Test
    void testModification() {
        Products.getInstance().addProduct("grapes", 10);
        Map<String, Integer> items = Products.getInstance().getAllItems();
        assertEquals(1, Products.getInstance().getAllItems().size());
        items.put("apple", 20);
        assertEquals(1, Products.getInstance().getAllItems().size());
    }

    @Test
    void testInvalidItem() {
        Products.getInstance().addProduct("", 2);
        assertTrue(Products.getInstance().getAllItems().isEmpty());
    }

    @Test
    void testInvalidItemWithJustWhiteSpaces() {
        Products.getInstance().addProduct("  ", 2);
        assertTrue(Products.getInstance().getAllItems().isEmpty());
    }

    @Test
    void testInvalidPrice() {
        Products.getInstance().addProduct("grapes", -1);
        assertTrue(Products.getInstance().getAllItems().isEmpty());
    }

    @Test
    void testInvalidPriceInMap() {
        Map<String, Integer> products = new HashMap<>();
        products.put("apple", -1);
        Products.getInstance().addAllProducts(products);
        assertTrue(Products.getInstance().getAllItems().isEmpty());
    }

    @Test
    void testAddProduct() {
        Map<String, Integer> products = new HashMap<>();
        products.put("apple", 20);
        Products.getInstance().addAllProducts(products);
        Map<String, Integer> actual = Products.getInstance().getAllItems();
        assertEquals(products, actual);
        Products.getInstance().addProduct("grapes", 10);
        assertNotEquals(products, Products.getInstance().getAllItems());
        products.put("grapes", 10);
        assertEquals(products, Products.getInstance().getAllItems());
    }

}