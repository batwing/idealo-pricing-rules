package com.idealo.checkout.storage;

import com.idealo.checkout.model.Sku;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class SkuStorage {
    private Map<String, Sku> storage = new ConcurrentHashMap<>();

    public void add(Sku sku){
        storage.putIfAbsent(sku.id, sku);
    }

    public Sku findById(String skuId) throws NoSuchElementException {
        if (!storage.containsKey(skuId))
            throw new NoSuchElementException("There is not SKU with id = " + skuId);

        return storage.get(skuId);
    }

    public void deleteById(String skuId){
        if (!storage.containsKey(skuId))
            throw new NoSuchElementException("There is not SKU with id = " + skuId);

        storage.remove(skuId);
    }
}
