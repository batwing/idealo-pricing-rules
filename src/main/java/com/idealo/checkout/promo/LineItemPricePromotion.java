package com.idealo.checkout.promo;

import com.idealo.checkout.Cart;
import com.idealo.checkout.model.LineItem;

import java.math.BigDecimal;

public interface LineItemPricePromotion extends PricePromotionStrategy {

    BigDecimal getTotalPricePerItemWithDiscount(LineItem lineItem);

    default BigDecimal apply(Cart cart){
        return cart.getLineItems().stream()
                .filter(lineItem -> filter(lineItem))
                .map(lineItem -> getTotalPricePerItemWithDiscount(lineItem))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    default boolean filter(LineItem lineItem) {
        return true;
    }
}
