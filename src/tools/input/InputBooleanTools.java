/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools.input;

import static tools.input.InputStringTools.sc;

/**
 *
 * @author duyvu
 */
public class InputBooleanTools extends InputTools {

    // ======================================
    // = BOOLEAN GROUPS
    // ======================================
    /**
     * Parsing the input string based on the boolean data
     *
     * @param input: input a string
     * @return true if the string is 't', 'T', 1, any case of 'true' and 'false'
     */
    public static Boolean parseBoolean(String input) {

        Boolean value = null;

        // Match only 1 character: t,1,f or true at the beginning of the string
        String regexTrueValue = "^([t1y]{1}|true|yes)$";
        String regexFalseValue = "^([f0n]{1}|false|no)$";

        // Presanitize the input string
        input = input.trim().toLowerCase();
        if (input.matches(regexTrueValue)) {
            value = true;
        } else if (input.matches(regexFalseValue)) {
            value = false;
        }

        // Return the null value if not matching with the boolean pattern
        return value;
    }

    /**
     * Reading the boolean pattern from the input string
     *
     * @param prompt: A prompt to input a string
     * @param invalidMsg: invalid messages
     * @return true or false based on pattern configured on the for the input string
     */
    public static boolean readBoolean(String prompt,
                                      String invalidMsg) {

        Boolean result = null;
        do {
            System.out.print("\n[!]" + prompt + ": ");
            result = parseBoolean(sc.nextLine());

            // If the Boolean object is null, print out to the console and try again
            if (result == null && invalidMsg.length() > 0) {
                System.out.println(invalidMsg);
            }

            // looping until result has value
        } while (result == null);

        return result;
    }
}
