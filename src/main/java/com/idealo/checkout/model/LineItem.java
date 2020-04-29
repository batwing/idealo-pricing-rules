package com.idealo.checkout.model;

import com.idealo.checkout.promo.PricePromotionStrategy;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LineItem {
    private Sku sku;
    private int count;
    private BigDecimal total;
    private List<Class<? extends PricePromotionStrategy>> appliedPromotions = new ArrayList<>();

    public LineItem(Sku sku, int count) {
        this.sku = sku;
        this.count = count;
        total = sku.getPrice().multiply(BigDecimal.valueOf(count));
    }
}
