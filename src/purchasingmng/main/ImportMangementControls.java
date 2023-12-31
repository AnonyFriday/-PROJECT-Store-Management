/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.main;

import java.io.File;
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.function.Function;
import javax.swing.JFileChooser;
import inventory.products.Product;
import inventory.products.ProductInventory;
import purchasingmng.pureceipt.PurchaseReceipt;
import purchasingmng.pureceipt.PurchaseReceiptList;
import tools.Constants;
import tools.FileTools;
import tools.MenuTools;
import tools.NumberVerifier;
import tools.input.InputBooleanTools;
import tools.input.InputDateTools;
import tools.input.InputNumberTools;
import tools.input.InputStringTools;

/**
 *
 * @author duyvu
 */
public class ImportMangementControls {

    private final PurchaseReceiptList prList;
    private final ProductInventory pInventory;

    // ======================================
    // = Constructor 
    // ======================================
    /**
     * Default Constructor
     */
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
    public void addPurchasingReceiptWithProducts() {
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

	// Drawing a line as the decoration
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Display products that sorted on prID and pID", 50);

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

	// Find products based on those condition
	if (!pInventory.displayProducts(cmpPrID.thenComparing(cmpPID), ((t) -> t.getCurQuantity() > 0))) {
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("No products found", 40);
	}
    }

    /**
     * Display products are nearly expire if the expireDay is only 10 days left corresponding to the current date
     */
    public void displayProductsEarlyExpiryDate() {

	// Drawing a line as the decoration
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Find products that expire in 10 days left", 40);

	// Create the finding condition on expireDay is only 10 days left
	var isEarlyExpiryDate = new Function<Product, Boolean>() {
	    public Boolean apply(Product obj) {
		int expireDay = InputDateTools.getDatePart(obj.getExpirationDate(),
			Calendar.DAY_OF_YEAR);
		int currentDay = LocalDate.now().getDayOfYear();
		boolean isExpiredEarly = ((expireDay - currentDay) <= 10)
			&& ((expireDay - currentDay) >= 0);
		return isExpiredEarly;
	    }
	};

	// Find products based on those condition
	if (!this.pInventory.displayProducts(isEarlyExpiryDate)) {
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("No products found", 40);
	}
    }

    /**
     * Display products on name and is still in active (continuous = true)
     */
    public void displayProductsActiveOnName() {

	String name;

	// Drawing a line as the decoration
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Find active products by product's name", 40);

	// Prompting User to enter a name
	name = InputStringTools.readString("Enter product's name",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Only contains numeric alphabets and space character",
			"Cannot be null",
			"e.g. Lemongrass Oil 23"),
		"^[a-zA-Z0-9 ]+$", false);

	// Create the finding condition on matched name and is still be active
	var isActiveOnName = new Function<Product, Boolean>() {
	    public Boolean apply(Product obj) {

		boolean isNameMatched = obj.getName().toLowerCase().contains(name.toLowerCase());
		boolean isActive = obj.getIsContinuous();

		return isNameMatched && isActive;
	    }
	};

