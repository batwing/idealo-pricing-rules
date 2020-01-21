package com.idealo.checkout.promo;

import com.idealo.checkout.model.LineItem;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;

@AllArgsConstructor
public class BuyFewGetNextWithDiscount implements LineItemPricePromotion {
    private HashSet<String> setOfSkuId;//TODO: rename to setOfPromoSkuId
    private int minCountOfSkuToGetDiscount;
    private int minCountOfSkuWithDiscount;
    private double discountValue;

    @Override
    public boolean filter(LineItem lineItem) {
        return setOfSkuId.contains(lineItem.getSku().id);
    }

    @Override
    public BigDecimal getTotalPricePerItemWithDiscount(LineItem lineItem){
        int totalSkuWithDiscountPerItem = (lineItem.getCount() / minCountOfSkuToGetDiscount) * minCountOfSkuWithDiscount;//divQuotient
        int totalSkuNoDiscountPerItem = lineItem.getCount() - totalSkuWithDiscountPerItem;//divRemainder
        return lineItem.getSku().price
                .multiply(BigDecimal.valueOf(totalSkuNoDiscountPerItem))
                .add(lineItem.getSku().price.multiply(BigDecimal.valueOf(totalSkuWithDiscountPerItem * (1-discountValue))));
    }
}
