package com.mc.co;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Checkout class which does the core checkout
 * Intentionally a singleton class
 */
public class Checkout {

    public static final String APPLE = "apple";
    public static final String ORANGE = "orange";
    private static Checkout INSTANCE;

    private Checkout() {
    }

    public static Checkout getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Checkout();
        }
        return INSTANCE;
    }

    /**
     * Takes in the list of items as String and returns the total price as String
     * @param cart - List of all items in the cart
     * @return - final total price after all the discounts
     */
    public String checkout(List<String> cart) {
        float val = 0.00f;

        if(!(cart == null || cart.isEmpty())) {
            // Total Price before the offers
            long sumInPence = cart.stream().map(String::toLowerCase).map(i -> Products.getInstance().getAllItems().get(i)).reduce(0, Integer::sum);

            // Factor in the offers

            Map<String, Integer> itemCount = cart.stream().collect(Collectors.toMap(k -> k, k -> 1, Integer::sum));
            int applesToBeDiscounted = itemCount.containsKey(APPLE) ? numberOfDiscountedItemsForBuy1Get1(itemCount.get(APPLE)) : 0;
            int orangesToBeDiscounted = itemCount.containsKey(ORANGE) ? numberOfDiscountedItemsFor3For2(itemCount.get(ORANGE)) : 0;

            long applesOfferInPence = (long) applesToBeDiscounted *  Products.getInstance().getAllItems().get(APPLE);
            long orangesOfferInPence = (long) orangesToBeDiscounted * Products.getInstance().getAllItems().get(ORANGE);

            sumInPence = sumInPence - applesOfferInPence - orangesOfferInPence;
            val = (float) sumInPence / 100;

        }

        return String.format("£%.2f", val);
    }

    /**
     * This can be refactored to be a single line like below
     * "return numberOfItems / 2"
     * provided if the access is restricted within this class using private.
     * However, I have made this public to enable testing
     * @param numberOfItems - number of items that qualifies for buy 1, get 1
     * @return number of items to be discounted
     */
    public static int numberOfDiscountedItemsForBuy1Get1(int numberOfItems) {
        int itemsToDiscount = 0;
        if(numberOfItems > 0 ) {
            itemsToDiscount = numberOfItems / 2;
        }
        return itemsToDiscount;
    }

    /**
     * This can be refactored to be a single line like below
     * "return numberOfItems / 3"
     * provided if the access is restricted within this class using private.
     * However, I have made this public to enable testing
     * @param numberOfItems - number of items that qualifies for this offer
     * @return - number of items to be discounted
     */
    public static int numberOfDiscountedItemsFor3For2(int numberOfItems) {
        int itemsToDiscount = 0;
        if(numberOfItems > 0) {
            itemsToDiscount = numberOfItems / 3;
        }
        return itemsToDiscount;
    }
}
