/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

/**
 * The file contains Constants Variables of the Application
 *
 * @author duyvu
 */
public abstract class Constants {

    // ==================================
    // == FILENAME GROUP
    // ==================================
    public static final String FILENAME_IMPORTS = "./data/imports.dat";
    public static final String FILENAME_PRODUCTS = "./data/products.dat";
    public static final String FILENAME_EXPORTS = "./data/exports.dat";
    public static final String FILENAME_BSPRODUCTS = "./data/bsproducts.dat";

    // ==================================
    // == DATE GROUP
    // ==================================
    public static final String DATE_DELIMITER = "-";
    public static final String DATE_FORMAT = "dd-MM-yyyy";

    // ==================================
    // == MESSAGE GROUP
    // ==================================
    // Empty Messaage
    public static final String EMPTY_VALUE_MSG = "No values found. Please try again";

    // Invalid Message
    public static final String INVALID_MSG(String subject) {
	return "Invalid " + subject + ". Please try again.";
    }

    // Duplicated Message
    public static final String DUPLICATED_MSG(String subject) {
	return "Duplicated " + subject + ". Please try again.";
    }

    // In format and specific Condition Message
    public static final String MUST_IN_CONDITIONS_MSG(
	    String... conditions) {

	StringBuilder str = new StringBuilder("Only accept values that: ");
	int L = conditions.length;

	for (int i = 0; i < L; i++) {
	    str = str.append("\n\t* ").append(conditions[i]).append(".");
	}

	// If there is no condition, then return ""
	return str.toString();
    }

    // Default Value Message
    public static final String DEFAULT_VALUE_MSG(String subject,
	    String value) {
	return "If undefined, value of " + subject + " will be set to " + value + ".";
    }

    // ==================================
    // == OUTPUT DRAWING GROUP
    // ==================================
    // Drawing 2 dynamic size top and bottom with 1 message inside
    public static final void DRAWING_DYNAMIC_LINE_ONE_MESSAGE(String prompt,
	    int noOfDashs) {
	// Drawing 2 edges of the table
	StringBuilder str = new StringBuilder("=");
	for (int i = 0; i < noOfDashs; i++) {
	    str.append("=");
	}

	System.out.format("%n\t\t+" + str.toString() + "+%n");
	System.out.format("\t\t| " + prompt);
	System.out.format("%n\t\t+" + str.toString() + "+%n");
    }

    // Drawing 2 dynamic size top and bottom lines with content inside 
    public static final void DRAWING_DYNAMIC_LINE_WITH_CONTENT(int noOfDashs,
	    Runnable content) {
	// Drawing 2 edges of the table
	StringBuilder str = new StringBuilder("-");
	for (int i = 0; i < noOfDashs; i++) {
	    str.append("-");
	}

	// Creating a table
	System.out.format("%n\t\t+" + str.toString() + "+%n");
	content.run();
	System.out.format("\t\t+" + str.toString() + "+%n");
    }

    // Drawing 1 fixed size line
    public static final void DRAWING_FIXED_TABLE_EDGE_LINE(int noOfDashs) {
	// Drawing 2 edges of the table
	StringBuilder str = new StringBuilder("-");
	for (int i = 0; i < noOfDashs; i++) {
	    str.append("-");
	}
	System.out.format("\t\t+" + str.toString() + "+%n");
    }
}
