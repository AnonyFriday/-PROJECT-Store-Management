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
import purchasingmng.product.Product;
import tools.Constants;
import tools.NumberVerifier;
import tools.input.InputDateTools;
import tools.input.InputNumberTools;
import tools.input.InputStringTools;
import util.IProductManagement;

/**
 * A purchase receipt contains purchased products (Import)
 *
 * <br><br>Since the relationship between Product(1) and PurchaseReceipt(M) is association (1 to many),
 * then this class will include the array of products
 *
 * @author duyvu
 */
public class PurchaseReceipt implements Comparable<PurchaseReceipt> {

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

    /**
     * Override the toString() function to output product's fields
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
    // = Update Methods　
    // ======================================
    /**
     * Update Product based on Product's ID
     *
     * @param pID: passing Product ID to find the Product
     * @return true if product was founded and changed successfully
     */
//    @Override
//    public boolean updateProduct(String pID) {
//
//        // Find the index of the product by providing the pID
//        int foundedIndex = this.indexOf(new Product(pID));
//
//        // If not found then return false
//        if (foundedIndex == -1) {
//            return false;
//        }
//
//        // Get back the product and declare its variables
//        Product product = this.get(foundedIndex);
//        String name;
//        Double price;
//        Integer quantity;
//
//        // Name
//        // - Unchange if user press Enter
//        name = InputStringTools.readString("Update Name (Enter to unchange)",
//                                           Constants.MUST_IN_CONDITIONS_MSG(
//                                                   "Only contains numeric alphabets and space character",
//                                                   "Press Enter to unchange value",
//                                                   "e.g. Lemongrass Oil 23"),
//                                           "^[a-zA-Z0-9 ]+$", true);
//        if (!name.isEmpty()) {
//            product.setName(name);
//        }
//
//        // Purchase Price 
//        // - Adding extra checking on greater than 0
//        // - Unchange if user press Enter
//        price = InputNumberTools.readDouble("Update Purchase Price (Enter to unchange)",
//                                            Constants.MUST_IN_CONDITIONS_MSG(
//                                                    "Only contains float value greater than 0",
//                                                    "e.g. 12, 1546.4"), true,
//                                            "^\\d+\\.?\\d*$",
//                                            (value) -> NumberVerifier.isGreaterThanEqualsTo(value, 0.1));
//        if (!(price == null)) {
//            product.setPurchasePrice(price);
//        }
//
//        // Initial Quantity
//        // - The current quantity equals to the initial quantity automatically
//        // - Adding extra checking on greater than 0
//        // - Unchange if user press Enter
//        quantity = InputNumberTools.readInteger("Update Initial Quantity (Enter to unchange)",
//                                                Constants.MUST_IN_CONDITIONS_MSG(
//                                                        "Only contains integral value greater than 0",
//                                                        "e.g. 20, 32, 40"), true,
//                                                "^\\d+$",
//                                                (value) -> NumberVerifier.isGreaterThanEqualsTo(value, 1.0));
//        if (!(quantity == null)) {
//            product.setInitialQuantity(quantity);
//        }
//
//        return true;
//    }
//
//    // ======================================
//    // = Supplement Methods
//    // ======================================
//    /**
//     * Sort a list of products based on condition
//     *
//     * @param comparator: passing a comparator and use the collections to sort the list of products
//     */
//    @Override
//    public void sortProducts(Comparator<Product> comparator) {
//        
////        ArrayList<Product> Collections.sort(this, comparator);
//    }
//
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

//    public static void main(String[] args) {
//        PurchaseReceipt receipt = new PurchaseReceipt(InputDateTools.parseDateFromString("22-10-2000", Constants.DATE_FORMAT));
//
//        // Testing Add Function
//        receipt.add(new Product("VU", 12, 12,
//                                InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                InputDateTools.parseDateFromString("2-08-2023", Constants.DATE_FORMAT),
//                                "VR1111"));
//        receipt.add(new Product("VU KIM DUY DS", 12, 12,
//                                InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
//                                "VR1111"));
//        receipt.add(new Product("VU", 12, 12,
//                                InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
//                                "VR1111"));
//        receipt.add(new Product("VU", 12, 0,
//                                InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
//                                "VR1111"));
//
//        // Testing Display products function
//        receipt.addProduct();
//        receipt.displayProducts();
//
////        // Testing Expire Early function
////        receipt.displayProducts(new Function<Product, Boolean>() {
////            @Override
////            public Boolean apply(Product obj) {
////                int expireDay = InputDateTools.getDatePart(obj.getExpirationDate(), Calendar.DAY_OF_YEAR);
////                int currentDay = LocalDate.now().getDayOfYear();
////                boolean isExpiredEarly = ((expireDay - currentDay) <= 10) &&
////                                         ((expireDay - currentDay) >= 0);
////                return isExpiredEarly;
////            }
////        });
//        // Testing Update function
//        receipt.updateProduct("P000002");
//
////        receipt.displayProducts(new Function<Product, Boolean>() {
////            @Override
////            public Boolean apply(Product obj) {
////                int expireDay = InputDateTools.getDatePart(obj.getExpirationDate(), Calendar.DAY_OF_YEAR);
////                int currentDay = LocalDate.now().getDayOfYear();
////                boolean isExpiredEarly = ((expireDay - currentDay) <= 10) &&
////                                         ((expireDay - currentDay) >= 0);
////                return isExpiredEarly;
////            }
////        });
//        // Testing Display products function
//        receipt.displayProducts();
//    }
}
