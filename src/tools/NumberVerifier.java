/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.util.function.BiConsumer;

/**
 * A common verifier function for some specific case
 *
 * @author duyvu
 */
public abstract class NumberVerifier {

    /**
     * Check if the number is greater than or equals to the number
     *
     * @param value: supplied value
     * @return true if greater than, or false
     */
    public final static boolean isGreaterThanEqualsTo(int value,
                                                      int marker) {
        return value >= marker;
    }

    public final static boolean isGreaterThanEqualsTo(double value,
                                                      double marker) {
        return value >= marker;
    }

    /**
     * Check if the number is less than or equals to the number
     *
     * @param value: supplied value
     * @return true if less than, or false
     */
    public final static boolean isLessThanEqualsTo(int value,
                                                   int marker) {
        return value <= marker;
    }

    public final static boolean isLessThanEqualsTo(double value,
                                                   double marker) {
        return value <= marker;
    }
}
