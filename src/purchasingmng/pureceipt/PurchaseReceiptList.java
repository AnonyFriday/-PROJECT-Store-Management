/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.pureceipt;

import java.util.ArrayList;
import java.util.Date;
import purchasingmng.product.ProductInventory;
import tools.Constants;
import tools.NumberVerifier;
import tools.input.InputDateTools;
import tools.input.InputNumberTools;

/**
 *
 * @author duyvu
 */
public final class PurchaseReceiptList extends ArrayList<PurchaseReceipt> {

    // ======================================
    // = Constructor (Singleton)
    // ======================================
    // Set private constructor as a part of the singleton
    private PurchaseReceiptList() {
    }

    // Applying Bill Pugh Singleton Implementation
    // Inner class to provide instance of the PurchaseReceiptList class
    // Avoid memory wastage if the object is not in usage
    private static class PurchaseReceiptListSingleton {

        private final static PurchaseReceiptList purReceiptList = new PurchaseReceiptList();
    }

    // Calling getInstance will get the static instance of this class located at the heap memory
    // Instance of the ProductInventory will be created at the time of calling getInstance()
    // Create only 1 instance through the entire program
    public static PurchaseReceiptList getInstance() {
        return PurchaseReceiptListSingleton.purReceiptList;
    }

    // ======================================
    // = Create Groups
    // ======================================
    /**
     * Add new Receipt containing new imported products
     */
    public boolean addPurchaseReceipt() {
        Date purchaseDate;
        int noProducts;
        boolean isAnyError = false; // Checking if import the product failed

        // Announcing the input of purchase receipt
        Constants.DRAWING_LINE_ONE_MESSAGE("Enter new Purchase Receipt", 40);

        // Purchase Date
        purchaseDate = InputDateTools.readDateBefore("Enter Purchase Date (e.g. 25-09-2023)",
                                                     Constants.MUST_IN_CONDITIONS_MSG(
                                                             "Cannot be null",
                                                             "Production Date is before Purchase Date",
                                                             "e.g. 12-12-2000 (dd-MM-yyyy)"),
                                                     Constants.DATE_FORMAT,
                                                     InputDateTools.getCurrentDatePlusDate(0),
                                                     false);

        // Create new Purchase Receipt
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt(purchaseDate);

        // Enter nums of products belonged to this receipt
        Constants.DRAWING_LINE_ONE_MESSAGE("How many Products belongs to the Purchase Receipt (" + purchaseReceipt.getPrID() + ")", 60);
        noProducts = InputNumberTools.readInteger("Enter number of products",
                                                  Constants.MUST_IN_CONDITIONS_MSG(
                                                          "Cannot be null",
                                                          "Only contains integral value greater than 0",
                                                          "e.g. 20, 32, 40"), false,
                                                  "^\\d+$",
                                                  (value) -> NumberVerifier.isGreaterThanEqualsTo(value, 1.0));

        // Add product to and Extract the static instance of the inventory
        ProductInventory inventory = ProductInventory.getInstance();
        for (int i = 0; i < noProducts; i++) {

            // Announcing the input of product
            Constants.DRAWING_LINE_ONE_MESSAGE("Enter a new Product [" + (i + 1) + "]", 40);

            // Adding product to the inventory with the foreign-key assigned
            if (!inventory.addProduct(purchaseReceipt)) {
                Constants.INVALID_MSG("Add Product");
                isAnyError = true;
                break;
            }
        }

        // If some product are imported failed. Do not create a receipt
        return !isAnyError ? this.add(purchaseReceipt) : false;
    }
}
