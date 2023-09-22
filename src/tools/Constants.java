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
    public static final String FILENAME = "./data/vehicle.dat";

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
    public static final String MUST_IN_CONDITIONS_MSG(String... conditions) {

	String str = "Only accept values that: ";
	int L = conditions.length;

	for (int i = 0; i < L; i++) {
	    str = str.concat("\n\t* " + conditions[i] + ".");
	}

	// If there is no condition, then return ""
	return L > 0 ? str : "";
    }

    // Default Value Message
    public static final String DEFAULT_VALUE_MSG(String subject,
	    String value) {
	return "If undefined, value of " + subject + " will be set to " + value + ".";
    }

    // ==================================
    // == OUTPUT DRAWING GROUP
    // ==================================
    public static final void DRAWING_LINE_ONE_MESSAGE(String prompt) {
	System.out.println("\n\t\t+==============================================+");
	System.out.print("\t\t| " + prompt);
	System.out.println("\n\t\t+==============================================+");
    }

    public static final void DRAWING_TABLE_EDGE_LINE() {
	System.out.println("\t\t+----------------------------------------------+");
    }
}
