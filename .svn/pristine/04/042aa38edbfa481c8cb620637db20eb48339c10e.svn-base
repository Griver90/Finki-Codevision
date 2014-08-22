/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 * Model za novost
 * @author Riste
 */
public class Novost {

   //ID na novosta(kod ili predmet)
    private int idNovost;
    
    
    
    //Sodrzinata na predmet ili kod koj e dodaden
    private String sodrzinaNovost;
    
     //Datum na dodavanje na predmet ili kod
    private String datumNovost;
    
    //Informacii za (predmetid,kodid i jazikid)
    private String urlInfo;
    
   
    
    
    //Pocetok set i get metodi
     
      /**
     *
     * @return Integer Go vrakja ID-to na novosta
     */
    public int getIdNovost() {
        return idNovost;
    }

      /**
     * Go postavuva ID-to na novosta 
     *
     * @param idNovost Prima int
     */
    public void setIdNovost(int idNovost) {
        this.idNovost = idNovost;
    }

    

    /**
     * 
     * @return String Ja vrakja sodrzinata na novosta
     */
    public String getSodrzinaNovost() {
        return sodrzinaNovost;
    }

      /**
     * Ja postavuva sodrzinata na novosta 
     *
     * @param sodrzinaNovost Prima String
     */
    public void setSodrzinaNovost(String sodrzinaNovost) {
        this.sodrzinaNovost = sodrzinaNovost;
    }

     /**
     * 
     * @return String Go vrakja datumot na dodadeniot predmet ili kod
     */
    public String getDatumNovost() {
        return datumNovost;
    }

     /**
     * Postavuva datum na novosta 
     *
     * @param datumNovost Datum koga e dodaden predmetot ili kodot
     */
    public void setDatumNovost(String datumNovost) {
        this.datumNovost = datumNovost;
    }
    
    /**
     * 
     * @return String vrakja string vo format id-predmet-idjazik-idkod
     */
    public String getUrlInfo() {
       return urlInfo;
    }

     /**
     * Postavuva informacii za novosta
     *
     * @param urlInfo Prima String
     */
    public void setUrlInfo(String urlInfo) {
        this.urlInfo = urlInfo;
    }
    
 //Kraj na set i get metodite
   
     /**
     * Preoptovaruvanje na toString metodata za pecatenje na Novosta(kod ili predmet)
     *
     * @return String Formatiran text za novosta
     */
    @Override
    public String toString() {
        return sodrzinaNovost + " Датум: " + datumNovost + " ID: " + idNovost;
    }
}
