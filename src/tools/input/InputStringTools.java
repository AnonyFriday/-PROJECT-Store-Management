package tools.input;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Common tools in Application
 *
 * @author Vu Kim Duy
 */
public abstract class InputStringTools extends InputTools {

    // ======================================
    // = String Methods
    // ======================================
    /**
     * Read a String using the pre-define pattern
     *
     * @param prompt: prompting user msg
     * @param invalidMsg:invalid messages for specific input type
     * @param strFormat: regular expression to match the string format
     * @param isSkippable: determine if the user skip the input and return null to set a default value
     * @return null value if skippable or a new value
     */
    public static String readString(String prompt,
                                    String invalidMsg,
                                    String strFormat,
                                    boolean isSkippable) {

        String inputStr = "";
        boolean isMatched = false;
        do {
            System.out.print("\n[!] " + prompt + ": ");
            inputStr = sc.nextLine().trim();

            // Break the loop immediately if the skippable is enable and user enter empty character
            if (inputStr.isEmpty() && isSkippable) {
                break;
            }

            // Comparing the input and the pattern
            isMatched = inputStr.matches(strFormat);

            // Print notice msg if not matching the pattern and having invalid messages         
            if (!isMatched && invalidMsg.length() > 0) {
                System.out.println(invalidMsg.toString());
            }

        } while (!isMatched);

        return inputStr;
    }
}
