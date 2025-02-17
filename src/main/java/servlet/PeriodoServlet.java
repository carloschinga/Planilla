/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.PeriodoDAO;
import dto.Periodo;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
@WebServlet(name = "PeriodoServlet", urlPatterns = {"/periodoservlet/*"})
public class PeriodoServlet extends HttpServlet {

    private final PeriodoDAO periodoDAO;
    private final EntityManagerFactory emf;

    public PeriodoServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.periodoDAO = new PeriodoDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Obtener todos los registros
                List<Periodo> periodoList = periodoDAO.findPeriodoEntities();
                JSONArray jsonArray = new JSONArray();
                for (Periodo periodo : periodoList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiPeri", periodo.getCodiPeri());
                    jsonObject.put("nombPeri", periodo.getNombPeri());
                    jsonObject.put("actiPeri", periodo.getActiPeri());
                    jsonArray.put(jsonObject);
                }
                response.getWriter().write(jsonArray.toString());
            } else {
                // Obtener un registro por ID
                int id = Integer.parseInt(pathInfo.substring(1));
                Periodo periodo = periodoDAO.findPeriodo(id);
                if (periodo != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiPeri", periodo.getCodiPeri());
                    jsonObject.put("nombPeri", periodo.getNombPeri());
                    jsonObject.put("actiPeri", periodo.getActiPeri());
                    response.getWriter().write(jsonObject.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al obtener los datos: " + e.getMessage()).toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Parse form-encoded parameters
            String codiPeri = request.getParameter("codiPeri");
            String nombPeri = request.getParameter("nombPeri");
            String actiPeri = request.getParameter("actiPeri");

            // Validate required fields
            if (codiPeri == null || nombPeri == null || actiPeri == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new JSONObject()
                        .put("error", "Missing required fields").toString());
                return;
            }

            // Map to Periodo object
            Periodo periodo = new Periodo();
            periodo.setCodiPeri(Integer.parseInt(codiPeri.isEmpty() ? "0" : codiPeri)); // Handle empty string
            periodo.setNombPeri(nombPeri);
            periodo.setActiPeri(Boolean.parseBoolean(actiPeri));

            // Persist the Periodo object
            periodoDAO.create(periodo);

            // Respond with success
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(new JSONObject()
                    .put("message", "Registro creado exitosamente").toString());
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new JSONObject()
                    .put("error", "Invalid number format: " + e.getMessage()).toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject()
                    .put("error", "Error al crear el registro: " + e.getMessage()).toString());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            // Parse the request body as application/x-www-form-urlencoded
            Map<String, String> parameters = new HashMap<>();
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String[] pairs = body.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8.name());
                String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.name());
                parameters.put(key, value);
            }

            // Extract values from the parsed parameters
            int codiPeri = Integer.parseInt(parameters.get("codiPeri"));
            String nombPeri = parameters.get("nombPeri");
            boolean actiPeri = Boolean.parseBoolean(parameters.get("actiPeri"));

            // Update Periodo object
            Periodo periodo = new Periodo();
            periodo.setCodiPeri(codiPeri);
            periodo.setNombPeri(nombPeri);
            periodo.setActiPeri(actiPeri);

            // Persist changes
            periodoDAO.edit(periodo);

            // Respond with success
            response.getWriter().write(new JSONObject().put("message", "Registro actualizado exitosamente").toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al actualizar el registro: " + e.getMessage()).toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo != null && pathInfo.length() > 1) {
                int id = Integer.parseInt(pathInfo.substring(1));
                periodoDAO.destroy(id);
                response.getWriter().write(new JSONObject().put("message", "Registro eliminado exitosamente").toString());
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new JSONObject().put("error", "ID de registro no válido").toString());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al eliminar el registro: " + e.getMessage()).toString());
        }
    }

}
