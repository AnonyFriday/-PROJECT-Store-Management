/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools.input;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import tools.Constants;
import static tools.input.InputStringTools.sc;

/**
 *
 * @author duyvu
 */
public class InputDateTools extends InputTools {

    // ======================================
    // = DATE GROUPS
    // ======================================
    /**
     * Normalizing a data string to use only '-' as the seperator
     *
     * <br><br>For example with 7------2//////////2023 --> 7-2-2023
     *
     * @param dateStr: input date string
     * @return new formatted string-typed date
     */
    public static String normalizeDateStr(String dateStr) {

        // Chaining the replaceAll to format the date string
        // ** Special character followed by .<character> => ./**
        // First regex: 7   --  2 /// 2033 => 7--2///2023 (replace all in-between spaces)
        // Second regex: 7--2///2033 => 7-2-2023 (replace all in-between special escape characters)
        String result = dateStr
                .replaceAll("\\s+", "")
                .replaceAll("[^0-9]+", Constants.DATE_DELIMITER);
        return result;
    }

    /**
     * Parse Date based on from String-typed Date
     *
     * @param inputStr: string-typed Date
     * @param dateFormat: a format for date
     * @return a date-typed Date from string-type Date
     */
    public static Date parseDateFromString(String inputStr,
                                           String dateFormat) {

        // Format the input string to 2-3-2022
        inputStr = normalizeDateStr(inputStr);
        DateFormat formatter = new SimpleDateFormat(dateFormat);

        // Set lenient to force the strict rule apply on natural calendar 
        // e.g. 31/2 => error
        formatter.setLenient(false);

        // Formatting the given 2-3-2023 to the specific format date
        try {
            return formatter.parse(inputStr);
        } catch (ParseException ex) {

            // Extract the method name
            String methodName = new Object() {
            }.getClass().getEnclosingMethod().getName();

            System.err.println(methodName + " " + ex.getMessage());
        }
        return null; // Return error if parsing date unsuccessful
    }

    /**
     * Parsing a String format from the Date Object
     *
     * @param date: a date object
     * @param dateFormat: a format of date object
     * @return a string-typed date from the data object
     */
    public static String parseStringFromDate(Date date,
                                             String dateFormat) {
        // If null than return empty string
        if (date == null) {
            return "";
        }

        // Create the formatter with the given dateFormat
        DateFormat formatter = new SimpleDateFormat(dateFormat);

        // return the format of dateString based on the dateObject
        return formatter.format(date);
    }

    /**
     * Extract the part of date by using Calendar package
     *
     * @param date: a date object
     * @param calendarPart: a part of time need extracting
     * @return a value of extracted date's part
     */
    public static int getDatePart(Date date,
                                  int calendarPart) {

        // Determine the calendar by creating the object GregorianCalendar
        Calendar calendar = new GregorianCalendar(Locale.ENGLISH);
        calendar.setTime(date);

        // Set Lenient to false to force the strict rule on natural calendar
        calendar.setLenient(false);

        // Handling the 0th-index of the first month if extracting month
        if (calendarPart == Calendar.MONTH) {
            return calendar.get(calendarPart) + 1;
        }

        // Other Calendar parts
        return calendar.get(calendarPart);
    }

    /**
     * Prompting user to input a date-like-string and convert to the Date object
     *
     * <br>Available Date format: dd-MM-yyyy, yyyy-MM-dd,
     *
     * @param prompt: will be printed before inputting
     * @param invalidMsg: output messages to console in case of invalid input
     * @param dateFormat: input a date format
     * @param isSkippable: return null immediately if user do not enter any values
     * @return a date object based on the input string
     */
    public static Date readDate(String prompt,
                                String invalidMsg,
                                String dateFormat,
                                boolean isSkippable) {

        Date date = null;
        String inputStr = null;
        do {
            System.out.print("\n[!] " + prompt + ": ");
            inputStr = sc.nextLine().trim();

            // If skippable is enabled then return the null immediately
            if (inputStr.isEmpty() && isSkippable) {
                return null;
            }

            // Assign the date object created from the parsing function 
            date = parseDateFromString(inputStr, dateFormat);

            // Output the msg if the date is invalid
            if (date == null && invalidMsg.length() > 0) {
                System.out.println(invalidMsg);
            }

        } while (date == null);

        return date;
    }

    /**
     * Read Date Before the given date
     *
     * @param prompt: prompting the date to return
     * @param invalidMsg: output messages to console in case of invalid input
     * @param dateFormat: date format
     * @param markerDate: the given date
     * @param isSkippable: support skipping value if enables
     * @return the date before the given date or null if skippable is activated
     */
    public static Date readDateBefore(String prompt,
                                      String invalidMsg,
                                      String dateFormat,
                                      Date markerDate,
                                      boolean isSkippable) {

        Date dateBefore = null;
        String inputStr = "";
        boolean isValidDateBefore = false;

        do {
            // Input the date before the marker date
            System.out.print("\n[!] " + prompt + ": ");
            inputStr = sc.nextLine().trim();

            // Return null if user enter nothing and skippable is enable
            if (inputStr.isEmpty() && isSkippable) {
                return null;
            }

            // Check if the date is not null and before the given date
            dateBefore = parseDateFromString(inputStr, dateFormat);
            isValidDateBefore = (dateBefore != null) && dateBefore.before(markerDate);

            // Output the msg if the date is valid
            if (!isValidDateBefore && invalidMsg.length() > 0) {
                System.out.println(invalidMsg);
            }
        } while (!isValidDateBefore);

        return dateBefore;
    }

    /**
     * Read Date after the given date
     *
     * @param prompt: prompting the date to return
     * @param invalidMsg: output messages to console in case of invalid input
     * @param dateFormat: date format
     * @param markerDate: the given date
     * @param isSkippable: support skipping value if enables
     * @return the date after the given date or null if skippable is activated
     */
    public static Date readDateAfter(String prompt,
                                     String invalidMsg,
                                     String dateFormat,
                                     Date markerDate,
                                     boolean isSkippable) {

        Date dateAfter = null;
        String inputStr = "";
        boolean isValidDateAfter = false;

        do {
            // Input the date after the marker date
            System.out.print("\n[!] " + prompt + ": ");
            inputStr = sc.nextLine().trim();

            // Return null if user enter nothing and skippable is enable
            if (inputStr.isEmpty() && isSkippable) {
                return null;
            }

            // Print notice msg if not matching the pattern and having invalid messages   
            dateAfter = parseDateFromString(inputStr, dateFormat);
            isValidDateAfter = (dateAfter != null) && dateAfter.after(markerDate);

            if (!isValidDateAfter && invalidMsg.length() > 0) {
                System.out.println(invalidMsg);
            }
        } while (!isValidDateAfter);

        return dateAfter;
    }

    /**
     * Get the n-days Date after the current Date
     *
     * @param plusDays: number of days after the current date
     * @return a string representation of date
     */
    public static Date getCurrentDatePlusDate(int plusDays) {
        Date today = new Date();
        Date datePlusDays = new Date(today.getTime() + (1000 * 60 * 60 * 24 * plusDays));

        return datePlusDays;
    }
}
