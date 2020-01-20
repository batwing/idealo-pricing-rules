package com.idealo.checkout.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LineItem {
    private Sku sku;
    private int count;
}
