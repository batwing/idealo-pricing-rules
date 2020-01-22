package com.idealo.checkout;

import java.math.BigDecimal;

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
    pricingRules.getPromotionStrategies().forEach(promo -> promo.apply(shoppingCart));
    total = shoppingCart.getLineItems().stream()
            .map(lineItem -> lineItem.getTotal())
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO);
  }

  public BigDecimal getTotal() {
    return total;
  }
}
