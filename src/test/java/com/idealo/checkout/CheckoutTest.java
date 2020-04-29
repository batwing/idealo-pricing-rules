package com.idealo.checkout;

import static org.junit.Assert.assertEquals;

import com.idealo.checkout.model.Sku;
import com.idealo.checkout.promo.BuyFewGetNextWithDiscount;
import com.idealo.checkout.promo.BuyOneGetFixedDiscount;
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
  public static void init(){

    storage = new SkuStorage();
    storage.add(new Sku("A", new BigDecimal(40)));
    storage.add(new Sku("B", new BigDecimal(50)));
    storage.add(new Sku("C", new BigDecimal(25)));
    storage.add(new Sku("D", new BigDecimal(20)));
    storage.add(new Sku("E", new BigDecimal(50)));


    List<PricePromotionStrategy> promotionStrategies = new ArrayList<>();
    promotionStrategies.add(new BuyFewGetNextWithDiscount(new HashSet<>(Arrays.asList("A")),3,1,0.5));
    promotionStrategies.add(new BuyFewGetNextWithDiscount(new HashSet<>(Arrays.asList("B")),2,1,0.4));
    promotionStrategies.add(new BuyOneGetFixedDiscount(new HashSet<>(Arrays.asList("E")),10));

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
  public void shouldApplyFixedDiscountPerEachItem() {
    assertEquals(40, calculatePrice("EA").intValue());
    assertEquals(80, calculatePrice("EE").intValue());
  }

  @Test
  public void totals() {
    assertEquals(0, calculatePrice("").intValue());
    assertEquals(40, calculatePrice("A").intValue());
    assertEquals(90, calculatePrice("AB").intValue());
    assertEquals(135, calculatePrice("CDBA").intValue());
    assertEquals(80, calculatePrice("AA").intValue());
    assertEquals(100, calculatePrice("AAA").intValue());
    assertEquals(140, calculatePrice("AAAA").intValue());
    assertEquals(180, calculatePrice("AAAAA").intValue());
    assertEquals(200, calculatePrice("AAAAAA").intValue());
    assertEquals(150, calculatePrice("AAAB").intValue());
    assertEquals(180, calculatePrice("AAABB").intValue());
    assertEquals(200, calculatePrice("AAABBD").intValue());
    assertEquals(200, calculatePrice("DABABA").intValue());
  }

  @Test
  public void incremental() {
    CheckOut checkout = new CheckOut(rules);
    assertEquals(0, checkout.getTotal().intValue());
    checkout.scan("A");
    assertEquals(40, checkout.getTotal().intValue());
    checkout.scan("B");
    assertEquals(90, checkout.getTotal().intValue());
    checkout.scan("A");
    assertEquals(130, checkout.getTotal().intValue());
    checkout.scan("A");
    assertEquals(150, checkout.getTotal().intValue());
    checkout.scan("B");
    assertEquals(180, checkout.getTotal().intValue());
  }
}
