/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.entities;

import java.util.Date;

/**
 *
 * @author duyvu
 */
public class PurchaseReceipt implements Comparable<PurchaseReceipt> {

    // ======================================
    // = ATTRIBUTES
    // ======================================
    private String prID;
    private Date purchaseDate;

    // ======================================
    // = CONSTRUCTORS
    // ======================================
    // Parameterized Constructor
    public PurchaseReceipt(String prID, Date purchaseDate) {
	this.prID = prID;
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

    // ======================================
    // = GETTERS & SETTERS
    // ======================================
    public String getPrID() {
	return prID;
    }

    public void setPrID(String prID) {
	this.prID = prID;
    }

    public Date getPurchaseDate() {
	return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
	this.purchaseDate = purchaseDate;
    }

}
