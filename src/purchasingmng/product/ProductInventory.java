/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Function;
import purchasingmng.pureceipt.PurchaseReceipt;
import tools.Constants;
import tools.NumberVerifier;
import tools.input.InputDateTools;
import tools.input.InputNumberTools;
import tools.input.InputStringTools;
import util.IProductManagement;

/** A list containing Products within this store
 *
 * <br>Apply Singleton design pattern for this class as a single shared resources (create only 1 instance of the class)
 *
 * @author duyvu
 */
public final class ProductInventory extends ArrayList<Product> {

    // ======================================
    // = Constructor (Singleton)
    // ======================================
    // Set private constructor as a part of the singleton
    private ProductInventory() {
    }

    // Applying Bill Pugh Singleton Implementation
    // Inner class to provide instance of the ProductInventory class
    // Avoid memory wastage if the object is not in usage
    private static class ProducInventorySingleton {

        private final static ProductInventory productInventory = new ProductInventory();
    }

    // Calling getInstance will get the static instance of this class located at the heap memory
    // Instance of the ProductInventory will be created at the time of calling getInstance()
    // Create only 1 instance through the entire program
    public static ProductInventory getInstance() {
        return ProducInventorySingleton.productInventory;
    }

    // ======================================
    // = Create Methods
    // ======================================
    /**
     * Add product to the central inventory after generating the purchasing receipt
     *
     * @param purReceipt: assign the purchasing receipt as the foreign key for each product
     * @return true if added successfully, otherwise return false
     */
    public boolean addProduct(PurchaseReceipt purReceipt) {

        String name;
        double purchasePrice;
        int initialQuantity;
        Date productionDate;
        Date expirationDate;

        // Name
        name = InputStringTools.readString("Enter Name",
                                           Constants.MUST_IN_CONDITIONS_MSG(
                                                   "Cannot be null",
                                                   "Only contains numeric, alphabets and space character",
                                                   "e.g. Lemongrass Oil 23"),
                                           "^[a-zA-Z0-9 ]+$", false);

        // Purchase Price
        purchasePrice = InputNumberTools.readDouble("Enter Purchase Price",
                                                    Constants.MUST_IN_CONDITIONS_MSG(
                                                            "Cannot be null",
                                                            "Only contains float value greater than 0",
                                                            "e.g. 12, 1546.4"), false,
                                                    "^\\d+\\.?\\d*$",
                                                    (value) -> NumberVerifier.isGreaterThanEqualsTo(value, 0.1)
        );

        // Initial Quantity
        initialQuantity = InputNumberTools.readInteger("Enter Initial Quantity",
                                                       Constants.MUST_IN_CONDITIONS_MSG(
                                                               "Cannot be null",
                                                               "Only contains integral value greater than 0",
                                                               "e.g. 20, 32, 40"), false,
                                                       "^\\d+$",
                                                       (value) -> NumberVerifier.isGreaterThanEqualsTo(value, 1.0));

        // Production Date
        productionDate = InputDateTools.readDateBefore("Enter Production Date",
                                                       Constants.MUST_IN_CONDITIONS_MSG(
                                                               "Cannot be null",
                                                               "Production Date is before Purchase Date",
                                                               "e.g. 12-12-2000 (dd-MM-yyyy)"),
                                                       Constants.DATE_FORMAT, purReceipt.getPurchaseDate(), false);

        // Expiration Date
        expirationDate = InputDateTools.readDateAfter("Enter Expiration Date",
                                                      Constants.MUST_IN_CONDITIONS_MSG(
                                                              "Cannot be null",
                                                              "Production Date is after Purchase Date",
                                                              "e.g. 12-12-2000 (dd-MM-yyyy)"),
                                                      Constants.DATE_FORMAT, purReceipt.getPurchaseDate(), false);

        // Add this product into this Purchase Receipt
        Product product = new Product(name, purchasePrice, initialQuantity, productionDate, expirationDate, purReceipt.getPrID());

        // If something wrong, return false to notice the error
        return this.add(product);
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
            product.displayProductWithFormat();
        }
        return !this.isEmpty();
    }

    /**
     * Display all Products under specific fields
     *
     * @param verify: passing a functional interface as the condition
     * @return true if no error, otherwise false
     */
    public boolean displayProducts(Function<Product, Boolean> verify) {
        for (Product product : this) {
            if (verify.apply(product)) {
                product.displayProductWithFormat();
            }
        }
        return !this.isEmpty();
    }

    /**
     * Display all Products under Sorting and Specific fields
     *
     * <br><br> Since sorting occurs, the display must be conducted on copy array;
     * @param comp: comparator used for sorting
     * @param verify: passing a functional interface as the condition
     * @return true if no error, otherwise false
     */
    public boolean displayProducts(Comparator<Product> comp,
                                   Function<Product, Boolean> verify) {

        ArrayList<Product> tmp = new ArrayList<>(this);
        Collections.sort(tmp, comp);

        for (Product product : tmp) {
            if (verify.apply(product)) {
                product.displayProductWithFormat();
            }
        }
        return !this.isEmpty();
    }
}
