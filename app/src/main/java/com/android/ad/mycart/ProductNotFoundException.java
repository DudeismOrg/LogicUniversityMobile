package com.android.ad.mycart;

/**
 * Created by rajeev on 19/1/2017.
 */

public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 43L;

    private static final String DEFAULT_MESSAGE = "Product is not found in the shopping cart.";

    public ProductNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
