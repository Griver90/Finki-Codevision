    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Model Predmet koj se koristi za mapiranje so Hibernate
 *
 * @author Aleksandar
 */
public class Predmet implements Serializable {

    /**
     * ID na predmetot koj e mapiran kako primaren kluc vo bazata
     */
    private int idPredmet;
    /**
     * Koj semestar se zapisuva predmetot, integer vrednost
     */
    private int semestadPredmet;
    /**
     * Ime na predmetot
     */
    private String imePredmet;
    /**
     * Detalen opis na predmetot
     */
    private String opisPredmet;
    /**
     * Kolekcija od deca od vid Kod na sekoj roditel od vid Predmet (1:M)
     */
    private Set<Kod> hashsetPredmet = new HashSet<Kod>(0);

    /**
     * Konstruktor bez parametri
     */
    public Predmet() {
    }

    /**
     * Konstruktor so parametri i prima:
     *
     * @param imePredmet String koj sodrzi imeto na predmetot
     * @param semestadPredmet Integer koj kazuva vo koj semestar se slusa
     * predmetot
     * @param opisPredmet String koj detalno opisuva predmetot
     */
    public Predmet(String imePredmet, int semestadPredmet, String opisPredmet) {
        this.imePredmet = imePredmet;
        this.semestadPredmet = semestadPredmet;
        this.opisPredmet = opisPredmet;
    }

    /**
     * @return String Vrakja ime na predmetot
     */
    public String getImePredmet() {
        return imePredmet;
    }

    /**
     * Podesuva imeto na predmetot
     *
     * @param imePredmet Prima String
     */
    public void setImePredmet(String imePredmet) {
        this.imePredmet = imePredmet;
    }

    /**
     *
     * @return Integer Go vrakja IDto na predmetot
     */
    public int getIdPredmet() {
        return idPredmet;
    }

    /**
     * Podesuva ID na predmet
     *
     * @param idPredmet ID na predmet vo basata
     */
    public void setIdPredmet(int idPredmet) {
        this.idPredmet = idPredmet;
    }

    /**
     *
     * @return Integer Vrakja vo koj semestar se slusa predmetot
     */
    public int getSemestadPredmet() {
        return semestadPredmet;
    }

    /**
     * Podesuva semestarot vo koj se slusa predmetot
     *
     * @param semestadPredmet Semestar vo koj se slusa predmetot
     */
    public void setSemestadPredmet(int semestadPredmet) {
        this.semestadPredmet = semestadPredmet;
    }

    /**
     *
     * @return String Vrakja opis na predmetot
     */
    public String getOpisPredmet() {
        return opisPredmet;
    }

    /**
     * Podesuva opis na predmetot
     *
     * @param opisPredmet Opisot na predmetot
     */
    public void setOpisPredmet(String opisPredmet) {
        this.opisPredmet = opisPredmet;
    }

    /**
     * Vrakja kolekcijata na deca za daden predmet
     *
     * @return Set<Kod> Kolekcijata od deca
     */
    public Set<Kod> getHashsetPredmet() {
        return hashsetPredmet;
    }

    /**
     * Podesuva kolekcija deca na daden predmet
     *
     * @param hashsetPredmet Kolekcijata od deca
     */
    public void setHashsetPredmet(Set<Kod> hashsetPredmet) {
        this.hashsetPredmet = hashsetPredmet;
    }

    /**
     * Preoptovaruvanje na toString metodot za pecatenje na predmet
     *
     * @return String Formatiran text za predmetot
     */
    @Override
    public String toString() {
        return "Semestar " + semestadPredmet + " Ime " + imePredmet + " ID " + idPredmet;
    }
}
