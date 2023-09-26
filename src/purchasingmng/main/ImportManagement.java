/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.main;

import tools.Constants;
import tools.MenuTools;
import tools.input.InputNumberTools;

/**
 *
 * @author duyvu
 */
public class ImportManagement {

    public static void main(String[] args) {
	ImportMangementControls controls = new ImportMangementControls();

	// A variable determince the user's choice and program's execution
	int userChoice;
	boolean isExitedProgram = false;

	// Load file at first for the application
	controls.loadProductsAndImportFromFile();

	do {
	    // Generate the Menu options
	    userChoice = MenuTools.getChoiceInt(
		    "Add both Purchasing Receipt and its Products's data",
		    "Display products in inventory",
		    "Display nearly expired Products",
		    "Display Products on sale by Name (continuous)",
		    "Display Products not on sale (discontinuous)",
		    "Display Products under Quantity input",
		    "Update Product (Name, Price, Initial Q., Current Q.)",
		    "Save to file");

	    switch (userChoice) {

		// Exit the program
		case 0: {
		    isExitedProgram = true;
		    break;
		}

		// Adding PurchasingReceipt with Products
		case 1: {
		    controls.addPurchasingReceiptWithProducts();

		    // Ask user to continue adding to the list
		    MenuTools.continueOption(() -> controls.addPurchasingReceiptWithProducts(),
			    "Do you want to continue ADDING new Import Receipt (Y/N)");
		    break;
		}

		// Display products in inventory
		case 2: {
		    controls.displayProductsSortingAndQuantity();
		    break;
		}

		// Display nearly expired Products
		case 3: {
		    controls.displayProductsEarlyExpiryDate();
		    break;
		}

		// Display Products on sale by Name (continuous)
		case 4: {
		    controls.displayProductsActiveOnName();
		    break;
		}
		// Display inactive products
		case 5: {
		    controls.displayProductsInActive();
		    break;
		}
		// Display products on quantity conditions
		case 6: {
		    int options = MenuTools.getChoiceInt("Set Initial Q. to filter products",
			    "Set Current Q. to filter products");
		    switch (options) {
			case 0:
			    break;
			case 1: {
			    controls.displayProductsWithInitQuantityCondition();
			    break;
			}
			case 2: {
			    controls.displayProductsWithCurrQuantityCondition();
			    break;
			}
		    }
		    break;
		}
		// Update Product on ID
		case 7: {
		    controls.updateProductOnId();
		    break;
		}
		// Save data to files
		case 8: {
		    controls.saveImportsToFile();
		    controls.saveProductsToFile();
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
