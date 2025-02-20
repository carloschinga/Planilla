/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.AuxiliarDAO;
import dto.Auxiliar;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AuxiliarServlet", urlPatterns = {"/auxiliarservlet/*"})
public class AuxiliarServlet extends HttpServlet {

    private final AuxiliarDAO auxiliarDAO;
    private final EntityManagerFactory emf;

    public AuxiliarServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.auxiliarDAO = new AuxiliarDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        String periodoParam = request.getParameter("periodo");
        int periodoId = 1; // Valor predeterminado

        try (PrintWriter out = response.getWriter()) {
            if (periodoParam != null && !periodoParam.isEmpty()) {
                try {
                    periodoId = Integer.parseInt(periodoParam); // Convertir a entero
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.write("{\"error\":\"El parámetro 'periodo' debe ser un número entero válido\"}");
                    return;
                }
            }

            if (pathInfo == null || pathInfo.equals("/")) {
                // Obtener todos los registros filtrados por periodo
                List<Auxiliar> auxiliarList = auxiliarDAO.findAuxiliarByPeriodo(periodoId);
                JSONArray jsonArray = new JSONArray();

                for (Auxiliar auxiliar : auxiliarList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiAux", auxiliar.getCodiAux());
                    jsonObject.put("nombAux", auxiliar.getNombAux());
                    jsonObject.put("codiPeri", auxiliar.getCodiPeri());
                    jsonObject.put("actvAux", auxiliar.getActvAux());
                    jsonArray.put(jsonObject);
                }

                out.write(jsonArray.toString());
            } else {
                // Validar que el path contiene un ID numérico
                String idStr = pathInfo.substring(1);
                int id;

                try {
                    id = Integer.parseInt(idStr);
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.write("{\"error\":\"El ID proporcionado no es válido\"}");
                    return;
                }

                // Obtener un registro por ID
                Auxiliar auxiliar = auxiliarDAO.findAuxiliar(id);
                if (auxiliar != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiAux", auxiliar.getCodiAux());
                    jsonObject.put("nombAux", auxiliar.getNombAux());
                    jsonObject.put("codiPeri", auxiliar.getCodiPeri());
                    jsonObject.put("actvAux", auxiliar.getActvAux());
                    out.write(jsonObject.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.write("{\"error\":\"No se encontró el auxiliar con el ID especificado\"}");
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
        String pathInfo = request.getPathInfo();

        try {
            // Obtener los parámetros del formulario
            String codiAux = request.getParameter("codiAux");
            String nombAux = request.getParameter("nombAux");
            String codiPeri = request.getParameter("codiPeri");
            String actvAux = request.getParameter("actvAux");

            // Crear el objeto Auxiliar y asignar valores
            Auxiliar auxiliar = new Auxiliar();
            auxiliar.setCodiAux(Integer.parseInt(codiAux.isEmpty() ? "0" : codiAux));
            auxiliar.setNombAux(nombAux);
            auxiliar.setCodiPeri(Integer.parseInt(codiPeri));
            auxiliar.setActvAux(Boolean.parseBoolean(actvAux));

            // Crear registro en la base de datos
            auxiliarDAO.create(auxiliar);

            // Responder con un mensaje de éxito
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(new JSONObject().put("message", "Registro creado exitosamente").toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al crear el registro: " + e.getMessage()).toString());
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
                String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8.name());
                String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.name());
                parameters.put(key, value);
            }

            int codiAux = Integer.parseInt(parameters.get("codiAux"));
            String nombAux = parameters.get("nombAux");
            int codiPeri = Integer.parseInt(parameters.get("codiPeri"));
            boolean actvAux = Boolean.parseBoolean(parameters.get("actvAux"));

            // Update Auxiliar object
            Auxiliar auxiliar = new Auxiliar();
            auxiliar.setCodiAux(codiAux);
            auxiliar.setNombAux(nombAux);
            auxiliar.setCodiPeri(codiPeri);
            auxiliar.setActvAux(actvAux);

            // Actualizar registro
            auxiliarDAO.edit(auxiliar);
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
                // Eliminar registro por ID
                int id = Integer.parseInt(pathInfo.substring(1));
                auxiliarDAO.destroy(id);
                response.getWriter().write(new JSONObject().put("message", "Registro eliminado exitosamente").toString());
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new JSONObject().put("error", "Debe proporcionar un ID válido").toString());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al eliminar el registro: " + e.getMessage()).toString());
        }
    }
}
