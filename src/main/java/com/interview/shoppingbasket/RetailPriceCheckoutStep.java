package com.interview.shoppingbasket;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private PromotionsService promotionsService;
    private double retailTotal;

    public RetailPriceCheckoutStep(PricingService pricingService, PromotionsService promotionsService) {
        this.pricingService = pricingService;
        this.promotionsService = promotionsService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        retailTotal = 0.0;

        basket.consolidateItems();

        for (BasketItem basketItem : basket.getItems()) {
            // int quantity = basketItem.getQuantity();
            double price = pricingService.getPrice(basketItem.getProductCode());
            basketItem.setProductRetailPrice(price);
            for (Promotion promotion : promotionsService.getPromotions(basket)) {
                price = applyPromotion(promotion, basketItem, price);
            }

            retailTotal += price;
        }

        checkoutContext.setRetailPriceTotal(retailTotal);
    }

    public double applyPromotion(Promotion promotion, BasketItem item, double price) {
        /*
         * Implement applyPromotion method
         * price = price for the item
         * retailTotal = price * quantity of same item
         */
        if (promotion.getProductCode().equalsIgnoreCase(item.getProductCode())) {
            if (promotion.getTypePromotion().equalsIgnoreCase("2 items for the price of 1")) {
                if ((item.getQuantity() % 2) == 0) {
                    price = price / 2;
                    retailTotal = price * item.getQuantity();
                    return retailTotal;
                } else {
                    if (item.getQuantity() - 1 != 0) {
                        price = price / 2;
                        retailTotal = price * (item.getQuantity() - 1) + price;
                        return retailTotal;
                    } else {
                        return price;
                    }
                }
            } else if (promotion.getTypePromotion().equalsIgnoreCase("50% off retail price")) {
                price = price / 2;
                retailTotal = price * item.getQuantity();
                return retailTotal;
            } else if (promotion.getTypePromotion().equalsIgnoreCase("10% off retail price")) {
                price = price / 10;
                double priceTotal = price * 9;
                retailTotal = priceTotal * item.getQuantity();
                return retailTotal;
            }
        }
        return retailTotal * item.getQuantity();
    }
}
