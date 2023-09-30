/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salemng.expproduct;

import inventory.products.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import salemng.expmng.BillOfSale;
import tools.Constants;
import tools.NumberVerifier;
import tools.input.InputDateTools;
import tools.input.InputNumberTools;

/**
 *
 * @author duyvu
 */
public class BsProductList extends ArrayList<BsProduct> {

    // ======================================
    // = Constructor (Singleton)
    // ======================================
    // Set private constructor as a part of the singleton
    private BsProductList() {
    }

    // Applying Bill Pugh Singleton Implementation
    // Inner class to provide instance of the BsProductList class
    // Avoid memory wastage if the object is not in usage
    private static class BsProductListSingleton {

	private final static BsProductList bsProducttList = new BsProductList();
    }

    // Calling getInstance will get the static instance of this class located at the heap memory
    // Instance of the ProductInventory will be created at the time of calling getInstance()
    // Create only 1 instance through the entire program
    public static BsProductList getInstance() {
	return BsProductListSingleton.bsProducttList;
    }

    // ======================================
    // = Create Groups
    // ======================================
    public boolean addBsProduct(BillOfSale billOfSale, Product product) {

	double bsPrice;
	int bsQuantity;

	// BsPrice
	bsPrice = InputNumberTools.readDouble("Enter Selling Price ( sell price >= " + product.getPurchasePrice() + " )",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Cannot be null",
			"Only contains float value greater than the purchase price",
			"e.g. 12, 1546.4"), false,
		"^\\d+\\.?\\d*$",
		(value) -> NumberVerifier.isGreaterThanEqualsTo(value, product.getPurchasePrice())
	);

	// BsQuantity
	bsQuantity = InputNumberTools.readInteger("Enter Bill Of Sale Quantity",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Cannot be null",
			"Only contains integral value greater than 0",
			"e.g. 20, 32, 40"), false,
		"^\\d+$",
		(value) -> NumberVerifier.isGreaterThanEqualsTo(value, 1.0));

	// Add this bsProduct into the bsProduct list
	BsProduct bsProduct = new BsProduct(billOfSale.getBsID(), product.getPID(), bsPrice, bsQuantity);

	// If adding successfull, deduct the selling product from the product's quantity in stock
	if (this.add(bsProduct)) {
	    int currentQuantityInStock = product.getCurQuantity() - bsQuantity;
	    product.setCurQuantity(currentQuantityInStock);
	    return true;
	}
	// If something wrong, return false to notice the error
	return false;
    }

    // ======================================
    // = Read Groups
    // ======================================
    /**
     * Display current selling products
     */
    public void displaySellingProducts() {
	for (BsProduct bsProduct : this) {
	    bsProduct.displayBsProducttWithFormat();
	}
    }

    // ======================================
    // = File Groups
    // ======================================
    /**
     * Load Bill Of Sale Product (Selling Product) from text file
     *
     * @param fileName: a file contains information of Selling Product
     */
    public void loadBsProductFromTextFile(String fileName) {
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
		    // bsID, pID, bsPrice, bsQuantity
		    StringTokenizer tokens = new StringTokenizer(line, ",");

		    String bsID = tokens.nextToken().trim();
		    String pID = tokens.nextToken().trim();
		    double bsPrice = Double.parseDouble(tokens.nextToken().trim());
		    int bsQuantity = Integer.parseInt(tokens.nextToken().trim());

		    // Creat new instance of BillOfSale and add to the interal array list
		    BsProduct newBsProduct = new BsProduct(bsID, pID, bsPrice, bsQuantity);
		    this.add(newBsProduct);
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
