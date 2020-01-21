package com.idealo.checkout.promo;

import com.idealo.checkout.Cart;
import com.idealo.checkout.model.LineItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface LineItemPricePromotion extends PricePromotionStrategy {

    BigDecimal getTotalPricePerItemWithDiscount(LineItem lineItem);

    default Optional<BigDecimal> apply(Cart cart){
        List<BigDecimal> listOfTotalsPerItemWithDiscount =  cart.getLineItems().stream()
                .filter(lineItem -> filter(lineItem))
                .map(lineItem -> getTotalPricePerItemWithDiscount(lineItem))
                .collect(Collectors.toList());

        return listOfTotalsPerItemWithDiscount.size() == 0
            ? Optional.empty()
            : Optional.of(listOfTotalsPerItemWithDiscount.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add));

    }

    default boolean filter(LineItem lineItem) {
        return true;
    }
}
