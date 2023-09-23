/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import javax.print.attribute.HashAttributeSet;

/**
 * A common verifier function for some specific case
 *
 * @author duyvu
 */
public abstract class Verifier {

    /**
     *
     * @param value: supplied value
     * @param target: a target to be compared
     * @return true if greater than, or false
     */
    public final static boolean isGreaterThan(int value,
                                              int target) {
        return value > target;
    }

    /**
     *
     * @param value: supplied value
     * @param target: a target to be compared
     * @return true if less than, or false
     */
    public final static boolean isLessThan(int value,
                                           int target) {
        return value < target;
    }

    /**
     *
     * @param value: supplied value
     * @param target: a target to be compared
     * @return true if both are equals, or false
     */
    public final static boolean isEqualsTo(int value,
                                           int target) {
        return value == target;
    }
}
