/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

/**
 *
 * @author duyvu
 */
public class UtilitiesTesting {

    public static void main(String[] args) {
	System.out.println(Utilities.normalizeDateStr("7$@##$!@!$@@@-9-  2000"));
	
	System.out.println(Utilities.parseDateFromString("12-12-", Constants.DATE_FORMAT));
	
	
    }
}
