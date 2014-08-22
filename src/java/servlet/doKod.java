/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import classes.Kod;
import db.helpers.dbKod;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.Konstanti;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Aleksandar
 */
public class doKod extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet doDodadiKod</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet doDodadiKod at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Content-Type", "application/json");

        String jazikID = request.getParameter("jazikID");
        String kodID = request.getParameter("kodID");
        String predmetID = request.getParameter("predmetID");

        List<Kod> listaKodovi = new ArrayList<Kod>();

        PrintWriter pw = response.getWriter();
        JSONObject jsnObj;
        JSONArray jsnArray = new JSONArray();
        if (jazikID != null) {
            listaKodovi = dbKod.vratiKodZaJazik(jazikID);
            for (Kod kod : listaKodovi) {
                try {
                    jsnObj = new JSONObject();
                    jsnObj.put("ID", kod.getIdKod());
                    jsnObj.put("Naslov", kod.getNaslovKod());
                    jsnArray.put(jsnObj);
                } catch (JSONException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            pw.print(jsnArray);
        } else if (predmetID != null) {
            listaKodovi = dbKod.vratiKodZaPredmet(predmetID);
            for (Kod kod : listaKodovi) {
                try {
                    jsnObj = new JSONObject();
                    jsnObj.put("ID", kod.getIdKod());
                    jsnObj.put("Naslov", kod.getNaslovKod());
                    jsnArray.put(jsnObj);
                } catch (JSONException ex) {
                    Logger.getLogger(doKod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pw.print(jsnArray);
        } else if (kodID != null) {
            Kod kod = dbKod.vratiKod(kodID);
            if (kod != null) {
                jsnObj = new JSONObject();
                try {
                    jsnObj.put("naslov", kod.getNaslovKod());
                    jsnObj.put("opis", kod.getOpisKod());
                    jsnObj.put("jazik",kod.getJazikKod().getImeJazik());
                    JSONArray kodStrani = new JSONArray();
                    for(int i = 0; i < kod.getBrojStraniciKod(); i++){
                        JSONObject jKod = new JSONObject();
                        jKod.put("kod", kod.getSodrzinaKod(i));   
                        kodStrani.put(i,jKod);
                    }
                    jsnObj.put("kodStrana", kodStrani);
                } catch (JSONException ex) {
                    Logger.getLogger(doKod.class.getName()).log(Level.SEVERE, null, ex);
                }
                pw.print(jsnObj);
            }else{
                JSONObject obj = new JSONObject();
                try {
                    obj.put("error", "Кодот со ид " + kodID + "не е постои");
                    pw.print(obj);
                } catch (JSONException ex) {
                    Logger.getLogger(doKod.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Se cita broj na strani
        int brojStrani = Integer.parseInt(request.getParameter(Konstanti.kodBrStraniInt));
        // Se cita naslov na kod 
        String naslovKod = request.getParameter(Konstanti.kodNaslovString);
        // Se cita opis na kod
        String opisKod = request.getParameter(Konstanti.kodOpisString);
        // se Cita programskiot jazik t.e vrednsota ID to od soodvetno od baza
        Integer jazikId = Integer.parseInt(request.getParameter(Konstanti.kodJazik));
        // se Citat predmetite za koj kodot se vnesuva
        String[] indeksiPredmet = request.getParameterValues(Konstanti.kodPredmeti);
        // gi parsirame vo integer
        int[] predmetiId = new int[1];
        predmetiId[0]=-1;
        //proverka dali vrateniot parametar e null
        if (indeksiPredmet != null) {
            //incializirame nizata od integer na onolku elementi kolku se ni potrebni
            predmetiId = new int[indeksiPredmet.length];
            //parsirame od string vo int elementite so for ciklus
            for (int i = 0; i < indeksiPredmet.length; i++) {
                predmetiId[i] = Integer.parseInt(indeksiPredmet[i]);
            }
        }
        // Se citaat site strani vneseni so kod:
        String[] sodrzinaKod = new String[brojStrani];
        for (int i = 0; i <= brojStrani; i++) {
            // Prvo se cita vo privremena promenliva
            String privSodrzina = request.getParameter(Konstanti.kodSodrzinaIntNiza + (i + 1));
            // Potoa ako funkcijata vrati null ili sodrzinata na kodot e prazen 
            // operacijata ne se izvrsuva.
            if (privSodrzina != null && !privSodrzina.equals("")) {
                // Dokolku funkcija ne e null i sodrzinata ne e prazna se dodava vo nizata od sodrzina
                sodrzinaKod[i] = privSodrzina;
            }
        }
        // Od site zemeni podatoci se kreira objekt
        Kod kod = new Kod(naslovKod, brojStrani, opisKod, sodrzinaKod);
        try {
            // Se povikuva funkcijata za vnesuvanje kod.
            dbKod.vnesiKod(kod, jazikId, predmetiId);
            request.setAttribute("ActionResult", "Successfull");
           
        } catch (Exception ex) {
           System.out.println(ex.getMessage() + "\n" + ex.getStackTrace());
           request.setAttribute("ActionResult", "Failed");
        }
        
         RequestDispatcher dp = request.getRequestDispatcher("AdminPanel.jsp");
            dp.forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
