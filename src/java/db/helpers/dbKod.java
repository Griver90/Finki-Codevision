/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.helpers;

import classes.Kod;
import classes.Jazik;
import classes.Predmet;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Pomosna klasa za pristap i vnesuvanje na Kod preku Hibernate
 * @author Aleksandar Pavlov
 * @author  Muhamed Isein
 */
public class dbKod {

    /**
     * Funkcija za vnesuvanje Kod
     * @param  Kod - kodot sto treba da se vnese
     * @param  idJazik - id na programski jazik na kodot
     * @param  idPredmeti  niza od celi za 'id' na predmeti vo koj sto se susa predmetot
     * 
     */
    public static synchronized void vnesiKod(Kod kod, int idJazik, int[] idPredmeti) {
        Session session = null;
        Transaction tx = null;
        try {

            session = dbHelper.getSessionFact().openSession();
            tx = session.beginTransaction();
            // Se  poms na lazy-load se vcituva od baza objektot jazik so id - idJazik
            Jazik jazik = (Jazik) session.load(Jazik.class, idJazik);
            // Se postavuva jazikot za kodot da e objektot vcitan od baza
            kod.setJazikKod(jazik);
            // Se dodava kodot vo listata od kodovi za toj jazik
            jazik.getHashsetJazik().add(kod);

            // Potoa se dodava vo listata na predmeti vo koj sto se pojavuva toj kod
            Predmet pr;
            for (int i = 0; i < idPredmeti.length; i++) {
                pr = (Predmet) session.load(Predmet.class, idPredmeti[i]);
                kod.getHashsetPredmet().add(pr);
            }
            // Se dpdava lpdpt vo baza
            session.save(kod);
            tx.commit();
            // Se generiraat iminja na fajlovi spoed kodot 
            // vo foramt jazik-idkod-brojStrana
            String[] imeDadoteki = dbKod.zemiImeNaDadoteki(kod);
            // Soodvetno se kreira i niza od fajlovi so sodrzinata za sekoj kod
            File[] file = FileHandler.kreirajFileKod(imeDadoteki, kod);
            // Potoa se aploadiraat vo file serverot vo folder Kod
            FTP.addFile(file, "Kod");
            // Se generira url so podatoci za tabelata novost
            //  predmetId-jdjazik-kodid
            String url = "0-" + idJazik + "-" + kod.getIdKod();
            // se dodava nov "novost" vo bazata
            dbNovost.vnesiNovost("Нов код : " + kod.getNaslovKod(),url);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }


        }
    }
    /**
     * Vrakja posledniot kod vnesen
     * @return Kod, Posleden Kod vnesen
     */
    public static synchronized Kod zemiPosledenKod() {
        Kod posledenKod;
        List<Kod> site = dbKod.zemiSiteKodovi();
        posledenKod = site.get(site.size() - 1);
        return posledenKod;
    }
   
    /**
     *  Vraka lista na site kodovi od basa za site jazici
     * @return  List<Kod> 
     * 
     */
     public static synchronized List<Kod> zemiSiteKodovi() {
        Session session = null;
        List<Kod> rezultat = null;
        try {
            session = dbHelper.getSessionFact().openSession();
            Criteria findAll = session.createCriteria(Kod.class);
            rezultat = findAll.list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
        return rezultat;
    }
     
    /**
     * Vraka lista od kod za jazik
     * @param  idJazik  - programski jazik za kodot
     * @return  lista od kodovi za jazik  -  idJazik
     */
    public static  List<Kod> vratiKodZaJazik(String idJazik) {
        Session session = null;
        Jazik jazik;
        Kod[] arrKod = null;
        List<Kod> listKod = new ArrayList<Kod>();
        try {
            session = dbHelper.getSessionFact().openSession();
            // So pomos na lazy Load se cita objektot jazik so ID_JAZIK
            jazik = (Jazik) session.load(Jazik.class, Integer.parseInt(idJazik));
            arrKod = new Kod[jazik.getHashsetJazik().size()];
            jazik.getHashsetJazik().toArray(arrKod);
            listKod = Arrays.asList(arrKod);

        } catch (Exception ex) {
            ex.getMessage();
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
        return listKod;
    }
    /**
     * Funkcijata vrakja site kodovi za odreden predmet, pri toa prima kako
     * parametar ID na Predmetot
     * @param predmetID String, Id na predmetot od koj sakame Kodovite
     * @return List<Kod>, Lista na kodovi za predmetot so ID @param predmetID
     */
    public static List<Kod> vratiKodZaPredmet(String predmetID) {
        Session session = null;
        List<Kod> listaKod = new ArrayList<Kod>();
        Kod[] arrKod = null;
        try {
            session = dbHelper.getSessionFact().openSession();
            //Zimame predmetot so id predmetID
            Predmet predmet = (Predmet) session.load(Predmet.class, Integer.parseInt(predmetID));
            //Alocirame prostor za potrebnite Kodove
            arrKod = new Kod[predmet.getHashsetPredmet().size()];
            //Stavame vo pomosnata promenliva arrKod kolekcijata na deca od predmetot
            predmet.getHashsetPredmet().toArray(arrKod);
            //Staame vo array listata objektite od vid Kod
            listaKod = Arrays.asList(arrKod);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }

        }
        return listaKod;
    }
    
    public static Kod vratiKod(String KodID) {
        Session session = null;
        Jazik jazik = new Jazik();
        Kod kod = new Kod();
        try {
            session = dbHelper.getSessionFact().openSession();
            Kod tmpKod = (Kod) session.load(Kod.class, Integer.parseInt(KodID));
            if (tmpKod.getBrojStraniciKod() > 0) {
                kod.setBrojStraniciKod(tmpKod.getBrojStraniciKod());
                kod.setHashsetPredmet(tmpKod.getHashsetPredmet());
                kod.setIdKod(tmpKod.getIdKod());

                jazik.setIdJazik(tmpKod.getJazikKod().getIdJazik());
                jazik.setImeJazik(tmpKod.getJazikKod().getImeJazik());

                kod.setJazikKod(jazik);
                kod.setNaslovKod(tmpKod.getNaslovKod());
                kod.setOpisKod(tmpKod.getOpisKod());
                String[] imeNaFajlovi;
                String[] sodrzinaFajlovi = new String[kod.getBrojStraniciKod()];
                imeNaFajlovi = dbKod.zemiImeNaDadoteki(kod);

                for (int i = 0; i < imeNaFajlovi.length; i++) {
                    File f = FTP.readFile(imeNaFajlovi[i], "Kod");
                    sodrzinaFajlovi[i] = FileHandler.vratiSodrzinaFile(f);
                }
                kod.setSodrzinaKod(sodrzinaFajlovi);
                return kod;
            }
        } catch (Exception ex) {
            ex.getMessage();
            return null;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }

        }
        return null;
    }
    
    public static synchronized boolean brisiKod(String kodID){;
        Session session = null;
        Transaction tx = null;
        try {

            session = dbHelper.getSessionFact().openSession();
            tx = session.beginTransaction();
            Kod k = vratiKod(kodID);
            if(k == null){
                return false;
            }
            dbNovost.brisiNovostZaKod(k.getIdKod());
            session.delete(k);
            tx.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }
    
    public static String[] zemiImeNaDadoteki(Kod kod) {
        String imeDadoteki[] = new String[kod.getBrojStraniciKod()];
        for (int i = 0; i < imeDadoteki.length; i++) {
            imeDadoteki[i] = kod.getJazikKod().getImeJazik() + "-" + kod.getIdKod() + "-" + (i + 1) + ".txt";
        }
        return imeDadoteki;
    }
}
