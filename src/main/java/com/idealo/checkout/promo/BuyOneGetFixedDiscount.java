package com.idealo.checkout.promo;

import java.math.BigDecimal;
import java.util.HashSet;
import com.idealo.checkout.model.LineItem;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BuyOneGetFixedDiscount implements LineItemPricePromotion {
    private HashSet<String> setOfPromoSkuId;
    private double discountValue;

    @Override
    public boolean filter(LineItem lineItem) {
        return setOfPromoSkuId.contains(lineItem.getSku().getId());
    }

    @Override
    public void applyDiscount(LineItem lineItem){
        BigDecimal skuPrice = lineItem.getSku().getPrice().subtract(BigDecimal.valueOf(discountValue));
        lineItem.setTotal(skuPrice.multiply(BigDecimal.valueOf(lineItem.getCount())));

        if (lineItem.getTotal().compareTo(
                skuPrice.multiply(BigDecimal.valueOf(lineItem.getCount()))) < 0) {
            lineItem.getAppliedPromotions().add(this.getClass());
        }
    }
}
