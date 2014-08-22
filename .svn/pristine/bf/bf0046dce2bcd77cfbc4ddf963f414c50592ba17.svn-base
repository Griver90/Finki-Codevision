/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Model za Programski Jazik
 * Se koristi za mapiranjee so Hibernate 
 *
 * @author Aleksandar
 */
public class Jazik implements Serializable {

    /**
     * ID na jazik vo baza
     */
    private int idJazik;
    /**
     * Ime na jazik (C/C++/C#)
     */
    private String imeJazik;
    /**
     * HashSet kade sto se cuvaat site kodovi za toj programski jazik
     */
    private Set<Kod> hashsetJazik = new HashSet<Kod>(0);

    /**
     *
     * @return integer id na jazik
     */
    public int getIdJazik() {
        return idJazik;
    }

    /**
     * podesuva id na jazik
     *
     * @param idJazik id na jazik vo bazata
     */
    public void setIdJazik(int idJazik) {
        this.idJazik = idJazik;
    }

    /**
     * @return vraka ima ne jazik
     */
    public String getImeJazik() {
        return imeJazik;
    }

    /**
     * Podesuva jazik na programskiot kod
     *
     * @param imeJazik ime na jazik (C/C++,JS itn)
     */
    public void setImeJazik(String imeJazik) {
        this.imeJazik = imeJazik;
    }

    /**
     * Podesuva tabela kade sto se cuvaat site deca kodovi od programskiot jazik
     *
     * @return HashSet tabela so deca od tip Kod
     */
    public Set<Kod> getHashsetJazik() {
        return hashsetJazik;
    }

    /**
     * 
     * @param Set<Kod> tabela od tip Kod, koi se oznaceni kako deca, Dokolku se
     * 
     */
    public void setHashsetJazik(Set<Kod> hashsetJazik) {
        this.hashsetJazik = hashsetJazik;
    }

    /**
     * 
     *
     * @return String
     */
    @Override
    public String toString() {
        return "JAZIK: " + imeJazik + " ID: " + idJazik + " ";
    }
}
