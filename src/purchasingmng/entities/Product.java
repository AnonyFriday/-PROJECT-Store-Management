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
public class Product implements Comparable<Product> {

    // ======================================
    // = ATTRIBUTES
    // ======================================
    // Variables
    private String name;
    private double purchasePrice;
    private int initialQuantity;
    private int curQuantity;
    private Date productionDate;
    private Date expirationDate;
    private boolean continuous;

    // prID as the foreign-key from the Purchase Receipt table
    private String prID;

    // Static Variables
    private static int trackingID = 0;
    private String pID;

    // ======================================
    // = CONSTRUCTOR
    // ======================================	
    public Product() {

    }

    // ID Only Constructor
    public Product(String pID) {
	this.pID = pID;
    }

    // Full Parameterize Constructor
    public Product(String name, double purchasePrice, short initialQuantity,
	    short curQuantity, Date productionDate, Date expirationDate,
	    boolean continuous, String prID) {
	this.name = name;
	this.purchasePrice = purchasePrice;
	this.initialQuantity = initialQuantity;
	this.curQuantity = curQuantity;
	this.productionDate = productionDate;
	this.expirationDate = expirationDate;
	this.continuous = continuous;

	this.pID = calculatePID();
	this.prID = prID;
    }

    // ======================================
    // = METHODS
    // ======================================
    /**
     * Automatically increase and retrieve the the PID as a string
     *
     * <br><br>e.g. P000000, P0000001
     *
     * @return a string-typed PID
     */
    private String calculatePID() {
	if (trackingID == 0) {
	    // Set for index 0th
	    return String.format("P%06d", trackingID++);
	} else {
	    // Set for increment index ith
	    return String.format("P%06d", ++trackingID);
	}
    }

    /**
     * Override the toString() function to output product's fields
     *
     * @return a product-formatted representative string
     */
    @Override
    public String toString() {
	return String.format("%s,  %s,  %.4f,  %d,  %d,  %s,  %s,  %b,  %s",
		pID, name, purchasePrice, initialQuantity,
		curQuantity, productionDate, expirationDate, continuous, prID);
    }

    /**
     * Override the equals function by checking equal on pid to check if both are equal or not
     *
     * @param obj
     * @return true if matching, or false
     */
    @Override
    public boolean equals(Object obj) {
	return this.getPID().equals(((Product) obj).getPID());
    }

    /**
     * Override the compare function by comparing pid of both product
     *
     * @param o: a passed product
     * @return -1,0,1 depends on the compare function
     */
    @Override
    public int compareTo(Product o) {
	return this.getPID().compareTo(o.getPID());
    }

    // ======================================
    // = SETTERS & GETTERS
    // ======================================
    public String getPID() {
	return String.format("P06%d", pID);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getPurchasePrice() {
	return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
	this.purchasePrice = purchasePrice;
    }

    public int getInitialQuantity() {
	return initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) {
	this.initialQuantity = initialQuantity;
    }

    public int getCurQuantity() {
	return curQuantity;
    }

    public void setCurQuantity(int curQuantity) {
	this.curQuantity = curQuantity;
    }

    public Date getProductionDate() {
	return productionDate;
    }

    public void setProductionDate(Date productionDate) {
	this.productionDate = productionDate;
    }

    public Date getExpirationDate() {
	return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
	this.expirationDate = expirationDate;
    }

    public boolean isContinuous() {
	return continuous;
    }

    public void setContinuous(boolean continuous) {
	this.continuous = continuous;
    }

    public String getPrID() {
	return prID;
    }

    public void setPrID(String prID) {
	this.prID = prID;
    }

    public static void main(String[] args) {
	Product product = new Product();

	// Testing ID
	System.out.println(product.calculatePID());
	System.out.println(product.calculatePID());
	System.out.println(product.calculatePID());
    }

}
