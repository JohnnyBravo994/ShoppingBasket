package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class CheckoutPipelineTest {

    CheckoutPipeline checkoutPipeline;

    @Mock
    Basket basket;

    @Mock
    CheckoutStep checkoutStep1;

    @Mock
    CheckoutStep checkoutStep2;

    @BeforeEach
    void setup() {
        checkoutPipeline = new CheckoutPipeline();
    }

    @Test
    void returnZeroPaymentForEmptyPipeline() {
        PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);

        assertEquals(paymentSummary.getRetailTotal(), 0.0);
    }

    @Test
    void executeAllPassedCheckoutSteps() {
        // Exercise - implement testing passing through all checkout steps

        Basket basket = new Basket();
        basket.add("productCode", "myProduct", 10);
        basket.add("productCode2", "myProduct2", 10);
        basket.add("productCode3", "myProduct3", 10);
        basket.add("productCode2", "myProduct2", 15);
        basket.add("productCode", "myProduct323", 5);

        basket.consolidateItems();

        checkoutPipeline.addStep(checkoutStep1);
        checkoutPipeline.addStep(checkoutStep1);

        PaymentSummary paymentWeGot = checkoutPipeline.checkout(basket);

        PaymentSummary paymentSummary = new PaymentSummary(15 * basket.getItems().get(0).getProductRetailPrice()
                + 25 * basket.getItems().get(1).getProductRetailPrice()
                + 10 * basket.getItems().get(2).getProductRetailPrice());

        assertEquals(paymentWeGot, paymentSummary);
    }

}
