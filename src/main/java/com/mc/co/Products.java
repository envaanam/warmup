package com.mc.co;

import java.util.HashMap;
import java.util.Map;

/**
 * This can easily be represented as an enum ( or record ) to start with
 * as there are only two items with item and price.
 * However, I am using Map as the retrieval based on the item is O(1) which obviously will be used more often ( get )
 * Also, We want only one products instance and hence this should be a singleton
 */

public class Products {

    private static Products INSTANCE;

    final private Map<String, Integer> allItems;

    private Products() {
        allItems = new HashMap<>();
    }

    public static Products getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Products();

        }

        return INSTANCE;
    }

    public void addAllProducts(Map<String, Integer> items) {
        items.entrySet().stream().filter(e -> isValid(e.getKey(), e.getValue())).forEach(e -> allItems.put(e.getKey(), e.getValue()));
    }

    public void addProduct(String item, int priceInPence) {
        if(isValid(item, priceInPence)) {
             allItems.put(item.toLowerCase(), priceInPence);
        }
    }

    public static boolean isValid(String item, int priceInPence) {
        return item != null && !item.isBlank() && priceInPence > 0;
    }

    public Map<String, Integer> getAllItems() {
        return new HashMap<>(allItems);
    }

    public void removeItem(String key) {
        allItems.remove(key);
    }
}
