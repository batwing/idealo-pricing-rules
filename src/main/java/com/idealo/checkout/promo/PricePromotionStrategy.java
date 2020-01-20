package com.idealo.checkout.promo;

import com.idealo.checkout.Cart;

import java.math.BigDecimal;

public interface PricePromotionStrategy {
    BigDecimal apply(Cart cart);
}
