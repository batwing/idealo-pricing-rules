package com.idealo.checkout.promo;

import com.idealo.checkout.model.LineItem;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;

@AllArgsConstructor
public class BuyFewGetNextWithDiscount implements LineItemPricePromotion {
    private HashSet<String> setOfPromoSkuId;
    private int minCountOfSkuToGetDiscount;
    private int minCountOfSkuWithDiscount;
    private double discountValue;

    @Override
    public boolean filter(LineItem lineItem) {
        return setOfPromoSkuId.contains(lineItem.getSku().getId());
    }

    @Override
    public void applyDiscount(LineItem lineItem){
        BigDecimal skuPrice = lineItem.getSku().getPrice();
        int totalSkuWithDiscountPerItem = (lineItem.getCount() / minCountOfSkuToGetDiscount) * minCountOfSkuWithDiscount;
        int totalSkuNoDiscountPerItem = lineItem.getCount() - totalSkuWithDiscountPerItem;
        lineItem.setTotal(
                skuPrice
                        .multiply(BigDecimal.valueOf(totalSkuNoDiscountPerItem))
                        .add(skuPrice
                                .multiply(BigDecimal.valueOf(totalSkuWithDiscountPerItem * (1-discountValue)))
                        )
        );

        if (lineItem.getTotal().compareTo(
                skuPrice.multiply(BigDecimal.valueOf(lineItem.getCount()))) < 0) {
            lineItem.getAppliedPromotions().add(this.getClass());
        }
    }
}
