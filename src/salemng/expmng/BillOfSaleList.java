/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salemng.expmng;

import inventory.products.Product;
import inventory.products.ProductInventory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import salemng.expproduct.BsProductList;
import tools.Constants;
import tools.NumberVerifier;
import tools.input.InputDateTools;
import tools.input.InputNumberTools;
import tools.input.InputStringTools;

/**
 *
 * @author duyvu
 */
public final class BillOfSaleList extends ArrayList<BillOfSale> {

    // ======================================
    // = Constructor (Singleton)
    // ======================================
    // Set private constructor as a part of the singleton
    private BillOfSaleList() {
    }

    // Applying Bill Pugh Singleton Implementation
    // Inner class to provide instance of the BillOfSaleList class
    // Avoid memory wastage if the object is not in usage
    private static class BillOfSaleListSingleton {

	private final static BillOfSaleList billOfSaleList = new BillOfSaleList();
    }

    // Calling getInstance will get the static instance of this class located at the heap memory
    // Instance of the ProductInventory will be created at the time of calling getInstance()
    // Create only 1 instance through the entire program
    public static BillOfSaleList getInstance() {
	return BillOfSaleListSingleton.billOfSaleList;
    }

    // ======================================
    // = Create Groups
    // ======================================
    /**
     * Add new Export Receipt (BOS) containing exported products
     *
     * @return true if adding successfully, otherwise return false
     */
    public boolean addbillOfSale() {
	int noProducts;
	String pID;
	boolean isNoError = true; // Checking if any errors happen

	// Announcing the input of bill of sales
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Add new Bill Of Sale", 40);
	BillOfSale exportBill = new BillOfSale(new Date());

	// Enter nums of products belonged to this receipt
	noProducts = InputNumberTools.readInteger("Enter number of products",
		Constants.MUST_IN_CONDITIONS_MSG(
			"Cannot be null",
			"Only contains integral value greater than 0",
			"e.g. 20, 32, 40"), false,
		"^\\d+$",
		(value) -> NumberVerifier.isGreaterThanEqualsTo(value, 1.0));

	// Find product and Extract the static instance of the inventory
	ProductInventory inventory = ProductInventory.getInstance();
	BsProductList bsProducts = BsProductList.getInstance();

	for (int i = 0; i < noProducts; i++) {

	    // Announcing the input of product
	    Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Check a Product [" + (i + 1) + "]", 40);

	    // Find the product by id
	    pID = InputStringTools.readString("Find Product's ID (P******, * is a digit)",
		    Constants.MUST_IN_CONDITIONS_MSG("Cannot be null",
			    "Follow the format P****** (* is a digit only)",
			    "Only contains numeric characters",
			    "e.g. P123123, P000000"),
		    "^P\\d{6}$", false);

	    Product product = inventory.findProductById(pID);

	    // If not found the product, continue until ending the loop
	    if (product == null) {
		Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Not found Product with ID (" + pID + ")", 40);
		continue;
	    }

	    // Adding product to the inventory with the foreign-key assigned
	    if (product != null && product.getIsContinuous()) {
		isNoError = bsProducts.addBsProduct(exportBill, product);
		if (!isNoError) {
		    Constants.INVALID_MSG("Add Selling Product");
		    return false;
		}
	    }
	}

	// If some product are imported failed. Do not create a receipt
	return this.add(exportBill);
    }

    // ======================================
    // = File Groups
    // ======================================
    /**
     * Load Bill Of Sale (Export Receipt) from text file
     *
     * @param fileName: a file contains information of Export Receipt
     */
    public void loadBillOfSaleFromTextFile(String fileName) {
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
		    // bsID, bsDate
		    StringTokenizer tokens = new StringTokenizer(line, ",");
		    String bsID = tokens.nextToken().trim();
		    Date bsDate = InputDateTools.parseDateFromString(tokens.nextToken().trim(), Constants.DATE_FORMAT);

		    // Creat new instance of BillOfSale and add to the interal array list
		    BillOfSale newBOS = new BillOfSale(bsID, bsDate);
		    this.add(newBOS);
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
