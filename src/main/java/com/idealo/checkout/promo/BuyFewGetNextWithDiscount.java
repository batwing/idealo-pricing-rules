package com.idealo.checkout.promo;

import com.idealo.checkout.model.LineItem;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;

@AllArgsConstructor
public class BuyFewGetNextWithDiscount implements LineItemPricePromotion {
    private HashSet<String> setOfSkuId;
    private int minCountOfSkuToGetDiscount;
    private int minCountOfSkuUnderDiscount;
    private double discountValue;

    @Override
    public boolean filter(LineItem lineItem) {
        return setOfSkuId.contains(lineItem.getSku().id);
    }

    @Override
    public BigDecimal getTotalPricePerItemWithDiscount(LineItem lineItem){
        int totalSkuUnderDiscountPerItem = (lineItem.getCount() / minCountOfSkuToGetDiscount) * minCountOfSkuUnderDiscount;//divQuotient
        int totalSkuWithoutDiscountPerItem = lineItem.getCount() - totalSkuUnderDiscountPerItem;//divRemainder
        return lineItem.getSku().price
                .multiply(BigDecimal.valueOf(totalSkuWithoutDiscountPerItem))
                .add(lineItem.getSku().price.multiply(BigDecimal.valueOf(totalSkuUnderDiscountPerItem * (1-discountValue))));
    }
}
