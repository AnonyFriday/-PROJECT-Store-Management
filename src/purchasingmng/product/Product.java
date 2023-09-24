/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.product;

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
    private final String pID;

    // ======================================
    // = Constructor
    // ======================================
    // ID Only Constructor
    public Product(String pID) {
        this.pID = pID;
    }

    // Full Parameterize Constructor
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
        // - Does not create set function, it will automatically update based on export quantity 
        // and initial quantity
        this.curQuantity = initialQuantity;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;

        // If curr quantity is 0 then discontinue the product (Derived Attribute)
        this.continuous = this.curQuantity == 0 ? false : true;

        this.pID = calculatePID();
        this.prID = prID;
    }

    // ======================================
    // = Class-related Methods
    // ======================================
    /**
     * Automatically increase and retrieve the the PID as a string
     *
     * <br><br>e.g. P000000, P0000001
     *
     * @return a string-typed PID
     */
    private String calculatePID() {
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
        Constants.DRAWING_LINE_WITH_CONTENT(77, () -> {
                                        System.out.println(String.format(
                                                "| Product ID    " +
                                                "| Name              " +
                                                "| Purchase Price " +
                                                "| Initial Q. " +
                                                "| Current Q. |"
                                        ));

                                        System.out.println(String.format("| %-13s " +
                                                                         "| %-17s " +
                                                                         "| %-14.2f " +
                                                                         "| %-10d " +
                                                                         "| %-10d |", pID, name, purchasePrice, initialQuantity,
                                                                         curQuantity
                                        ));

                                        Constants.DRAWING_TABLE_EDGE_LINE(77);

                                        System.out.println(String.format(
                                                "| Production D. " +
                                                "| Expiry D.         " +
                                                "| Is Con.        " +
                                                "| Import ID  " +
                                                "| %10s |", " "));

                                        System.out.println(String.format(
                                                "| %-13s " +
                                                "| %-17s " +
                                                "| %-14b " +
                                                "| %-10s " +
                                                "| %10s |",
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

    /**
     * The continuity depends on the current quantity
     *
     * <br><br>If the current == 0, then the product is inactive, otherwise return true
     *
     * @param currentQuantity: current quantity of the product
     */
    public void setContinuous(int currentQuantity) {
        this.continuous = currentQuantity == 0 ? false : true;
    }

    public String getPrID() {
        return prID;
    }

    public void setPrID(String prID) {
        this.prID = prID;
    }
}
