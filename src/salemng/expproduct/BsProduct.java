/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salemng.expproduct;

import tools.Constants;
import tools.input.InputDateTools;

/**
 *
 * @author duyvu
 */
public final class BsProduct {

    // ======================================
    // = Attributes
    // ======================================
    private String bsID;
    private String pID;
    private double bsPrice;
    private int bsQuantity;

    // ======================================
    // = Constructor
    // ======================================
    // Full Parameterize Constructor 
    // - Used at Loading BsProduct from file
    // - USed at Adding BsProduct function
    public BsProduct(String bsID, String pID, double bsPrice, int bsQuantity) {
	this.bsID = bsID;
	this.pID = pID;
	this.bsPrice = bsPrice;
	this.bsQuantity = bsQuantity;
    }

    // ======================================
    // = Class-related Methods
    // ======================================
    /**
     * Override the toString() function to output bs product's fields
     * <br><br>(Used for writing to a text file for better performance)
     *
     * @return a bill of sale's representative string
     */
    @Override
    public String toString() {
	return String.format("%s,%s,%.2f,%d",
		bsID, pID, bsPrice, bsQuantity);
    }

    /**
     * Drawing a formatted table to represent all attribute of each product
     * <br><br>(Used for writing to a text file for better performance)
     *
     */
    public void displayBsProducttWithFormat() {
	Constants.DRAWING_DYNAMIC_LINE_WITH_CONTENT(77, () -> {
	    System.out.println(String.format(
		    "\t\t| Bill Of Sale ID "
		    + "| Product ID        "
		    + "| Selling Price     "
		    + "| Selling Quantity  "
	    ));

	    System.out.println(String.format(
		    "\t\t| %-13s "
		    + "| %-17s "
		    + "| %.2f $%-7.2s "
		    + "| %-10d ",
		    bsID, pID, bsPrice, " ", bsQuantity
	    ));
	});
    }

    // ======================================
    // = Getters & Setters Methods
    // ======================================
    public String getBsID() {
	return bsID;
    }

    public String getpID() {
	return pID;
    }

    public double getBsPrice() {
	return bsPrice;
    }

    public void setBsPrice(double bsPrice) {
	this.bsPrice = bsPrice;
    }

    public int getBsQuantity() {
	return bsQuantity;
    }

    public void setBsQuantity(int bsQuantity) {
	this.bsQuantity = bsQuantity;
    }
}
