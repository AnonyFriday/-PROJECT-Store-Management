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
     *
     * @param value: supplied value
     * @return true if positive than, or false
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
     *
     * @param value: supplied value
     * @return true if negative than, or false
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
