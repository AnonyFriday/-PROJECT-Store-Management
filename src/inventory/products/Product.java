/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.products;

import java.util.Date;
import tools.Constants;
import tools.input.InputDateTools;

/**
 *
 * @author duyvu
 */
public class Product implements Comparable<Product> {

    // ======================================
    // = Attributes
    // ======================================
    // Static Variables
//    private static int trackingID = 0;   // ERROR: Not work on loading file, see the function calculatePrID below for new algorithm
    private final String pID;

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

    // ======================================
    // = Constructor
    // ======================================
    // ID Only Constructor
    // - Used at equals() function
    public Product(String pID) {
	this.pID = pID;
    }

    // Full Parameterize Constructor 
    // - Used at Loading Product from file
    public Product(
	    String pID,
	    String name,
	    double purchasePrice,
	    int initialQuantity,
	    int curQuantity,
	    Date productionDate,
	    Date expirationDate,
	    boolean continuous,
	    String prID) {
	this.pID = pID;
	this.name = name;
	this.purchasePrice = purchasePrice;
	this.initialQuantity = initialQuantity;
	this.curQuantity = curQuantity;
	this.productionDate = productionDate;
	this.expirationDate = expirationDate;
	this.continuous = continuous;
	this.prID = prID;
    }

    // Full Parameterize Constructor (No pID)
    // - Used at Adding function
    public Product(String name,
	    double purchasePrice,
	    int initialQuantity,
	    Date productionDate,
	    Date expirationDate,
	    String prID) {
	this.name = name;
	this.purchasePrice = purchasePrice;
	this.initialQuantity = initialQuantity;

	// By default, current quantity equals to initial quantity (Derived Attribute)
	this.curQuantity = initialQuantity;
	this.productionDate = productionDate;
	this.expirationDate = expirationDate;

	// If curr quantity is 0 then discontinue the product (Derived Attribute)
	this.continuous = true;

	this.pID = calculatePID();
	this.prID = prID;
    }

    // ======================================
    // = Class-related Methods
    // ======================================
    /**
     * Automatically increase and retrieve the the PID as a string
     *
     * <br><br>Since the ID has been retrieved from file, it can't be track automatically, which resolves in duplicated
     * pID when adding a new product. Solution is to calculate the tracking ID on the size of the ProductInventory list
     *
     *
     *
     * <br><br>e.g. P000000, P0000001
     *
     * @return a string-typed PID
     */
    private String calculatePID() {
	int productsInInventory = ProductInventory.getInstance().size();

	int trackingID = productsInInventory;
	// Set for increment index ith
	return String.format("P%06d", trackingID++);
    }

    /**
     * Override the toString() function to output product's fields
     * <br><br>(Used for writing to a text file for better performance)
     *
     * @return a product's representative string
     */
    @Override
    public String toString() {
	return String.format("%s,%s,%f,%d,%d,%s,%s,%b,%s",
		pID, name, purchasePrice, initialQuantity,
		curQuantity,
		InputDateTools.parseStringFromDate(productionDate, Constants.DATE_FORMAT),
		InputDateTools.parseStringFromDate(expirationDate, Constants.DATE_FORMAT),
		continuous, prID);
    }

    /**
     * Drawing a formatted table to represent all attribute of each product
     * <br><br>(Used for writing to a text file for better performance)
     *
     * @return a product-formatted representative table
     */
    public void displayProductWithFormat() {
	Constants.DRAWING_DYNAMIC_LINE_WITH_CONTENT(77, () -> {
	    System.out.println(String.format(
		    "\t\t| Product ID    "
		    + "| Name              "
		    + "| Purchase Price "
		    + "| Initial Q. "
		    + "| Current Q. |"
	    ));

	    System.out.println(String.format("\t\t| %-13s "
		    + "| %-17s "
		    + "| %.2f $%-7.2s "
		    + "| %-10d "
		    + "| %-10d |", pID, name, purchasePrice, " ", initialQuantity,
		    curQuantity
	    ));

	    Constants.DRAWING_FIXED_TABLE_EDGE_LINE(77);

	    System.out.println(String.format(
		    "\t\t| Production D. "
		    + "| Expiry D.         "
		    + "| Is Con.        "
		    + "| Import ID  "
		    + "| %10s |", " "));

	    System.out.println(String.format(
		    "\t\t| %-13s "
		    + "| %-17s "
		    + "| %-14b "
		    + "| %-10s "
		    + "| %10s |",
		    InputDateTools.parseStringFromDate(productionDate, Constants.DATE_FORMAT),
		    InputDateTools.parseStringFromDate(expirationDate, Constants.DATE_FORMAT),
		    continuous, prID, " "));
	}
	);
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
    // = Getters & Setters Methods
    // ======================================
    public String getPID() {
	return pID;
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

    /**
     * If setting import quantity (initial), then increase the stock quantity if import quantity increase or decrease
     * the stock quantity if import quantity decrease
     *
     * @param updatedInitialQuantity: new value of imported quantity
     */
    public void setInitialQuantity(int updatedInitialQuantity) {
	int changeInImportQuantity = updatedInitialQuantity - this.initialQuantity;
	setCurQuantity(curQuantity + changeInImportQuantity);

	this.initialQuantity = updatedInitialQuantity;
    }

    public int getCurQuantity() {
	return curQuantity;
    }

    /**
     * Setting the current on stock quantity
     *
     * <br><br>If the on stock = 0 then the product will be discontinue by default
     *
     * @param curQuantity : new value of current quantity
     */
    public void setCurQuantity(int curQuantity) {
	if (curQuantity == 0) {
	    setContinuous(false);
	}
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

    public boolean getIsContinuous() {
	return continuous;
    }

    /**
     * The continuity depends on the current quantity, or setting on purpose
     *
     * <br><br>If the current == 0, then the product is inactive (discontinue), otherwise return true
     *
     * @param continuous: set if the product is continue on sell or not
     */
    public void setContinuous(boolean continuous) {
	this.continuous = continuous;
    }

    public String getPrID() {
	return prID;
    }

    public void setPrID(String prID) {
	this.prID = prID;
    }
}
