/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salemng.expmng;

import java.util.Date;
import purchasingmng.pureceipt.PurchaseReceiptList;
import tools.Constants;
import tools.input.InputDateTools;

/**
 *
 * @author duyvu
 */
public final class BillOfSale implements Comparable<BillOfSale> {

    // ======================================
    // = Attributes
    // ======================================
    private final String bsID;
    private Date bsDate;

    // ======================================
    // = Constructor
    // ======================================
    // ID Only Constructor
    // - Used at equals() function
    public BillOfSale(String bsID) {
	this.bsID = bsID;
    }

    // Full Parameterize Constructor 
    // - Used at Loading Product from file
    public BillOfSale(String bsID, Date bsDate) {
	this.bsID = bsID;
	this.bsDate = bsDate;
    }

    // Full Parameterize Constructor (No bsID)
    // - Used at Adding function
    public BillOfSale(Date bsDate) {
	this.bsDate = bsDate;
	this.bsID = calculateBsID();
    }

    // ======================================
    // = Class-related Methods
    // ======================================
    /**
     * Override the compareTo function to compare both bsID
     *
     * @param o: passed bs object
     * @return: 1,-1,0 depends on the compareTo function
     */
    @Override
    public int compareTo(BillOfSale o) {
	return o.getBsID().compareTo(this.bsID);
    }

    /**
     * Calculate automatically bsID
     *
     * <br><br>Increase based on the list of Purchase Receipt to duplicated prID when loading a file
     *
     * @return a format string BSxxxxxx of the bsID (x is 6 digits)
     */
    private String calculateBsID() {
	// Set for increment index ith
	int billOfSaleInList = BillOfSaleList.getInstance().size();

	int trackingID = billOfSaleInList;
	return String.format("BS%06d", trackingID++);
    }

    /**
     * Override the toString() function to output bill of sale's fields
     * <br><br>(Used for writing to a text file for better performance)
     *
     * @return a bill of sale's representative string
     */
    @Override
    public String toString() {
	return String.format("%s,%s",
		bsID,
		InputDateTools.parseStringFromDate(bsDate, Constants.DATE_FORMAT));

    }

    // ======================================
    // = Getters & Setters
    // ======================================
    public String getBsID() {
	return bsID;
    }

    public Date getBsDate() {
	return bsDate;
    }

    public void setBsDate(Date bsDate) {
	this.bsDate = bsDate;
    }
}
