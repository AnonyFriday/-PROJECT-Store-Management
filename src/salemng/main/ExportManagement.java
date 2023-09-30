/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salemng.main;

import purchasingmng.main.ImportManagement;
import purchasingmng.main.ImportMangementControls;
import tools.MenuTools;

/**
 *
 * @author duyvu
 */
public class ExportManagement {

    public static void main(String[] args) {
	ImportMangementControls importControlss = new ImportMangementControls();
	ExportMangementControls exportControls = new ExportMangementControls();

	// A variable determince the user's choice and program's execution
	int userChoice;
	boolean isExitedProgram = false;

	// Load file at first for the application
	importControlss.loadProductsAndImportFromFile();
	exportControls.loadBOSandBSProductFromFile();

	do {
	    // Generate the Menu options
	    userChoice = MenuTools.getChoiceInt(
		    "Create Export Receipt (BOS) containing Selling Products (BsProduct)",
		    "Display Selling Products",
		    "Save to file");

	    switch (userChoice) {

		// Exit the program
		case 0: {
		    isExitedProgram = true;
		    break;
		}

		// Adding PurchasingReceipt with Products
		case 1: {
		    exportControls.addBillOfSaleReceiptWithSellingProducts();
		    break;
		}

		// Display products in inventory
		case 2: {
		    exportControls.displaySellingProducts();
		    break;
		}

		// Save BOs and BsProducts to File
		case 3: {
		    exportControls.saveBOSsToFile();
		    exportControls.saveBsProductsToFile();
		    break;
		}
		default: {
		    isExitedProgram = false;
		    break;
		}
	    }
	} while (!isExitedProgram);
    }
}
