/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import db.helpers.dbJazik;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Model za Kod
 * Se koristi za mapiranje so Hibernate
 * @author Aleksandar
 */
public class Kod implements Serializable {
    
//Pocetok Deklaracija na promenlivi
    
    //ID na Kod vo baza
    private Integer idKod;
      /**
     * Na koj jazik e kodot pisuvan
     */
    private Jazik jazikKod;
      /**
     * Naslov na kod
     */
    private String naslovKod;
      /**
     * Broj na stranici na kod
     */
    private Integer brojStraniciKod;
      /**
     * Informacii za kodot
     */
    private String opisKod; 
    /**
     * sodrzinata na kodot
     */
    private String[] sodrzinaKod;
     /**
     * HashSet kade sto se cuvaat site kodovi za toj predmet
     */
    private Set<Predmet> hashsetPredmet = new HashSet<Predmet>(0);

    
    /**
     * Konstruktor bez parametri
     */
    public Kod() {
    }

   /**
     * Konstruktor so parametri 
     * @param naslovKod Naslov na kod
     * @param brojStraniciKod Broj na stranici
     * @param opisKod Opis na zadaca
     */
    public Kod(String naslovKod, int brojStraniciKod, String opisKod, String[] sodrzinaKod) {
        this.naslovKod = naslovKod;
        this.brojStraniciKod = brojStraniciKod;
        this.opisKod = opisKod;
        this.sodrzinaKod = sodrzinaKod;
    }

     /**
     * @return Broj na stranici na kod
     */
    public Integer getBrojStraniciKod() {
        return brojStraniciKod;
    }

     /**
     * @return Opis na kod
     */
    public String getSodrzinaKod(int i) {
        if (i < brojStraniciKod) {
            return sodrzinaKod[i];
        } else {
            return null;
        }
    }

    /**
     * Postavuva sodrzina na kodot
     *
     * @param sodrzinaKod Sodrzina na sekoja stranica od kodot
     */
    public void setSodrzinaKod(String[] sodrzinaKod) {
        this.sodrzinaKod = sodrzinaKod;
    }

    /**
     * Postavuvanje broj na korisnici
     *
     * @param brojStraniciKod Prima Integer 
     */
    public void setBrojStraniciKod(int brojStraniciKod) {
        this.brojStraniciKod = brojStraniciKod;
    }

     /**
     * @return Integer Go vrakja Id-to na kodot
     */
    public Integer getIdKod() {
        return idKod;
    }

    
    /**
     * Go postavuva ID-to na kodot
     *
     * @param idKod Prima Integer 
     */
    public void setIdKod(Integer idKod) {
        this.idKod = idKod;
    }

     /**
     * @return String Vrakja jazikot so koj e pisuvan kodot
     */
    public Jazik getJazikKod() {
//        return dbJazik.vratiJazikPremaKod(this);
        return jazikKod;
    }

    /**
     * Go postavuva jazikot so koj e pisuvan kodot
     *
     * @param jazikKod Prima Jazik 
     */
    public void setJazikKod(Jazik jazikKod) {
        this.jazikKod = jazikKod;
    }
    
    /**
     * @return Set<Predmet> Vrakja predmeti vo koi se naogja kodot
     */
    public Set<Predmet> getHashsetPredmet() {
        return hashsetPredmet;
    }

     /**
     * Podesuva predmeti vo koi ke se naogja kodot
     *
     * @param hashsetPredmet Prima povekje predmeti
     */
    public void setHashsetPredmet(Set<Predmet> hashsetPredmet) {
        this.hashsetPredmet = hashsetPredmet;
    }

     /**
     * @return String Go vrakja naslovot na kodot
     */
    public String getNaslovKod() {
        return naslovKod;
    }

     /**
     * Go postavuva naslovot na kodot
     *
     * @param naslovKod Prima String
     */
    public void setNaslovKod(String naslovKod) {
        this.naslovKod = naslovKod;
    }

     /**
     * @return String vrakja opis za kodot
     */
    public String getOpisKod() {
        return opisKod;
    }

     /**
     * Postavuva sodrzina na kodot
     *
     * @param opisKod Prima String
     */
    public void setOpisKod(String opisKod) {
        this.opisKod = opisKod;
    }
    //kraj set i get  metodi

     /**
     * Preoptovaruvanje na toString metodata za pecatenje na kod
     *
     * @return String Formatiran text za kodot
     */
    @Override
    public String toString() {
        return "Naslov: " + naslovKod + "Id cod " + idKod + " B.S. :" + brojStraniciKod + " ";
    }
    /**
     * Vrakja string vo soodveten format za kastiranje vo JSON objekt
     * 
     * @return String Formatiran za JSON objekt
     */
    public String toJSON() {
        return "{ ID: " + idKod
                + ", Naslov: '" + naslovKod
                + "', Stranici: " + brojStraniciKod
                + ", Opis: '" + opisKod
                + "' }";
    }
}
