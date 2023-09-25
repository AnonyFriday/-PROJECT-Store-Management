/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.main;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import purchasingmng.product.Product;
import purchasingmng.product.ProductInventory;
import purchasingmng.pureceipt.PurchaseReceipt;
import purchasingmng.pureceipt.PurchaseReceiptList;
import tools.Constants;
import tools.input.InputBooleanTools;
import tools.input.InputDateTools;

/**
 *
 * @author duyvu
 */
public class ImportMangementControls {

    private PurchaseReceiptList prList;
    private final ProductInventory pInventory;

    // ======================================
    // = Constructor 
    // ======================================
    public ImportMangementControls() {
        prList = PurchaseReceiptList.getInstance();
        pInventory = ProductInventory.getInstance();
    }

    // ======================================
    // = Create Methods
    // ======================================
    /**
     * Add new Receipt containing new imported products
     */
    public void addPurchaseReceiptWithProducts() {
        if (!prList.addPurchaseReceipt()) {
            Constants.INVALID_MSG("Adding Purchase Receipt");
        }
    }

    // ======================================
    // = Read Methods
    // ======================================
    /**
     * Display Products in the store with quantity > 0, prID ascendingly then pID ascendingly
     */
    public void displayProductsSortingAndQuantity() {

        Comparator<Product> cmpPrID = new Comparator<Product>() {
            @Override
            public int compare(Product o1,
                               Product o2) {
                return o1.getPrID().compareTo(o2.getPrID());
            }
        };

        // This function will display under sort and quantity > 0
        Comparator<Product> cmpPID = new Comparator<Product>() {
            @Override
            public int compare(Product o1,
                               Product o2) {
                return o1.compareTo(o2);
            }
        };

        // Sorting PrID in ascending order, then sort PID in ascending order if 
        // 2 PrID are equals
        Comparator<Product> cmpForSort = cmpPrID.thenComparing(cmpPID);

        pInventory.displayProducts(cmpPrID.thenComparing(cmpPID), ((t) -> t.getCurQuantity() > 0));
    }

    // ======================================
    // = Getters & Setters
    // ======================================
    public ProductInventory getProInventory() {
        return this.pInventory;
    }

    public PurchaseReceiptList getPrList() {
        return prList;
    }

    public static void main(String[] args) {
        ImportMangementControls controls = new ImportMangementControls();

        controls.getPrList().add(new PurchaseReceipt(InputDateTools.parseDateFromString("12-12-2000",
                                                                                        Constants.DATE_FORMAT)));
        controls.getPrList().add(new PurchaseReceipt(InputDateTools.parseDateFromString("12-12-2000",
                                                                                        Constants.DATE_FORMAT)));
        controls.getPrList().add(new PurchaseReceipt(InputDateTools.parseDateFromString("12-12-2000",
                                                                                        Constants.DATE_FORMAT)));
        controls.getProInventory().addProduct(new PurchaseReceipt(InputDateTools.parseDateFromString("30-01-2999",
                                                                                                     Constants.DATE_FORMAT)));
        controls.getProInventory().add(new Product("VU", 12, 12,
                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                                                   InputDateTools.parseDateFromString("2-08-2023", Constants.DATE_FORMAT),
                                                   "VR1113"));
        controls.getProInventory().add(new Product("VU KIM DUY DS", 12, 12,
                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                                                   InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
                                                   "VR1112"));
        controls.getProInventory().add(new Product("VU KIM DUY DS", 12, 12,
                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                                                   InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
                                                   "VR1112"));
        controls.getProInventory().add(new Product("VU KIM DUY DS", 12, 12,
                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                                                   InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
                                                   "VR1112"));
        controls.getProInventory().add(new Product("VU", 12, 12,
                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                                                   InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
                                                   "VR1111"));
        controls.getProInventory().add(new Product("VU", 12, 0,
                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
                                                   InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
                                                   "VR1110"));

        controls.displayProductsSortingAndQuantity();

    }
}
