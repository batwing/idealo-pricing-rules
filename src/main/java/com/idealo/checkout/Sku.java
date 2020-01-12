package com.idealo.checkout;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Sku {
  String id;
  BigDecimal price;
  String name;
}
