package com.idealo.checkout.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Sku {
  private String id;
  private BigDecimal price;
}
