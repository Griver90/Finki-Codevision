/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.helpers;

import classes.Kod;
import classes.Predmet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

/**
 * Klasa koja sto se sprauva so kreiranje fajlovi za kod i predmet
 *
 * @author Muhamed
 */
public class FileHandler {

    /**
     * Kreira kod vo C\\Kod na lokalniot komp, dokolku ne postoi treba da se
     * kreira
     *
     * @param imeNaFajl ime na fajlot koj sto treba da se kreira
     * @param kod - kod od kade sto ke se cita sodrzinata na kodot i ke se
     * zapise vo fajlt
     * @return fajlot koj sto e kreiran lokalno
     */
    public static File[] kreirajFileKod(String[] imeNaFajl, Kod kod) throws Exception {
        // Kreira niza of fajlovi
        File[] files = new File[kod.getBrojStraniciKod()];
        //Vraka pateka za folder kade se zacuvuvaat fajlovite
        String filePath = Utilities.getPath();
        
        for (int i = 0; i < kod.getBrojStraniciKod(); i++) {
            // Kreiranje na file

            File file = new File(filePath + imeNaFajl[i]);

            try {

                // BufferedWriter za pisuvanje vo fie
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), "UTF-8"));
                // Se zapiusva sodrzinata na kodot vo file
                bw.write(kod.getSodrzinaKod(i));
                // Se zatvora fajlot
                bw.close();
                files[i] = file;
            } catch (Exception ex) {
                throw ex;
            }
        }
        return files;

    }

    /**
     * Kreira fajl za predmet ( sodrzina na predemetot ) 
     *
     * @param imeNaFajl ime na fajlot koj sto treba da se kreira
     * @param predmet - objekt od kade sto ke se cita opisot na predmetot i ke
     * se zapise vo fajlot
     * @return filot koj sto kreiran lokalno, ako ne e kreiran se vraka null
     */
    public static File[] kreirajFilePredmet(String imeNaFajl, Predmet predmet) throws Exception {
        //Kreiranje na datotekata na specificiranata lokacija
        File[] files = new File[1];
        String filePath = Utilities.getPath();
        
        files[0] = new File(filePath + imeNaFajl);
        try {
            //Se kreira FileWriter
            FileWriter fw = new FileWriter(files[0].getAbsoluteFile());
            //Se kreira nov zapisuvac na fajlove
            BufferedWriter bw = new BufferedWriter(fw);
            //zapisuva vo fajlot opisot od predmetot
            bw.write(predmet.getOpisPredmet());
            //go zatvora fajlot
            bw.close();
            return files;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    /**
     * Vraka sodrzina na fajl
     * @param  file  fajlot od koj sto treba da se procita sodrzina
     * @return  Sodrzina na fajlot 
     */
    public static String vratiSodrzinaFile(File file) {

        String sodrzina = new String();
        BufferedReader br;
        try {
            String red;
            // se cita red po red i se dodava vo string
            br = new BufferedReader(new FileReader(file));
            while ((red = br.readLine()) != null) {
                sodrzina += red + "\n";
            }
            // i toa se vraka kako rezultat
            return sodrzina;
        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Apdejtira fajld vo server taka sto se brise sodrzinata i se dodava novata sodrzina
     * @param  imeNaFajl  imeto na fajlot za apdejtiranje
     * @param  sodrzinaFile  novata sodrzina na fajlot
     */
     
    public static File updateKod(String imeNaFajl, String sodrzinaFile) throws Exception{
        String filePath = Utilities.getPath();
        File file = new File(filePath + imeNaFajl);
        try {
            // BufferedWriter za pisuvanje vo fie
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), "UTF-8"));
            // Se zapiusva sodrzinata na kodot vo file
            bw.write(sodrzinaFile);
            // Se zatvora fajlot
            bw.close();
            return file;
        } 
        catch (Exception ex) {
            throw ex;
        }
    }
}