	// Find products based on those condition
	if (!this.pInventory.displayProducts(isActiveOnName)) {
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("No products found", 40);
	}
    }

    /**
     * Display inactive products which having current quantity = 0
     */
    public void displayProductsInActive() {

	// Drawing a line as the decoration
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Find inactive products", 40);

	// Create the finding condition on matched name and is still be active
	var isInActive = new Function<Product, Boolean>() {
	    public Boolean apply(Product obj) {
		return !obj.getIsContinuous();
	    }
	};

	// Find products based on those condition
	if (!this.pInventory.displayProducts(isInActive)) {
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("No products found", 40);
	}
    }

    /**
     * Display products under the marker current current quantity
     */
    public void displayProductsWithCurrQuantityCondition() {

	// Drawing a line as the decoration
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Find products having current on current quantity condition", 60);

	// Prompting User to enter a markerCurrentQuantity
	var markerCurrentQuantity = InputNumberTools.readInteger("Enter maximum current quantity",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Cannot be null",
			"Only contains integral value greater than or equals to 0",
			"e.g. 20, 32, 40"), false,
		"^\\d+$",
		(value) -> NumberVerifier.isGreaterThanEqualsTo(value, 0.0));

	var isUnderMarkerCurrQuantity = new Function<Product, Boolean>() {
	    public Boolean apply(Product obj) {
		return obj.getCurQuantity() <= markerCurrentQuantity;
	    }
	};

	// Find products based on those condition
	if (!this.pInventory.displayProducts(isUnderMarkerCurrQuantity)) {
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("No products found", 40);
	}
    }

    /**
     * Display products under the marker current initial quantity
     */
    public void displayProductsWithInitQuantityCondition() {

	// Drawing a line as the decoration
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Find products having current on initial quantity condition", 60);

	// Prompting User to enter a marker InitQuantity
	var markerInitQuantity = InputNumberTools.readInteger("Enter maximum initial quantity",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Cannot be null",
			"Only contains integral value greater than or equals to 0",
			"e.g. 20, 32, 40"), false,
		"^\\d+$",
		(value) -> NumberVerifier.isGreaterThanEqualsTo(value, 0.0));

	var isUnderMarkerInitQuantity = new Function<Product, Boolean>() {
	    public Boolean apply(Product obj) {
		return obj.getInitialQuantity() <= markerInitQuantity;
	    }
	};

	// Find products based on those condition
	if (!this.pInventory.displayProducts(isUnderMarkerInitQuantity)) {
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("No products found", 40);
	}
    }

    // ======================================
    // = Update Methods
    // ======================================
    public void updateProductOnId() {

	String name;
	Double price;
	Integer quantity;
	String pID;

	// Drawing a line as the decoration
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Update Product on ID", 60);

	// Name
	pID = InputStringTools.readString("Enter Product's ID (P******, * is a digit)",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Cannot be null",
			"Only contains numeric characters",
			"e.g. P000000, P123123"),
		"^P[0-9]{6}$", false);

	// Find the index of the product by providing the pID
	Product product = this.pInventory.findProductById(pID);

	// If not found then return immediately
	if (product == null) {
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Product not found", 40);
	} else {

	    // Prompting User to choose
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Choose features to update", 60);

	    int choice = MenuTools.getChoiceInt("Update name",
		    "Update price",
		    "Update initial quantity (import q))",
		    "Update current quantity (stock q)");

	    switch (choice) {
		case 0:
		    return; // If enter 0, then exit the program
		case 1: {
		    this.pInventory.updateName(product);
		    break;
		}
		case 2: {
		    this.pInventory.updatePurchasePrice(product);
		    break;
		}
		case 3: {
		    this.pInventory.updateInitialQuantity(product);
		    break;
		}
		case 4: {
		    this.pInventory.updateCurrentQuantity(product);
		    break;
		}
	    }

	    // Prompting User to choose
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Update successfully", 60);
	}

    }

    // ======================================
    // = File Methods
    // ======================================
    /**
     * Save Products list into products.dat file
     */
    public void saveProductsToFile() {
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Save products to file", 22);
	FileTools.writeObjectsToTextFile(Constants.FILENAME_PRODUCTS, pInventory);
    }

    /**
     * Save Imports (PurchaseReceipt) into imports.dat file
     */
    public void saveImportsToFile() {
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Save imports to file", 22);
	FileTools.writeObjectsToTextFile(Constants.FILENAME_IMPORTS, prList);
    }

    /**
     * Load Product and Imports to each Array List for data manipulation
     */
    public void loadProductsAndImportFromFile() {
	pInventory.loadProductsFromTextFile(Constants.FILENAME_PRODUCTS);
	prList.loadPurchaseReceiptsFromTextFile(Constants.FILENAME_IMPORTS);
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

    // Testing Purpose
    public static void main(String[] args) {
	ImportMangementControls controls = new ImportMangementControls();

//        controls.getPrList().add(new PurchaseReceipt(InputDateTools.parseDateFromString("12-12-2000",
//                                                                                        Constants.DATE_FORMAT)));
//        controls.getPrList().add(new PurchaseReceipt(InputDateTools.parseDateFromString("12-12-2000",
//                                                                                        Constants.DATE_FORMAT)));
//        controls.getPrList().add(new PurchaseReceipt(InputDateTools.parseDateFromString("12-12-2000",
//                                                                                        Constants.DATE_FORMAT)));
//        
//        controls.getProInventory().add(new Product("VU", 12, 12,
//                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                                   InputDateTools.parseDateFromString("2-08-2023", Constants.DATE_FORMAT),
//                                                   "VR1113"));
//        
//        controls.getProInventory().add(new Product("TRI", 12, 0,
//                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                                   InputDateTools.parseDateFromString("2-08-2023", Constants.DATE_FORMAT),
//                                                   "VR1113"));
//        
//        controls.getProInventory().add(new Product("TRI", 12, 1,
//                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                                   InputDateTools.parseDateFromString("2-08-2023", Constants.DATE_FORMAT),
//                                                   "VR1113"));
//        
//        controls.getProInventory().add(new Product("VU KIM DUY DS", 12, 12,
//                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                                   InputDateTools.parseDateFromString("01-9-2023", Constants.DATE_FORMAT),
//                                                   "VR1112"));
//        controls.getProInventory().add(new Product("VU KIM DUY DS", 12, 12,
//                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                                   InputDateTools.parseDateFromString("02-9-2023", Constants.DATE_FORMAT),
//                                                   "VR1112"));
//        controls.getProInventory().add(new Product("VU KIM DUY DS", 12, 12,
//                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                                   InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
//                                                   "VR1112"));
//        controls.getProInventory().add(new Product("VU", 12, 12,
//                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                                   InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
//                                                   "VR1111"));
//        controls.getProInventory().add(new Product("VU", 12, 0,
//                                                   InputDateTools.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
//                                                   InputDateTools.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
//                                                   "VR1110"));
	// Testing Problem 1
//        controls.getProInventory().addProduct(new PurchaseReceipt(InputDateTools.parseDateFromString("30-01-2999",
//                                                                                                     Constants.DATE_FORMAT)));
	// Testing Problem 2
//        controls.displayProductsSortingAndQuantity();
	// Testing Problem 3
//        controls.displayEarlyExpiryDate();
	// Testing Problem 4
//        controls.displayProductsActiveOnName();
	// Testing Problem 5
//        controls.displayProductsInActive();
	// Testing Problem 6.1
//        controls.displayProductsWithCurrQuantityCondition();
	// Testing Problem 6.2
//        controls.displayProductsWithInitQuantityCondition();
	// Testing Problem 7
//        controls.updateProductOnId();
//        controls.displayProductsSortingAndQuantity();
//        controls.updateProductOnId();
//        controls.displayProductsSortingAndQuantity();
	// Testing problem 8
//        controls.saveProductsToFile();
//        controls.saveImportsToFile();
	// Testing problem 9
//        controls.loadProductsAndImportFromFile();
//        System.out.println(controls.prList.toArray());
//        controls.displayProductsSortingAndQuantity();
    }
}
