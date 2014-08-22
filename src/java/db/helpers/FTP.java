/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.helpers;

import it.sauronsoftware.ftp4j.FTPClient;
import java.io.File;

/**
 * Klasa za polesna rabota so FTP
 *
 * @author Muhamed
 */
public class FTP {

    /**
     * Dodava file vo ftp serverot,
     *
     * @param file - file koj sto treba da se zakaci na ftp server
     * @param directory - direktoriumo vo koj treba da se zapisa fajlot
     * @return vraka true dokolku uspesno e zakacan fjl, false dokolku nastanala
     * greska
     *
     */
    public static synchronized boolean addFile(File[] file, String directory) {
        try {
            // Kreira objekt od FTPClient
            FTPClient ftpClient = new FTPClient();
            // Se postavuva ip adresa ili db name
            ftpClient.connect("64b.75d.myftpupload.com");
            // Tuka se podesuvaat username i poassword za ftp serverot
            ftpClient.login("mrinf3ction", "Asd123@a");
            // Se podesuva momentalna lokacija vo ftp serverot da e folderot Kod kade sto se cuvaat site kodovi
            ftpClient.changeDirectory(directory);

            for (int i = 0; i < file.length; i++) {
                // Dokolku fajlot postoi,, t.e vneseiot objekt ne e null ili nekoja druga greska
                if (file[i].exists()) {
                    // Se zakacuva fajlot na server
                    ftpClient.upload(file[i]);
                    System.out.println("Uspesno zakacen fajl so ime " + file[i].getName());

                }
            }
            // Se diskonektira od serverot
            ftpClient.disconnect(true);
        } catch (Exception ex) {
            System.out.println(ex.getCause() + " " + ex.getMessage());
        }
        return false;
    }

    /**
     * Zima file od FTP serverot
     *
     *
     * @param imeFile - File sto treba de se zeme od server
     * @param directory - direktoriumo od koj treba da se zeme fajlot
     * @return zemaniot file od serverot
     */
    public static synchronized File readFile(String imeFile, String directory) {
        try {
            String filePath = Utilities.getPath();
            //Kreira objekt od tip FTPClient
            FTPClient ftpClient = new FTPClient();
            /*Postavuva vrska do odreden server specificiran kako parametar na funkcijata connect()
             *od klasata FTPClient, IP adresata ili URL na serverot treba da e vo String format
             */
            ftpClient.connect("64b.75d.myftpupload.com");
            //Se postavuvaat parametrite za logiranje na FTP serverot Username Password
            ftpClient.login("mrinf3ction", "Asd123@a");
            //Menuva tekovniot raboten direktorium vo specificiraniot od parametarot
            ftpClient.changeDirectory(directory);
            //Kreira file vo lokalniot direktorium
            File file = new File(filePath + imeFile);
            //Go prezapisuva lokalniot file so simnatiot
            ftpClient.download(imeFile, file);
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("\n" + ex.getMessage() + "\n");
        }
        return null;
    }
}
