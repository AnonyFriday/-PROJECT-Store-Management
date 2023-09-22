/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 * A purchase receipt contains purchased product
 *
 * @author duyvu
 */
public class PurchaseReceipt implements Comparable<PurchaseReceipt> {

    // ======================================
    // = ATTRIBUTES
    // ======================================
    private Date purchaseDate;

    private String prID;
    private static int trackingID = 0;

    // ======================================
    // = CONSTRUCTORS
    // ======================================
    // Parameterized Constructor
    public PurchaseReceipt(String prID, Date purchaseDate) {
	this.prID = calculatePrID();
	this.purchaseDate = purchaseDate;
    }

    // ======================================
    // = METHODS
    // ======================================
    /**
     * Override the compareTo function to compare both PrID
     *
     * @param o: passed PR object
     * @return: 1,-1,0 depends on the compareTo function
     */
    @Override
    public int compareTo(PurchaseReceipt o) {
	return o.getPrID().compareTo(this.prID);
    }

    /**
     * Calculate automatically PrID
     *
     * @return a format string IMxxxxxx of the PrID
     */
    private String calculatePrID() {
	// Set for increment index ith
	return String.format("IM%07d", trackingID++);
    }

    // ======================================
    // = GETTERS & SETTERS
    // ======================================
    public String getPrID() {
	return prID;
    }

    public Date getPurchaseDate() {
	return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
	this.purchaseDate = purchaseDate;
    }
}
