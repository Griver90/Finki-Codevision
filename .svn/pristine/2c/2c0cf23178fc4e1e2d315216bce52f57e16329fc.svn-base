/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * //session.load(jazik.class, j.getId_class_jazik() );
 */
package db.helpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Klasa za Hibernate
 * @author Aleksandar
 */
public class dbHelper {

    // Definizia na staticka promenliva koja ke bide povikana od drugite dbHelperi
    private static SessionFactory sessionFact = null;

    /**
     * SessionFactory è promenliva koja generira Sessii pri baranja
     * sekoja sesia koja ke se otvori pri navigazija na sajtot ke bide 
     * proizvedena od ova fabbrika.
     * 
     * @return SessionFactory, Vrakja promenliva od vid SessionFactory
     */
    public static SessionFactory getSessionFact() {
        if (sessionFact == null) {
            sessionFact = new Configuration().configure().buildSessionFactory();
        }

        return sessionFact;
    }

    /**
     * Pomosna funkcija za debagiranje, pomaga pri testiranje na konekcijata
     */
    public static void tryConnection() {

        Session s = null;

        try {
            s = getSessionFact().openSession();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        
    }
}
