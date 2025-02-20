/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;


import cu.CalcularPlanilla;
import cu.GenerarPlanilla;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
@WebServlet(name = "PlanillaServlet", urlPatterns = {"/planillaservlet"})
public class PlanillaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();

        try {
            JSONObject resultado = null;
            String jsonString="";
            if (pathInfo == null || pathInfo.equals("/")) {

                String accion = request.getParameter("accion");
                String periodo =request.getParameter("periodo");
                switch (accion) {
                    case "1":
                        GenerarPlanilla generarPlanilla = new GenerarPlanilla();
                        jsonString = generarPlanilla.existePlanillaGeneradaXPeriodo(Integer.parseInt(periodo));
                        resultado = new JSONObject(jsonString);
                        if (resultado.getString("resultado").equals("si")) {
                            //if (JOptionPane.YES_OPTION == JOptionPane.showInternalConfirmDialog(null, "Ya existe, desea anular el existente y volver a generar")) {
                            jsonString = generarPlanilla.anularPlanillaGenerada(Integer.parseInt(periodo), resultado.getString("codigos"));
                            resultado = new JSONObject(jsonString);
                            if (resultado.getString("resultado").equals("ok")) {
                                jsonString = generarPlanilla.generar(periodo);
                                resultado = new JSONObject(jsonString);
                            }
                            /*} else {
                        System.out.println("Se cancelo la operacion");
                    }*/
                        } else {
                            jsonString = generarPlanilla.generar(periodo);
                            resultado = new JSONObject(jsonString);
                        }
                        break;
                    case "2":
                        CalcularPlanilla calculator = new CalcularPlanilla();
                        jsonString = calculator.procesar(Integer.parseInt(periodo));
                        resultado = new JSONObject(jsonString);
                        break;
                }
                response.getWriter().write(resultado.toString());
            } else {

            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al obtener los datos: " + e.getMessage()).toString());
        }
    }
}
