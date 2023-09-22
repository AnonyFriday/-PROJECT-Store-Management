/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package purchasingmng.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.Function;
import purchasingmng.entities.Product;
import purchasingmng.entities.PurchaseReceipt;
import tools.Constants;
import tools.Utilities;

/**
 *
 * @author duyvu
 */
public class ProductList extends ArrayList<Product> {

    // ======================================
    // = CONSTRUCTOR
    // ======================================
    /**
     * Default Constructor
     */
    public ProductList() {
	this.add(new Product("VU", 12, 12, 12,
		Utilities.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
		Utilities.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
		true, "VR1111"));
	this.add(new Product("VU", 12, 12, 12,
		Utilities.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
		Utilities.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
		true, "VR1111"));
	this.add(new Product("VU", 12, 12, 12,
		Utilities.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
		Utilities.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
		true, "VR1111"));
	this.add(new Product("VU", 12, 12, 12,
		Utilities.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
		Utilities.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
		true, "VR1111"));
	this.add(new Product("VU", 12, 12, 12,
		Utilities.parseDateFromString("12-01-2000", Constants.DATE_FORMAT),
		Utilities.parseDateFromString("30-9-2023", Constants.DATE_FORMAT),
		true, "VR1111"));
    }

    // ======================================
    // = ADD GROUPS
    // ======================================
    /**
     * Add products to
     *
     * @param noProducts
     */
    public void addProducts(int noProducts, PurchaseReceipt purReceipt) {
	String name;
	double purchasePrice;
	int initialQuantity, curQuantitiy;
	Date productionDate;
	Date expirationDate;
	boolean continuous;
	for (int noProduct = 0; noProduct < noProducts; noProduct++) {

	    // Announcing the input of product
	    Constants.DRAWING_LINE_ONE_MESSAGE("Enter Product No." + noProduct);

	    // Name
	    name = Utilities.readString("Enter Name",
		    Constants.MUST_IN_CONDITIONS_MSG(
			    "Cannot be null",
			    "Only contains numeric and alphabets character",
			    "e.g. Lemongrass Oil 23"),
		    "^[a-zA-Z0-9 ]+$", false);

	    // Purchase Price
	    purchasePrice = Double.parseDouble(Utilities.readString("Enter Purchase Price",
		    Constants.MUST_IN_CONDITIONS_MSG(
			    "Cannot be null",
			    "Only contains float value greater than 0",
			    "e.g. 12, 1546.4"),
		    "^\\d+\\.?\\d*$", false));

	    // Initial Quantity
	    initialQuantity = Integer.parseInt(Utilities.readString("Enter Initial Quantity",
		    Constants.MUST_IN_CONDITIONS_MSG(
			    "Cannot be null",
			    "Only contains integral value greater than 0",
			    "e.g. 20, 32, 40"),
		    "^\\d+$", false));

	    // Current Quantity
	    // By default, current quantity equals to initial quantity
	    curQuantitiy = initialQuantity;

	    // Production Date
	    productionDate = Utilities.readDateBefore("Enter Production Date",
		    Constants.MUST_IN_CONDITIONS_MSG(
			    "Cannot be null",
			    "Production Date is before Purchase Date",
			    "e.g. 12-12-2000 (dd-MM-yyyy)"),
		    Constants.DATE_FORMAT, purReceipt.getPurchaseDate(), false);

	    // Expiration Date
	    expirationDate = Utilities.readDateAfter("Enter Expiration Date",
		    Constants.MUST_IN_CONDITIONS_MSG(
			    "Cannot be null",
			    "Production Date is after Purchase Date",
			    "e.g. 12-12-2000 (dd-MM-yyyy)"),
		    Constants.DATE_FORMAT, purReceipt.getPurchaseDate(), false);

	    // Continuous
	    continuous = Utilities.readBoolean("Is Product continuously on active (Y/N)",
		    Constants.MUST_IN_CONDITIONS_MSG(
			    "Cannot be null",
			    "Continous propery accepts only boolean value",
			    "e.g. true, false, Y, N"));

	    // Add this product into this Purchase Receipt
	    Product product = new Product(name, purchasePrice, initialQuantity, curQuantitiy, productionDate, expirationDate, continuous, purReceipt.getPrID());
	    this.add(product);
	}
    }

    // ======================================
    // = DISPLAYGROUPS
    // ======================================
    /**
     * Display all Products under no condition
     */
    public void displayAllProducts() {
	for (Product product : this) {
	    System.out.println(product.toString());
	}
    }

    /**
     * Display all Products under any condition
     */
    public void displayAllProducts(Function<Product, Boolean> verify) {
	for (Product product : this) {
	    if (verify.apply(product)) {
		System.out.println(product.toString());
	    }
	}
    }

    public static void main(String[] args) {
	ProductList list = new ProductList();
	list.displayAllProducts(new Function<Product, Boolean>() {
	    @Override
	    public Boolean apply(Product obj) {
		int expireDay = Utilities.getDatePart(obj.getExpirationDate(), Calendar.DAY_OF_YEAR);
		int currentDay = LocalDate.now().getDayOfYear();
		return ((expireDay - currentDay) <= 10);
	    }
	});
    }

}
