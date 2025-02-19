/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.AuxiliarDetalleDAO;
import dto.AuxiliarDetalle;
import dto.VistaAuxiliarDetallePersona;
import java.io.IOException;
import java.math.BigDecimal;
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
@WebServlet(name = "AuxiliarDetalleServlet", urlPatterns = {"/auxiliardetalleservlet/*"})
public class AuxiliarDetalleServlet extends HttpServlet {

    private final AuxiliarDetalleDAO auxiliarDetalleDAO;
    private final EntityManagerFactory emf;

    public AuxiliarDetalleServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.auxiliarDetalleDAO = new AuxiliarDetalleDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();

        // Obtener el parámetro 'auxiliar'
        String auxiliarParam = request.getParameter("auxiliar");
        int auxiliarId = 1; // Valor predeterminado

        if (auxiliarParam == null) {
            JSONObject jsonObject = new JSONObject();
            response.getWriter().write(jsonObject.toString());
        } else {
            try {
                if (pathInfo == null || pathInfo.equals("/")) {

                    if (auxiliarParam != null && !auxiliarParam.isEmpty()) {
                        try {
                            auxiliarId = Integer.parseInt(auxiliarParam); // Convertir a entero
                        } catch (NumberFormatException e) {
                            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                            response.getWriter().write("{\"error\":\"El parámetro 'auxiliar' debe ser un número entero válido\"}");
                            return;
                        }
                    }

                    // Obtener todos los registros
                    List<VistaAuxiliarDetallePersona> auxiliarDetalleList = auxiliarDetalleDAO.findAuxiliarByPeriodo(auxiliarId);
                    JSONArray jsonArray = new JSONArray();
                    for (VistaAuxiliarDetallePersona detalle : auxiliarDetalleList) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("codiDetaAux", detalle.getCodiDetaAux());
                        jsonObject.put("codiAux", detalle.getCodiAux());
                        jsonObject.put("codiPers", detalle.getCodiPers());
                        jsonObject.put("nombPers", detalle.getNombPers());
                        jsonObject.put("descDeta", detalle.getDescDeta());
                        jsonObject.put("montDeta", detalle.getMontDeta());
                        jsonArray.put(jsonObject);
                    }
                    response.getWriter().write(jsonArray.toString());
                } else {
                    // Obtener un registro por ID
                    int id = Integer.parseInt(pathInfo.substring(1));
                    AuxiliarDetalle detalle = auxiliarDetalleDAO.findAuxiliarDetalle(id);
                    if (detalle != null) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("codiDetaAux", detalle.getCodiDetaAux());
                        jsonObject.put("codiAux", detalle.getCodiAux());
                        jsonObject.put("codiPers", detalle.getCodiPers());
                        jsonObject.put("descDeta", detalle.getDescDeta());
                        jsonObject.put("montDeta", detalle.getMontDeta());
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        try {
            // Obtener los parámetros del formulario
            String codiAux = request.getParameter("codiAux");
            String codiPers = request.getParameter("codiPers");
            String descDeta = request.getParameter("descDeta");
            String montDeta = request.getParameter("montDeta");

            // Crear el objeto AuxiliarDetalle y asignar valores
            AuxiliarDetalle detalle = new AuxiliarDetalle();
            detalle.setCodiAux(Integer.parseInt(codiAux));
            detalle.setCodiPers(Integer.parseInt(codiPers));
            detalle.setDescDeta(descDeta);
            detalle.setMontDeta(new BigDecimal(montDeta));

            // Crear registro en la base de datos
            auxiliarDetalleDAO.create(detalle);

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

            int codiDetaAux = Integer.parseInt(parameters.get("codiDetaAux"));
            int codiAux = Integer.parseInt(parameters.get("codiAux"));
            int codiPers = Integer.parseInt(parameters.get("codiPers"));
            String descDeta = parameters.get("descDeta");
            BigDecimal montDeta = new BigDecimal(parameters.get("montDeta"));

            // Update AuxiliarDetalle object
            AuxiliarDetalle detalle = new AuxiliarDetalle();
            detalle.setCodiDetaAux(codiDetaAux);
            detalle.setCodiAux(codiAux);
            detalle.setCodiPers(codiPers);
            detalle.setDescDeta(descDeta);
            detalle.setMontDeta(montDeta);

            // Actualizar registro
            auxiliarDetalleDAO.edit(detalle);
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
                auxiliarDetalleDAO.destroy(id);
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
