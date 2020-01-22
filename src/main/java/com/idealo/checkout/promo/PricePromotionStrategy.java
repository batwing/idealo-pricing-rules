package com.idealo.checkout.promo;

import com.idealo.checkout.Cart;

import java.math.BigDecimal;
import java.util.Optional;

public interface PricePromotionStrategy {
    void apply(Cart cart);
}
