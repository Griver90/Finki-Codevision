/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.helpers;

import java.io.File;

/**
 * Korisni funkcii
 * @author muhamedisein
 */
public class Utilities {

    public static String os = System.getProperty("os.name");

    public static String getOS() {
        return os;
    }
    /**
     * Vraka pateka za zacuvuvanje na fajlovi spored operativniiot sistem
     * @return za Windowows -  C:\\Kod ili  za Linux - /tmp/Kod
     */ 
    public static String getPath() throws Exception {
        String filePath = null;
        // Se proveruva dali operativniot e unix-like operativen sistem
        if (isUnix()) {
            // Proveruva i kreira potrebnite folderite 
            boolean created = new File("/tmp/Kod").mkdirs();
            filePath = "/tmp/Kod/";
            if (!new File(filePath).exists()) {
                throw new Exception("Error creating folder for the file");
            }

        } // dokolku e windows
        else if (isWindows()) {
            // Se proveruva dali postojat folderite, dokolku ne se kreiraat
            boolean created = new File("C:\\Kod\\").mkdirs();
            filePath = "C:\\Kod\\";
            if (!new File(filePath).exists()) {
                throw new Exception("Error creating folder for the file");
            }
        }
        return filePath;
    }
    /**Proveruda dali operativniot sistem na momentalna masina e Windows
     * @return  boolean vrednost vo zavinsot dali e ili ne e windows
     */
    
    public static boolean  isWindows(){
        return os.toLowerCase().startsWith("win");
    }
    
    
    /**
     * Proveruva dali operativniot sistem e Unix-like
     * @return  boolean 
     */
    public static boolean isUnix(){
        return os.toLowerCase().contains("nux") || os.toLowerCase().contains("nix");
    }
}
