/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.ConstantBootstraps;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tools.input.InputBooleanTools;

/**
 *
 * @author duyvu
 *
 */
public abstract class FileTools {

    /**
     * Write objects to the text-based file
     *
     * @param <T>: Any object that needs to be handling with file
     * @param filename
     * @param objects
     */
    public static <T> void writeObjectsToTextFile(String fileName,
                                                  Collection<T> objects) {
        // Reading file stream
        FileWriter fileWriter = null;

        // Reading file by line
        PrintWriter printWriter = null;

        // File to save with overriden mode
        // vehicle.dat -> vehicleCopied.dat
        fileName = isSaveAtNewFile(fileName);

        try {
            fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);

            // Iterating the list and send the the writer stream
            for (T obj : objects) {
                printWriter.println(obj.toString());
            }

            // Print sucessful messages
            Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Save file successfully", 50);

        } catch (FileNotFoundException ex) {

            // Exit and printout if file is not 
            System.err.println(ex);
            Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("File not found. Please try again", 50);

        } catch (IOException ex) {

            // Logging if IO having problems
            Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Something went wrong. Please try again", 70);

        } finally {

            // Close all streams
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException ex) {
                Constants.DRAWING_DYNAMIC_LINE_ONE_MESSAGE("Something went wrong. Please try again", 70);
            }
        }
    }

    /**
     * Determine the file is being override or saved at new file
     *
     * @param fileName: original file
     * @return a file to be save
     */
    public static String isSaveAtNewFile(String fileName) {
        boolean isSaveAtNewFile = InputBooleanTools.readBoolean("Do you want to save at new file (Y/N)",
                                                                "Only accept boolean value (Y/N)");

        if (isSaveAtNewFile) {
            // vehicle.dat -> vehicleCopied.dat
            String regex = "(\\w+)(\\.(dat|txt))$";
            String replacementFile = "$1Copied$2";

            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(fileName);

            fileName = m.replaceAll(replacementFile);
        }
        return fileName;
    }

    
}

//    public void loadVehiclesFromFile(String filename) {
//
//	// Precheck if the file has been loaded
//	if (!this.isEmpty()) {
//	    Constants.DRAWING_LINE_ONE_MESSAGE("File has been loaded. Cannot load more.");
//	    return;
//	}
//
//	// Reading file stream
//	FileReader fileReader = null;
//
//	// Reading file by line
//	BufferedReader bufferReader = null;
//
//	try {
//	    fileReader = new FileReader(new File(filename));
//	    bufferReader = new BufferedReader(fileReader);
//	    String line;
//
//	    // Iterating each line of file to extract format
//	    while ((line = bufferReader.readLine()) != null) {
//
//		// Prevent the empty line
//		line = line.trim();
//		if (!line.isEmpty()) {
//		    // Line format: id,name,color,price,brand,type,productDate
//		    StringTokenizer str = new StringTokenizer(line, ",");
//
//		    // Adding those token into Vehicle's attributes
//		    String id = str.nextToken().trim();
//		    String name = str.nextToken().trim();
//		    String color = str.nextToken().trim();
//		    double price = Double.parseDouble(str.nextToken().trim());
//		    String brand = str.nextToken().trim();
//		    String type = str.nextToken().trim();
//		    Date productDate = Utilities.parseDateFromString(str.nextToken().trim(), Constants.DATE_FORMAT);
//
//		    // Adding to the list
//		    this.add(new Vehicle(id, name, color, price, brand, type, productDate));
//		}
//	    }
//
//	    // Print sucessful messages
//	    Constants.DRAWING_LINE_ONE_MESSAGE("Loaded file successfully.");
//
//	} catch (FileNotFoundException ex) {
//
//	    // Exit and printout if file is not 
//	    System.err.println(ex);
//	    Constants.DRAWING_LINE_ONE_MESSAGE("File not found. Please try again.");
//
//	} catch (IOException ex) {
//
//	    // Logging if IO having problems
//	    Logger.getLogger(VehicleList.class.getName()).log(Level.SEVERE, null, ex);
//
//	} finally {
//
//	    // Close all streams
//	    try {
//		fileReader.close();
//		bufferReader.close();
//	    } catch (IOException ex) {
//		Logger.getLogger(VehicleList.class.getName()).log(Level.SEVERE, null, ex);
//	    }
//	}
//    }

