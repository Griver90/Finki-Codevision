/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import classes.Predmet;
import db.helpers.FTP;
import db.helpers.FileHandler;
import db.helpers.dbHelper;
import db.helpers.dbPredmet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aleksandar
 */
@WebServlet(name = "doSmeniPredmet", urlPatterns = {"/doSmeniPredmet"})
public class doSmeniPredmet extends HttpServlet {

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
            out.println("<title>Servlet doSmeniPredmet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet doSmeniPredmet at " + request.getContextPath() + "</h1>");
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
        
        PrintWriter pw = response.getWriter();
        try{
            String id = request.getParameter("ID");
            dbPredmet.brisiPredmet(id);
            pw.print("Uspesno izbrisan predmet");
        }
        catch(Exception ex){
            pw.print("Error...."+ex);
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
        
            PrintWriter pw = response.getWriter();
            
            try {
                String name = request.getParameter("predmetName");
                String opis = request.getParameter("opisPredmet");
                int semestar = Integer.parseInt(request.getParameter("semestar"));
                int ID = Integer.parseInt(request.getParameter("ID"));
                Predmet p = new Predmet(name, semestar, opis);
                p.setIdPredmet(ID);
                dbPredmet.promeniPredmet(p);
                response.setStatus(200);
                pw.print("Uspesno smeneet predmet");
            } catch (Exception ex) {
                  pw.print("Error...."+ex);
            }
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
