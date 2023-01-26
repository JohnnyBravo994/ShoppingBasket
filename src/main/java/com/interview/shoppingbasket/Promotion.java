package com.interview.shoppingbasket;

public class Promotion {
    // Not yet implemented
    String typePromotion, productCode;

    public Promotion(String typePromotion, String productCode) {
        this.typePromotion = typePromotion;
        this.productCode = productCode;
    }

    public String getTypePromotion() {
        return this.typePromotion;
    }

    public void setTypePromotion(String typePromotion) {
        this.typePromotion = typePromotion;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}
