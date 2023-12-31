/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import tools.input.InputBooleanTools;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;
import tools.Constants;
import tools.input.InputNumberTools;
import tools.input.InputStringTools;

/**
 *
 * @author duyvu
 */
public class MenuTools {

    /**
     * All of the languages have the ability to let method take dynamic amount of arguments. It considers a 1D-array
     *
     * @param options: supply multiple options for menu's options
     * @return a selection from the user
     */
    // TODO: Replace by readingInteger
    public static int getChoiceInt(String... options) {

	// Drawing console top edge
	System.out.println("");
	Constants.DRAWING_FIXED_TABLE_EDGE_LINE(50);

	final int L = options.length;
	for (int i = 0; i < L; i++) {
	    System.out.println("\t\t| [" + (i + 1) + "] " + options[i]);
	}

	// Drawing console bottom edge
	Constants.DRAWING_FIXED_TABLE_EDGE_LINE(50);

	// Precheck the input option as the integer 
	return InputNumberTools.readInteger("Choose (1.." + L + ")(0 to exit)",
		Constants.INVALID_MSG("Option"),
		false,
		"^\\d+$",
		(value) -> value >= 0 && value <= L);
    }

    /**
     * Get the option by Numeric type from the user
     *
     * @param list: a list of options
     * @return an object chosen by the user
     */
    public static Object getChoiceObject(List list) {
	int choice = 0;
	final int L = list.size();
	for (int i = 0; i < L; i++) {
	    System.out.println("\t\t| " + (i + 1) + "[?] " + list.get(i).toString()
	    );
	}
	return (choice > 0 && choice <= L) ? list.get(choice - 1) : null;
    }

    /**
     * Continue the method from User's actions
     *
     * @param continueFunction: a function to be executed next
     * @param prompt: a prompt to continue the function
     */
    public static void continueOption(Runnable continueFunction,
	    String prompt) {

	// Assign a list of valid error message if user input the wrong type of boolean
	String invalidBooleanMsg = Constants.MUST_IN_CONDITIONS_MSG("Only accept boolean value (1, 0, f, t, y, n, true, false, yes, no)");

	// If true, then continue the function by applying the Functional Interface
	boolean isContinued = InputBooleanTools.readBoolean(prompt, invalidBooleanMsg);
	while (isContinued) {
	    continueFunction.run();
	    isContinued = InputBooleanTools.readBoolean(prompt, invalidBooleanMsg);
	}
    }
}
