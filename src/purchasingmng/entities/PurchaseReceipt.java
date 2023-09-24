/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Function;
import tools.Constants;
import tools.GeneralTools;
import util.IProductManagement;

/**
 * A purchase receipt contains purchased products (Import)
 *
 * <br><br>Since the relationship between Product(1) and PurchaseReceipt(M) is association (1 to many),
 * then this class will include the array of products
 *
 * @author duyvu
 */
public class PurchaseReceipt extends ArrayList<Product> implements Comparable<PurchaseReceipt>, IProductManagement {

    // ======================================
    // = Attributes
    // ======================================
    private Date purchaseDate;

    private final String prID;
    private static int trackingID = 0;

    // ======================================
    // = Constructor
    // ======================================   
    // Parameterized Constructor
    public PurchaseReceipt(Date purchaseDate) {
        this.prID = calculatePrID();
        this.purchaseDate = purchaseDate;

        this.add(new Product("VU", 12, 12,
                             GeneralTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                             GeneralTools.parseDateFromString("2-08-2023", Constants.DATE_FORMAT),
                             true, "VR1111"));
        this.add(new Product("VU", 12, 12,
                             GeneralTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                             GeneralTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
                             true, "VR1111"));
        this.add(new Product("VU", 12, 12,
                             GeneralTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                             GeneralTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
                             true, "VR1111"));
        this.add(new Product("VU", 12, 12,
                             GeneralTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                             GeneralTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
                             true, "VR1111"));
        this.add(new Product("VU", 12, 12,
                             GeneralTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                             GeneralTools.parseDateFromString("01-9-2024", Constants.DATE_FORMAT),
                             true, "VR1111"));
    }

    // ======================================
    // = Class-related Methods
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
    // = Create methods
    // ======================================
    /**
     * Add products to
     *
     * @param noProducts
     * @return true if no error, otherwise false
     */
    public boolean addProducts(int noProducts) {
        String name;
        double purchasePrice;
        int initialQuantity;
        Date productionDate;
        Date expirationDate;
        boolean continuous;

        for (int noProduct = 0; noProduct < noProducts; noProduct++) {

            // Announcing the input of product
            Constants.DRAWING_LINE_ONE_MESSAGE("Enter Product No." + noProduct);

            // Name
            name = GeneralTools.readString("Enter Name",
                                           Constants.MUST_IN_CONDITIONS_MSG(
                                                   "Cannot be null",
                                                   "Only contains numeric, alphabets and space character",
                                                   "e.g. Lemongrass Oil 23"),
                                           "^[a-zA-Z0-9 ]+$", false);

            // Purchase Price
            purchasePrice = Double.parseDouble(GeneralTools.readString("Enter Purchase Price",
                                                                       Constants.MUST_IN_CONDITIONS_MSG(
                                                                               "Cannot be null",
                                                                               "Only contains float value greater than 0",
                                                                               "e.g. 12, 1546.4"),
                                                                       "^\\d+\\.?\\d*$", false));

            // Initial Quantity
            initialQuantity = Integer.parseInt(GeneralTools.readString("Enter Initial Quantity",
                                                                       Constants.MUST_IN_CONDITIONS_MSG(
                                                                               "Cannot be null",
                                                                               "Only contains integral value greater than 0",
                                                                               "e.g. 20, 32, 40"),
                                                                       "^\\d+$", false));

            // Production Date
            productionDate = GeneralTools.readDateBefore("Enter Production Date",
                                                         Constants.MUST_IN_CONDITIONS_MSG(
                                                                 "Cannot be null",
                                                                 "Production Date is before Purchase Date",
                                                                 "e.g. 12-12-2000 (dd-MM-yyyy)"),
                                                         Constants.DATE_FORMAT, purchaseDate, false);

            // Expiration Date
            expirationDate = GeneralTools.readDateAfter("Enter Expiration Date",
                                                        Constants.MUST_IN_CONDITIONS_MSG(
                                                                "Cannot be null",
                                                                "Production Date is after Purchase Date",
                                                                "e.g. 12-12-2000 (dd-MM-yyyy)"),
                                                        Constants.DATE_FORMAT, purchaseDate, false);

            // Continuous
            continuous = GeneralTools.readBoolean("Is Product continuously on sell (Y/N)",
                                                  Constants.MUST_IN_CONDITIONS_MSG(
                                                          "Cannot be null",
                                                          "Continous propery accepts only boolean value",
                                                          "e.g. true, false, Y, N"));

            // Add this product into this Purchase Receipt
            Product product = new Product(name, purchasePrice, initialQuantity, productionDate, expirationDate, continuous, prID);

            // If something wrong, return false to notice the error
            if (!this.add(product)) {
                return false;
            }
        }
        return true;
    }

