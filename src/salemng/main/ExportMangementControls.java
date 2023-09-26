/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salemng.main;

import salemng.expmng.BillOfSaleList;
import salemng.expproduct.BsProductList;
import tools.Constants;
import tools.FileTools;

/**
 *
 * @author duyvu
 */
public class ExportMangementControls {

    private final BillOfSaleList bsList;
    private final BsProductList bsProdList;

    // ======================================
    // = Constructor 
    // ======================================
    /**
     * Default Constructor
     */
    public ExportMangementControls() {
	bsList = BillOfSaleList.getInstance();
	bsProdList = BsProductList.getInstance();
    }

    // ======================================
    // = Create Methods
    // ======================================
    /**
     * Add new Export Receipt containing selling product
     */
    public void addBillOfSaleReceiptWithSellingProducts() {
	if (!bsList.addbillOfSale()) {
	    Constants.INVALID_MSG("Adding Bill Of Sale");
	}
    }

    // ======================================
    // = Read Methods
    // ======================================
    /**
     * Display all selling products
     */
    public void displaySellingProducts() {
	bsProdList.displaySellingProducts();
    }

    // ======================================
    // = File Methods
    // ======================================
    /**
     * Save Bill Of Sales to file exports.dat
     */
    public void saveBOSsToFile() {
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Save Bill Of Sales to file", 22);
	FileTools.writeObjectsToTextFile(Constants.FILENAME_EXPORTS, bsList);
    }

    /**
     * Save Selling Products to bsproduct.dat
     */
    public void saveBsProductsToFile() {
	Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Save Selling Products to file", 22);
	FileTools.writeObjectsToTextFile(Constants.FILENAME_BSPRODUCTS, bsProdList);
    }

    /**
     * Load both BOS and BsProduct from fie
     */
    public void loadBOSandBSProductFromFile() {
	bsList.loadBillOfSaleFromTextFile(Constants.FILENAME_EXPORTS);
	bsProdList.loadBsProductFromTextFile(Constants.FILENAME_BSPRODUCTS);
    }

    // ======================================
    // = Getters & Setters
    // ======================================
    public BillOfSaleList getBsList() {
	return bsList;
    }

    public BsProductList getBsProdList() {
	return bsProdList;
    }
}
