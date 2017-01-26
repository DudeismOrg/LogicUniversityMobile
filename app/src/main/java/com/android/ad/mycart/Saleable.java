package com.android.ad.mycart;

/**
 * Created by rajeev on 19/1/2017.
 */
import java.math.BigDecimal;

/**
 * Implements this interface for any product which can be added to shopping cart
 */
public interface Saleable {
    BigDecimal getPrice();

    String getName();
}