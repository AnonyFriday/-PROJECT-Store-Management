/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package salemng.main;

import tools.MenuTools;

/**
 *
 * @author duyvu
 */
public class ExportManagement {

    public static void main(String[] args) {
	ExportMangementControls controls = new ExportMangementControls();

	// A variable determince the user's choice and program's execution
	int userChoice;
	boolean isExitedProgram = false;

	// Load file at first for the application
	controls.loadBOSandBSProductFromFile();

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
		    controls.addBillOfSaleReceiptWithSellingProducts();

		    // Ask user to continue adding to the list
		    MenuTools.continueOption(() -> controls.addBillOfSaleReceiptWithSellingProducts(),
			    "Do you want to continue ADDING new Import Receipt (Y/N)");
		    break;
		}

		// Display products in inventory
		case 2: {
		    controls.displaySellingProducts();
		    break;
		}
		case 3: {
		    controls.saveBOSsToFile();
		    controls.saveBsProductsToFile();
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
