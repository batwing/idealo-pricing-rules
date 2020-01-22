package com.idealo.checkout;

import com.idealo.checkout.model.LineItem;
import com.idealo.checkout.model.Sku;
import com.idealo.checkout.storage.SkuStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class Cart {

    private SkuStorage storage;
    private List<LineItem> lineItems = new ArrayList<>();
    private Date date = new Date();

    public Cart(SkuStorage storage) {
        this.storage = storage;
    }

    public void addSku(String skuId){
        Sku sku = storage.findById(skuId);

        Optional<LineItem> lineItem = lineItems.stream()
                .filter(item -> skuId.equals(item.getSku().id))
                .findFirst();

        if (lineItem.isPresent()) {
            lineItem.get().setCount(lineItem.get().getCount() + 1);
        }
        else {
            lineItems.add(new LineItem(sku, 1));
        }
    }
}
