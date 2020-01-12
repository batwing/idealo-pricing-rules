package com.idealo.checkout;

import java.util.Map;

public class CheckOut {
  private PricingRules pricingRules;
  private Map<String, Integer> shoppingCart;
  private int total = 0;

  public CheckOut(PricingRules pricingRules) {
    this.pricingRules = pricingRules;
  }

  public void scan(String skuId){

  }
}
