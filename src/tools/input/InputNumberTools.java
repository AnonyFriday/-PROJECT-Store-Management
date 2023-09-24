/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools.input;

import java.util.function.Function;
import tools.Constants;
import tools.NumberVerifier;
import static tools.input.InputTools.sc;

/**
 *
 * @author duyvu
 */
public class InputNumberTools {
    // ======================================
    // = Number Methods
    // ======================================

    /**
     * Read a double using the pre-define pattern + extra verifiers
     *
     * @param prompt: prompting user msg
     * @param invalidMsg:invalid messages for specific input type
     * @param isSkippable: determine if the user skip the input and return null to set a default value
     * @param strFormat: regular expression to match the string format
     * @param verifiers: a list of extra verifiers for corner case (.e.g greater than 12, divisible by 12, etc.)
     * @return
     */
    public static Double readDouble(String prompt,
                                    String invalidMsg,
                                    boolean isSkippable,
                                    String strFormat,
                                    Function<Double, Boolean>... verifiers) {

        String inputStr = "";
        double value = 0.0;
        boolean isQualifiedValue = false;

        do {
            System.out.print("\n[!] " + prompt + ": ");
            inputStr = sc.nextLine().trim();

            // Break the loop immediately if the skippable is enable and user enter empty character
            // - If skippable is enable, alow
            if (inputStr.isEmpty() && isSkippable) {
                return null;
            }

            // Comparing the input and the pattern
            // - Only Digits allowed
            // - Flag is set based on returned matched value
            isQualifiedValue = inputStr.matches(strFormat);

            // Converting bare string to double type
            try {
                value = Double.parseDouble(inputStr);
            } catch (Exception ex) {
                isQualifiedValue = false;
            }

            // If not provided, then skipping as checking with regex only
            if (!(verifiers == null)) {
                // Checking with extra verifiers for Double type
                for (Function<Double, Boolean> verifier : verifiers) {
                    if (!verifier.apply(value)) {
                        isQualifiedValue = false;
                        break;
                    }
                }
            }

            // Print notice msg if not matching the pattern and having invalid messages         
            if (!isQualifiedValue && invalidMsg.length() > 0) {
                System.out.println(invalidMsg);
            }
        } while (!isQualifiedValue);

        return value;
    }

    /**
     * Read a int using the pre-define pattern + extra verifiers
     *
     * @param prompt: prompting user msg
     * @param invalidMsg:invalid messages for specific input type
     * @param isSkippable: determine if the user skip the input and return null to set a default value
     * @param strFormat: regular expression to match the string format
     * @param verifiers: a list of extra verifiers for corner case (.e.g greater than 12, divisible by 12, etc.)
     * @return
     */
    public static Integer readInteger(String prompt,
                                      String invalidMsg,
                                      boolean isSkippable,
                                      String strFormat,
                                      Function<Integer, Boolean>... verifiers) {

        String inputStr = "";
        int value = 0;
        boolean isQualifiedValue = false;

        do {
            System.out.print("\n[!] " + prompt + ": ");
            inputStr = sc.nextLine().trim();

            // Return null immediately if the skippable is enable and user enter empty character
            if (inputStr.isEmpty() && isSkippable) {
                return null;
            }

            // Comparing the input and the pattern
            // - Only Digits allowed
            // - Flag is set based on returned matched value
            isQualifiedValue = inputStr.matches(strFormat);

            // Converting bare string to double type
            try {
                value = Integer.parseInt(inputStr);
            } catch (Exception ex) {
                isQualifiedValue = false;
            }

            // If not provided, then skipping as checking with regex only
            if (!(verifiers == null)) {
                // Checking with extra verifiers for Double type
                for (Function<Integer, Boolean> verifier : verifiers) {
                    if (!verifier.apply(value)) {
                        isQualifiedValue = false;
                        break;
                    }
                }
            }

            // Print notice msg if not matching the pattern and having invalid messages         
            if (!isQualifiedValue && invalidMsg.length() > 0) {
                System.out.println(invalidMsg);
            }
        } while (!isQualifiedValue);

        return value;
    }

    // Testing Function
    public static void main(String[] args) {
        int number = InputNumberTools.readInteger("Enter your number (Enter to unchange)",
                                                  Constants.MUST_IN_CONDITIONS_MSG(
                                                          "Only contains float value greater than 0",
                                                          "Press Enter to unchange value",
                                                          "e.g. 12, 1546.4"),
                                                  false,
                                                  "^-?\\d+\\.?\\d*$",
                                                  (value) -> NumberVerifier.isGreaterThanEqualsTo(value, -32));
    }
}