    // ======================================
    // = Read Methods
    // ======================================
    /**
     * Display all Products under no condition
     *
     * @return true if no error, otherwise false
     */
    public boolean displayProducts() {
        for (Product product : this) {
            System.out.println(product.toString());
        }
        return !this.isEmpty();
    }

    /**
     * Display all Products under any condition
     *
     * @param verify: passing a functional interface as the condition
     * @return true if no error, otherwise false
     */
    public boolean displayProducts(Function<Product, Boolean> verify) {
        for (Product product : this) {
            if (verify.apply(product)) {
                System.out.println(product.toString());
            }
        }
        return !this.isEmpty();
    }

    // ======================================
    // = Update Methods　(TODO)
    // ======================================
    /**
     * Update Product based on Product's ID
     *
     * @param pID: passsing Product ID to find the Product
     * @return true if product was founded and changed successfully
     */
    @Override
    public boolean updateProduct(String pID) {

        // Find the index of the product by providing the pID
        int foundedIndex = this.indexOf(new Product(pID));

        // If not found then return false
        if (foundedIndex == -1) {
            return false;
        }

        // Get back the product and declare its variables
        Product product = this.get(foundedIndex);
        String name, purchasePriceStr, initialQuantityStr;

        // Name
        name = GeneralTools.readString("Update Name (Enter to unchange)",
                                       Constants.MUST_IN_CONDITIONS_MSG(
                                               "Only contains numeric alphabets and space character",
                                               "Press Enter to unchange value",
                                               "e.g. Lemongrass Oil 23"),
                                       "^[a-zA-Z0-9 ]+$", true);
        if (!name.isEmpty()) {
            product.setName(name);
        }

        // ERROR
        // Purchase Price 
        // - Adding extra checking on Verifier
        purchasePriceStr = GeneralTools.readString("Enter Purchase Price (Enter to unchange)",
                                                   Constants.MUST_IN_CONDITIONS_MSG(
                                                           "Only contains float value greater than 0",
                                                           "Press Enter to unchange value",
                                                           "e.g. 12, 1546.4"),
                                                   "^\\d+\\.?\\d*$", true);

        // Initial Quantity
        // - The current quantity equals to the initial quantity automatically
        // - Adding extra checking on Verifier
        initialQuantityStr = GeneralTools.readString("Enter Initial Quantity (Enter to unchange)",
                                                     Constants.MUST_IN_CONDITIONS_MSG(
                                                             "Only contains integral value greater than 0",
                                                             "Press Enter to unchange value",
                                                             "e.g. 20, 32, 40"),
                                                     "^\\d+$", true);

        return true;
    }

    // ======================================
    // = Supplement Methods
    // ======================================
    /**
     * Sort a list of products based on condition
     *
     * @param comparator: passing a comparator and use the collections to sort the list of products
     */
    @Override
    public void sortProducts(Comparator<Product> comparator
    ) {
        Collections.sort(this, comparator);
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

    public static void main(String[] args) {

        // Testing Expire Early function
        PurchaseReceipt receipt = new PurchaseReceipt(GeneralTools.parseDateFromString("22-10-2000", Constants.DATE_FORMAT));

        receipt.displayProducts(new Function<Product, Boolean>() {
            @Override
            public Boolean apply(Product obj) {
                int expireDay = GeneralTools.getDatePart(obj.getExpirationDate(), Calendar.DAY_OF_YEAR);
                int currentDay = LocalDate.now().getDayOfYear();
                boolean isExpiredEarly = ((expireDay - currentDay) <= 10) &&
                                         ((expireDay - currentDay) >= 0);
                return isExpiredEarly;
            }
        });

        // Testing Update function
        receipt.updateProduct("P000002");

        receipt.displayProducts(new Function<Product, Boolean>() {
            @Override
            public Boolean apply(Product obj) {
                int expireDay = GeneralTools.getDatePart(obj.getExpirationDate(), Calendar.DAY_OF_YEAR);
                int currentDay = LocalDate.now().getDayOfYear();
                boolean isExpiredEarly = ((expireDay - currentDay) <= 10) &&
                                         ((expireDay - currentDay) >= 0);
                return isExpiredEarly;
            }
        });
    }

}
