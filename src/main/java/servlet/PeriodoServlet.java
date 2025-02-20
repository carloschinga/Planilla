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

    private JSONObject periodoToJson(Periodo periodo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("codiPeri", periodo.getCodiPeri());
        jsonObject.put("nombPeri", periodo.getNombPeri());
        jsonObject.put("actiPeri", periodo.getActiPeri());
        jsonObject.put("selePeri", periodo.getSelePeri());
        return jsonObject;
    }

    private void writeError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        JSONObject errorJson = new JSONObject();
        errorJson.put("error", message);
        response.getWriter().write(errorJson.toString());
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
                    jsonArray.put(periodoToJson(periodo));
                }
                response.getWriter().write(jsonArray.toString());
            } else if (pathInfo.equals("/activos")) {
                // Obtener registros activos
                List<Periodo> periodoList = periodoDAO.findPeriodoEntities();
                JSONArray jsonArray = new JSONArray();
                for (Periodo periodo : periodoList) {
                    if (Boolean.TRUE.equals(periodo.getActiPeri())) {
                        jsonArray.put(periodoToJson(periodo));
                    }
                }
                response.getWriter().write(jsonArray.toString());
            } else {
                // Obtener un registro por ID
                int id = Integer.parseInt(pathInfo.substring(1));
                Periodo periodo = periodoDAO.findPeriodo(id);
                if (periodo != null) {
                    response.getWriter().write(periodoToJson(periodo).toString());
                } else {
                    writeError(response, HttpServletResponse.SC_NOT_FOUND, "Registro no encontrado");
                }
            }
        } catch (Exception e) {
            writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los datos: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Se asume que en POST no se envía el ID ya que se genera automáticamente.
            String nombPeri = request.getParameter("nombPeri");
            String actiPeri = request.getParameter("actiPeri");
            String selePeri = request.getParameter("selePeri");

            if (nombPeri == null || actiPeri == null || selePeri == null) {
                writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Faltan campos requeridos");
                return;
            }

            Periodo periodo = new Periodo();
            periodo.setNombPeri(nombPeri);
            periodo.setActiPeri(Boolean.parseBoolean(actiPeri));
            periodo.setSelePeri(Boolean.parseBoolean(selePeri));

            periodoDAO.create(periodo);

            response.setStatus(HttpServletResponse.SC_CREATED);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("message", "Registro creado exitosamente");
            jsonResponse.put("codiPeri", periodo.getCodiPeri());
            response.getWriter().write(jsonResponse.toString());
        } catch (NumberFormatException e) {
            writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Formato numérico inválido: " + e.getMessage());
        } catch (Exception e) {
            writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear el registro: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            Map<String, String> parameters = new HashMap<>();
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String[] pairs = body.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8.name());
                    String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.name());
                    parameters.put(key, value);
                }
            }

            int codiPeri = Integer.parseInt(parameters.get("codiPeri"));
            String nombPeri = parameters.get("nombPeri");
            boolean actiPeri = Boolean.parseBoolean(parameters.get("actiPeri"));
            boolean selePeri = Boolean.parseBoolean(parameters.get("selePeri"));

            Periodo periodo = periodoDAO.findPeriodo(codiPeri);
            if (periodo == null) {
                writeError(response, HttpServletResponse.SC_NOT_FOUND, "Registro no encontrado");
                return;
            }

            periodo.setNombPeri(nombPeri);
            periodo.setActiPeri(actiPeri);
            periodo.setSelePeri(selePeri);

            periodoDAO.edit(periodo);

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("message", "Registro actualizado exitosamente");
            response.getWriter().write(jsonResponse.toString());
        } catch (NumberFormatException e) {
            writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Formato numérico inválido: " + e.getMessage());
        } catch (Exception e) {
            writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el registro: " + e.getMessage());
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
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("message", "Registro eliminado exitosamente");
                response.getWriter().write(jsonResponse.toString());
            } else {
                writeError(response, HttpServletResponse.SC_BAD_REQUEST, "ID de registro no válido");
            }
        } catch (Exception e) {
            writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el registro: " + e.getMessage());
        }
    }
}
