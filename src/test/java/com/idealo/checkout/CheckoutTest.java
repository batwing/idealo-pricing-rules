package com.idealo.checkout;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CheckoutTest {

  public int calculatePrice(String goods) {
    CheckOut checkout = new CheckOut(rule);
    for (int i = 0; i < goods.length(); i++) {
      checkout.scan(String.valueOf(goods.charAt(i)));
    }
    return checkout.total;
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
    CheckOut checkout = new CheckOut(rule);
    assertEquals(0, checkout.total);
    checkout.scan("A");
    assertEquals(40, checkout.total);
    checkout.scan("B");
    assertEquals(90, checkout.total);
    checkout.scan("A");
    assertEquals(130, checkout.total);
    checkout.scan("A");
    assertEquals(150, checkout.total);
    checkout.scan("B");
    assertEquals(180, checkout.total);
  }
}
