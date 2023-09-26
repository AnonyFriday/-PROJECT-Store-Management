/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.products;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.function.Function;
import purchasingmng.pureceipt.PurchaseReceipt;
import tools.Constants;
import tools.NumberVerifier;
import tools.input.InputBooleanTools;
import tools.input.InputDateTools;
import tools.input.InputNumberTools;
import tools.input.InputStringTools;
import util.IProductManagement;

/**
 * A list containing Products within this store
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
	boolean isHavingAny = false;
	for (Product product : this) {
	    product.displayProductWithFormat();
	    isHavingAny = true;
	}
	return isHavingAny;
    }

    /**
     * Display all Products under specific fields
     *
     * @param verify: passing a functional interface as the condition
     * @return true if no error, otherwise false
     */
    public boolean displayProducts(Function<Product, Boolean> verify) {

	boolean isHavingAny = false;

	for (Product product : this) {
	    if (verify.apply(product)) {
		product.displayProductWithFormat();
		isHavingAny = true;
	    }
	}
	return isHavingAny;
    }

    /**
     * Display all Products under Sorting and Specific fields
     *
     * <br><br> Since sorting occurs, the display must be conducted on copy array;
     *
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

    // ======================================
    // = Update Methods
    // ======================================
    /**
     * Update Name's attribute of the Product
     *
     * @param product: product to be modified
     */
    public void updateName(Product product) {
	// Name
	// - Unchange if user press Enter
	String name = InputStringTools.readString("Update Name (Enter to unchange)",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Only contains numeric alphabets and space character",
			"Press Enter to unchange value",
			"e.g. Lemongrass Oil 23"),
		"^[a-zA-Z0-9 ]+$", true);
	if (!name.isEmpty()) {
	    product.setName(name);
	}
    }

    /**
     * Update Initial Quantity's attribute of the Product
     *
     * @param product: product to be modified
     */
    public void updateInitialQuantity(Product product) {
	// Initial Quantity
	// - The current quantity equals to the initial quantity automatically by default
	// - If import more products (initial q.), the on stock will be topup (current q.)
	Integer quantity = InputNumberTools.readInteger("Update Initial Quantity (Enter to unchange)",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Only contains integral value greater than 0",
			"e.g. 20, 32, 40"), true,
		"^\\d+$",
		(value) -> NumberVerifier.isGreaterThanEqualsTo(value, 1.0));
	if (!(quantity == null)) {
	    product.setInitialQuantity(quantity);
	}
    }

    /**
     * Update Current Quantity's attribute (On Stock) of the Product
     *
     * @param product: product to be modified
     */
    public void updateCurrentQuantity(Product product) {
	// Current Quantity
	// - If import more products (initial q.), the on stock will be topup (current q.)
	// - If on stock = 0 then the product's continuous will be discontinue
	Integer quantity = InputNumberTools.readInteger("Update Current Quantity (Enter to unchange)",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Only contains integral value greater than or equals to 0",
			"e.g. 20, 32, 40"), true,
		"^\\d+$",
		(value) -> NumberVerifier.isGreaterThanEqualsTo(value, 0.0));
	if (!(quantity == null)) {
	    product.setCurQuantity(quantity);
	}
    }

    // Purchase Price 
    // - Adding extra checking on greater than 0
    // - Unchange if user press Enter
    public void updatePurchasePrice(Product product) {
	Double price = InputNumberTools.readDouble("Update Purchase Price (Enter to unchange)",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Only contains float value greater than 0",
			"e.g. 12, 1546.4"), true,
		"^\\d+\\.?\\d*$",
		(value) -> NumberVerifier.isGreaterThanEqualsTo(value, 0.1));
	if (!(price == null)) {
	    product.setPurchasePrice(price);
	}
    }

    // ======================================
    // = File Methods
    // ======================================
    /**
     * Load Products's information from Text File
     *
     * @param fileName: a filename
     */
    public void loadProductsFromTextFile(String fileName) {
	// File Reading stream
	FileReader fileReader = null;

	// Reading file by line 
	BufferedReader bufferReader = null;

	try {
	    fileReader = new FileReader(new File(fileName));
	    bufferReader = new BufferedReader(fileReader);

	    // Read by line
	    String line;
	    while ((line = bufferReader.readLine()) != null) {

		// skip if that is a blank line 
		if (!line.isEmpty()) {

		    // Deintegrate the string by ',' and attach each columns into this 
		    // name, purchasePrice, initialQuantity, curQuantity, productionDate, expirationDate, continuous, prID
		    StringTokenizer tokens = new StringTokenizer(line, ",");

		    String pID = tokens.nextToken().trim();
		    String name = tokens.nextToken().trim();
		    double purchasePrice = Double.parseDouble(tokens.nextToken().trim());
		    int initQ = Integer.parseInt(tokens.nextToken().trim());
		    int currQ = Integer.parseInt(tokens.nextToken().trim());
		    Date prodDate = InputDateTools.parseDateFromString(tokens.nextToken().trim(), Constants.DATE_FORMAT);
		    Date expDate = InputDateTools.parseDateFromString(tokens.nextToken().trim(), Constants.DATE_FORMAT);
		    boolean continuous = InputBooleanTools.parseBoolean(tokens.nextToken().trim());
		    String prID = tokens.nextToken().trim();

		    // Creat new instance of Product and add to the interal array list
		    Product newProduct = new Product(pID, name, purchasePrice, initQ, currQ, prodDate, expDate, continuous, prID);
		    this.add(newProduct);
		}
	    }

	    // Print sucessful messages
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Load file successfully", 50);

	} catch (FileNotFoundException ex) {
	    // Exit and printout if file is not 
	    System.err.println(ex);
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("File not found. Please try again", 50);
	} catch (IOException ex) {
	    // Logging if IO having problems
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Something went wrong. Please try again", 70);
	} finally {
	    // Close all streams
	    try {
		if (fileReader != null) {
		    fileReader.close();
		}
		if (bufferReader != null) {
		    bufferReader.close();
		}
	    } catch (IOException ex) {
		Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Something went wrong. Please try again", 70);
	    }
	}
    }
}
