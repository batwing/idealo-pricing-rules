package com.idealo.checkout;

public interface PricePromotionStrategy {
    int apply(ShoppingCart cart);
}
