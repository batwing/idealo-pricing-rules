package com.idealo.checkout;

import com.idealo.checkout.promo.PricePromotionStrategy;
import com.idealo.checkout.storage.SkuStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
public class PricingRules {
  private SkuStorage storage;
  private List<PricePromotionStrategy> promotionStrategies;

  public SkuStorage getStorage() {
    return storage;
  }

  public void setStorage(SkuStorage storage) {
    this.storage = storage;
  }

  public List<PricePromotionStrategy> getPromotionStrategies() {
    return promotionStrategies;
  }

  public void setPromotionStrategies(List<PricePromotionStrategy> promotionStrategies) {
    this.promotionStrategies = promotionStrategies;
  }
}
