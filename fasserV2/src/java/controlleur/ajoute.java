/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Etudiant;

/**
 *
 * @author zayoud_mohanned
 */
public class ajoute extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private int count;
    public void init() throws ServletException{
        count=6;
    }
    protected Cookie newCookie(HttpServletResponse req,HttpServletRequest res, String name, String value) {
        Cookie[] cookies = res.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie; // Return existing cookie if found
                }
            }
        }

        Cookie c = new Cookie(name, value);
        c.setMaxAge(60);
        req.addCookie(c);
        return c;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy");
            SimpleDateFormat ds = new SimpleDateFormat("yyyy-mm-dd");
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String cin = request.getParameter("cin");
            Date dns;
        try {
            dns = dt.parse(request.getParameter("dns"));
            dns = ds.parse(dns.toString());
        } catch (ParseException ex) {
            dns = new Date();
        }
            String classe = request.getParameter("classe");
            EtudiantJpaController e = new EtudiantJpaController();
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Etudiant ex= new Etudiant(id,nom, prenom,cin,dns,classe);
        try {
            e.create(ex);
            out.println("etudiant bien insret");
        } catch (Exception ex1) {
            out.println("error");
        }
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            //out.println(request.getSession().toString());
            String str = this.getInitParameter("initial_counter_value");
            Cookie c = newCookie(response,request,"mohanned","zayoud");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ajoute</title>");            
            out.println("</head>");
            
           out.println("<body>");
           HttpSession s = request.getSession();
           /*out.println(str);
            out.println(c.getName()+" "+c.getValue());
            out.println(++count);*/
            out.println("<h1><a href ='./'>home</a></h1>");
            s.invalidate();
            out.println("</body>");
            out.println("</html>");
            
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
