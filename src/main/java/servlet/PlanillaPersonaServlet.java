/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.VistaPlanillaPersonaDAO;
import dto.VistaPlanilaPersona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
@WebServlet(name = "PlanillaPersonaServlet", urlPatterns = {"/planillapersonaservlet"})
public class PlanillaPersonaServlet extends HttpServlet {

    private final VistaPlanillaPersonaDAO vistaPlanillaPersonaDAO;
    private final EntityManagerFactory emf;

    public PlanillaPersonaServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.vistaPlanillaPersonaDAO = new VistaPlanillaPersonaDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();

        // Obtener el parámetro 'plantilla'
        String periodoParam = request.getParameter("periodo");
        int periodoId = 1; // Valor predeterminado

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                if (periodoParam != null && !periodoParam.isEmpty()) {
                    try {
                        periodoId = Integer.parseInt(periodoParam); // Convertir a entero
                    } catch (NumberFormatException e) {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write("{\"error\":\"El parámetro 'plantilla' debe ser un número entero válido\"}");
                        return;
                    }
                }

                // Obtener los conceptos filtrados por plantilla o todos
                List<VistaPlanilaPersona> vistaPlanilaList = vistaPlanillaPersonaDAO.listaPlanillaPersonaXPeriodo(periodoId);

                // Construir el JSON de respuesta
                JSONArray jsonArray = new JSONArray();
                for (VistaPlanilaPersona vistaPlanilaPersona : vistaPlanilaList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiPlani", vistaPlanilaPersona.getCodiPlani());
                    jsonObject.put("codiPeri", vistaPlanilaPersona.getCodiPeri());
                    jsonObject.put("codiPers", vistaPlanilaPersona.getCodiPers());
                    jsonObject.put("actvPlani", vistaPlanilaPersona.getActiPlani());
                    jsonObject.put("nombPers", vistaPlanilaPersona.getNombPers());// Asegúrate de que este método exista en la clase Concepto
                    jsonArray.put(jsonObject);
                }

                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(jsonArray.toString());
            } else {

                

            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al procesar la solicitud: " + e.getMessage() + "\"}");
        }
    }

}
