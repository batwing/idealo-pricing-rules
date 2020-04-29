package com.idealo.checkout.promo;

import com.idealo.checkout.Cart;
import com.idealo.checkout.model.LineItem;

public interface LineItemPricePromotion extends PricePromotionStrategy {

    void applyDiscount(LineItem lineItem);

    default void apply(Cart cart){
        cart.getLineItems().stream()
                .filter(lineItem -> filter(lineItem))
                .forEach(lineItem -> applyDiscount(lineItem));
    }

    default boolean filter(LineItem lineItem) {
        return true;
    }
}
