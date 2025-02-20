/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import cu.GenerarPlanilla;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
@WebServlet(name = "GenerarPlanillaServlet", urlPatterns = {"/generarplanillaservlet"})
public class GenerarPlanillaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String codiPeri=request.getParameter("codiPeri");
            
            JSONObject resultado = null;
            GenerarPlanilla generarPlanilla = new GenerarPlanilla();
            String jsonString = generarPlanilla.existePlanillaGeneradaXPeriodo(Integer.parseInt(codiPeri));
            resultado = new JSONObject(jsonString);
            if (resultado.getString("resultado").equals("si")) {
                if (JOptionPane.YES_OPTION == JOptionPane.showInternalConfirmDialog(null, "Ya existe, desea anular el existente y volver a generar")) {
                    jsonString = generarPlanilla.anularPlanillaGenerada(1, resultado.getString("codigos"));
                    resultado = new JSONObject(jsonString);
                    if (resultado.getString("resultado").equals("ok")) {
                        jsonString = generarPlanilla.generar(codiPeri);
                        resultado = new JSONObject(jsonString);
                    }
                } else {
                    System.out.println("Se cancelo la operacion");
                }
            } else {
                jsonString = generarPlanilla.generar(codiPeri);
                resultado = new JSONObject(jsonString);
            }
            System.out.println(resultado);

        }
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
