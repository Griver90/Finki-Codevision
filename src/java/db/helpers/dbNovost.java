/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db.helpers;

import classes.Novost;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Pomosna klasa za rabota so Novost preku Hibernate
 * @author Aleksandar
 * @author Muhamed Isein
 */
public class dbNovost {

    /**
     * Funkcija koja sto vraka lista na novosti
     *
     * @param brNovosti integer broj na novosti koj sto treba da se zemat
     * @return vraka List<Novost> koja sto  sodrzi najdeni novosti
     */
    public static List<Novost> zemiNovost(int brNovosti) {

        /*Promenliva za sesija*/
        Session session = null;
        /*Promenliva za celosna lista na novosti*/
        List<Novost> l;
        /*Promenliva kade sto ke se cuvaat N novosti vneseni kako parametar*/
        List<Novost> Rezultati = new ArrayList<Novost>();
        try {
            /*Se otvara sesija za da pocne interakcija so baza*/
            session = dbHelper.getSessionFact().openSession();
            /*Se kreira criteria za da moze gi zeme site objekti od klasata Novost*/
            Criteria fa = session.createCriteria(Novost.class);
            /*vo l se smestuva celata lista na podatoci sto odgovara na criteria*/
            l = fa.addOrder(Order.desc("idNovost")).list();
            /*Dokolku ne e null*/
            if (brNovosti < 0) {
                Rezultati = l;
            } else {
                /*Ako kako parametar e vnesen -1 znaci da se vratat site najdeni novosti*/

                /*Dokolku brojot na pronajdeni novosti e pomal od brojot na barani novosti vednas se vraka*/
                if (l.size() < brNovosti) {
                    Rezultati = l;
                } else { /*Dokolku ne znaci pronajdeni se poveke novosti i se vrakaat prvite N novosti*/
                    for (int i = 0; i < brNovosti; i++) {
                        Rezultati.add(l.get(i));
                    }

                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
            return Rezultati;

        }
    }

    public static  <T> void vnesiNovost(String text, String url) {
        Novost n = new Novost();
        //Denesna Data
        Date today = Calendar.getInstance().getTime();
        //Izbiranje format na data
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        //dodavanje data kako string vo Novost objektot
        n.setDatumNovost(df.format(today));
        n.setSodrzinaNovost(text);
        n.setUrlInfo(url);

        vnesiNovostDB(n);
    }
    /**
     * Funkcijata prima parametar od vid Novost koja ja zapisuva vo bazata
     * 
     * @param novost Novost, objekt koj ke se zapise vo bazata
     */
    public static synchronized void vnesiNovostDB(Novost novost) {
        Session session = null;
        Transaction t = null;

        try {
            session = dbHelper.getSessionFact().openSession();
            t = session.beginTransaction();
            session.save(novost);
            t.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (t != null) {
                t.rollback();
            }
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }
    
    public static synchronized void brisiNovostZaKod(int kodID)
    {
        Session session = null;
        Transaction tx = null;
        try{
            session=dbHelper.getSessionFact().openSession();
            tx=session.beginTransaction();
            ArrayList<Novost> list = (ArrayList<Novost>) session.createCriteria(Novost.class).add(Restrictions.like("urlInfo", "%-%-"+kodID)).list();
            for(Novost k : list)
            {
                session.delete(k);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error..."+ex.getMessage());
            if(tx!=null){
                tx.rollback();
            }
        }
        finally
        {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }
    
    public static synchronized void brisiNovostZaPredmet(int predmetID)
    {
        Session session = null;
        Transaction tx = null;
        try{
            session=dbHelper.getSessionFact().openSession();
            tx=session.beginTransaction();
            ArrayList<Novost> list = (ArrayList<Novost>) session.createCriteria(Novost.class).add(Restrictions.like("urlInfo", predmetID+"-%-%")).list();
            for(Novost k : list)
            {
                session.delete(k);
            }
            tx.commit();
        }
        catch (Exception ex)
        {
            System.out.println("Error..."+ex.getMessage());
            if(tx!=null){
                tx.rollback();
            }
        }
        finally
        {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }
}
