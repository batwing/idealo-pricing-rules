package com.idealo.checkout;

import java.math.BigDecimal;
import java.util.Comparator;

public class CheckOut {
  private PricingRules pricingRules;
  private Cart shoppingCart;
  private BigDecimal total = BigDecimal.ZERO;

  public CheckOut(PricingRules pricingRules) {
    this.pricingRules = pricingRules;
    shoppingCart = new Cart(pricingRules.getStorage());
  }

  public void scan(String skuId){
    shoppingCart.addSku(skuId);
    total = pricingRules.getPromotionStrategies().stream()
            .map(promo -> promo.apply(shoppingCart))
            .min(Comparator.naturalOrder())
            .orElse(BigDecimal.ZERO);
  }

  public BigDecimal getTotal() {
    return total;
  }
}
