package com.idealo.checkout;

import static org.junit.Assert.assertEquals;

import com.idealo.checkout.model.Sku;
import com.idealo.checkout.promo.BuyFewGetNextWithDiscount;
import com.idealo.checkout.promo.PricePromotionStrategy;
import com.idealo.checkout.storage.SkuStorage;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CheckoutTest {
  private static PricingRules rules;
  private static SkuStorage storage;

  @BeforeClass
  public void init(){

    storage = new SkuStorage();
    storage.add(new Sku("A", new BigDecimal(40)));
    storage.add(new Sku("B", new BigDecimal(50)));
    storage.add(new Sku("C", new BigDecimal(25)));
    storage.add(new Sku("D", new BigDecimal(20)));

    List<PricePromotionStrategy> promotionStrategies = new ArrayList<>();
    promotionStrategies.add(new BuyFewGetNextWithDiscount(new HashSet<>(Arrays.asList("A")),3,1,0.5));
    promotionStrategies.add(new BuyFewGetNextWithDiscount(new HashSet<>(Arrays.asList("B")),2,1,0.4));

    rules = new PricingRules(storage, promotionStrategies);
  }

  public BigDecimal calculatePrice(String goods) {
    CheckOut checkout = new CheckOut(rules);
    for (int i = 0; i < goods.length(); i++) {
      checkout.scan(String.valueOf(goods.charAt(i)));
    }
    return checkout.getTotal();
  }

  @Test
  public void totals() {
    assertEquals(0, calculatePrice(""));
    assertEquals(40, calculatePrice("A"));
    assertEquals(90, calculatePrice("AB"));
    assertEquals(135, calculatePrice("CDBA"));
    assertEquals(80, calculatePrice("AA"));
    assertEquals(100, calculatePrice("AAA"));
    assertEquals(140, calculatePrice("AAAA"));
    assertEquals(180, calculatePrice("AAAAA"));
    assertEquals(200, calculatePrice("AAAAAA"));
    assertEquals(150, calculatePrice("AAAB"));
    assertEquals(180, calculatePrice("AAABB"));
    assertEquals(200, calculatePrice("AAABBD"));
    assertEquals(200, calculatePrice("DABABA"));
  }

  @Test
  public void incremental() {
    CheckOut checkout = new CheckOut(rules);
    assertEquals(0, checkout.getTotal());
    checkout.scan("A");
    assertEquals(40, checkout.getTotal());
    checkout.scan("B");
    assertEquals(90, checkout.getTotal());
    checkout.scan("A");
    assertEquals(130, checkout.getTotal());
    checkout.scan("A");
    assertEquals(150, checkout.getTotal());
    checkout.scan("B");
    assertEquals(180, checkout.getTotal());
  }
}
