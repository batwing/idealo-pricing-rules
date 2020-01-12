package com.idealo.checkout;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
@AllArgsConstructor
public class PricingRule {
  RuleType ruleType;
  Sku sku;


}
