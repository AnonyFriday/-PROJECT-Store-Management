/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.pureceipt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Function;
import inventory.products.Product;
import inventory.products.ProductInventory;
import tools.Constants;
import tools.NumberVerifier;
import tools.input.InputDateTools;
import tools.input.InputNumberTools;
import tools.input.InputStringTools;
import util.IProductManagement;

/**
 * A purchase receipt contains purchased products (Import)
 *
 * <br><br>Since the relationship between Product(1) and PurchaseReceipt(M) is association (1 to many), then this class
 * will include the array of products
 *
 * @author duyvu
 */
public class PurchaseReceipt implements Comparable<PurchaseReceipt> {

    // ======================================
    // = Attributes
    // ======================================
    private Date purchaseDate;

    private final String prID;
//    private static int trackingID = 0;

    // ======================================
    // = Constructor
    // ======================================   
    // Parameterized Constructor (No prID)
    // - Used at Adding new PurchaseReceipt
    public PurchaseReceipt(Date purchaseDate) {
	this.prID = calculatePrID();
	this.purchaseDate = purchaseDate;
    }

    // Parameterized Constructor 
    // - Used at Loading PurchaseReceipt from 
    public PurchaseReceipt(String prID,
	    Date purchaseDate) {
	this.prID = prID;
	this.purchaseDate = purchaseDate;
    }

    // ======================================
    // = Class-related Methods
    // ======================================
    /**
     * Override the compareTo function to compare both PrID
     *
     * @param o: passed pr object
     * @return: 1,-1,0 depends on the compareTo function
     */
    @Override
    public int compareTo(PurchaseReceipt o) {
	return o.getPrID().compareTo(this.prID);
    }

    /**
     * Calculate automatically PrID
     *
     * <br><br>Increase based on the list of Purchase Receipt to duplicated prID when loading a file
     *
     * @return a format string IMxxxxxxx of the PrID (x is 7 digits)
     */
    private String calculatePrID() {
	// Set for increment index ith
	int purchasingReceiptsInList = PurchaseReceiptList.getInstance().size();

	int trackingID = purchasingReceiptsInList;
	return String.format("IM%07d", trackingID++);
    }

    /**
     * Override the toString() function to output purchase receipt's fields
     * <br><br>(Used for writing to a text file for better performance)
     *
     * @return a purchase receipt's representative string
     */
    @Override
    public String toString() {
	return String.format("%s,%s",
		prID,
		InputDateTools.parseStringFromDate(purchaseDate, Constants.DATE_FORMAT));
    }

    // ======================================
    // = Getters & Setters
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
