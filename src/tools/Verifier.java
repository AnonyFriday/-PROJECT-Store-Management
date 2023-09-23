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
public abstract class Verifier {

    /**
     *
     * @param value: supplied value
     * @return true if positive than, or false
     */
    public final static boolean isPositive(int value) {
        return value > 0;
    }
    public final static boolean isPositive(double value) {
        return value > 0.0;
    }

    /**
     *
     * @param value: supplied value
     * @return true if negative than, or false
     */
    public final static boolean isNegative(int value) {
        return value < 0;
    }

    /**
     *
     * @param value: supplied value
     * @return true if greater than or equals to 0, or false
     */
    public final static boolean isGreaterThanEqualsToZero(int value) {
        return value >= 0;
    }
}
