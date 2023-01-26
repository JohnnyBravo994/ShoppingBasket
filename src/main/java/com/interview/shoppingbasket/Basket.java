package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
        // Exercise - implement this function
        for (int i = 0; i < getItems().size(); i++) {
            String codeItem = getItems().get(i).getProductCode();
            int quantityItem = getItems().get(i).getQuantity();
            for (int j = i + 1; j < getItems().size(); j++) {
                if (codeItem.equalsIgnoreCase(getItems().get(j).getProductCode())) {
                    quantityItem += getItems().get(j).getQuantity();
                    getItems().remove(j);
                }
            }
            getItems().get(i).setQuantity(quantityItem);
        }
    }
}
