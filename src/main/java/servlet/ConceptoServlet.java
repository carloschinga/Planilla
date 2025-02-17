/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.ConceptoDAO;
import dto.Concepto;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
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
@WebServlet(name = "ConceptoServlet", urlPatterns = {"/conceptoservlet/*"})
public class ConceptoServlet extends HttpServlet {

    private final ConceptoDAO conceptoDAO;
    private final EntityManagerFactory emf;

    public ConceptoServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.conceptoDAO = new ConceptoDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();

        // Obtener el parámetro 'plantilla'
        String plantillaParam = request.getParameter("plantilla");
        int plantillaId = 1; // Valor predeterminado

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                if (plantillaParam != null && !plantillaParam.isEmpty()) {
                    try {
                        plantillaId = Integer.parseInt(plantillaParam); // Convertir a entero
                    } catch (NumberFormatException e) {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write("{\"error\":\"El parámetro 'plantilla' debe ser un número entero válido\"}");
                        return;
                    }
                }

                // Obtener los conceptos filtrados por plantilla o todos
                List<Concepto> conceptoList = conceptoDAO.findConceptosByPlantilla(plantillaId);

                // Construir el JSON de respuesta
                JSONArray jsonArray = new JSONArray();
                for (Concepto concepto : conceptoList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiConc", concepto.getCodiConc());
                    jsonObject.put("nombConc", concepto.getNombConc());
                    jsonObject.put("tipoConc", concepto.getTipoConc());
                    jsonObject.put("cateConc", concepto.getCateConc());
                    jsonObject.put("valor", concepto.getValor());
                    jsonObject.put("formula", concepto.getFormula());
                    jsonObject.put("visible", concepto.getVisible());
                    jsonObject.put("ordnConc", concepto.getOrdnConc()); // Asegúrate de que este método exista en la clase Concepto
                    jsonArray.put(jsonObject);
                }

                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(jsonArray.toString());
            } else {

                int conceptoId = Integer.parseInt(pathInfo.substring(1));
                // Lógica para obtener detalles de un concepto específico

                try {

                    Concepto concepto = conceptoDAO.findConcepto(conceptoId);
                    if (concepto == null) {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("{\"error\":\"Concepto no encontrado\"}");
                        return;
                    }

                    // Crear la respuesta con los detalles del concepto
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiConc", concepto.getCodiConc());
                    jsonObject.put("nombConc", concepto.getNombConc());
                    jsonObject.put("tipoConc", concepto.getTipoConc());
                    jsonObject.put("cateConc", concepto.getCateConc());
                    jsonObject.put("valor", concepto.getValor());
                    jsonObject.put("formula", concepto.getFormula());
                    jsonObject.put("visible", concepto.getVisible());
                    jsonObject.put("ordnConc", concepto.getOrdnConc());

                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(jsonObject.toString());
                } catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    response.getWriter().write("{\"error\":\"El parámetro 'id' debe ser un número entero válido\"}");
                }

            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al procesar la solicitud: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Get parameters from request
            String nombConc = request.getParameter("nombConc");
            int tipoConc = Integer.parseInt(request.getParameter("tipoConc"));
            int cateConc = Integer.parseInt(request.getParameter("cateConc"));
            int ordnConc = Integer.parseInt(request.getParameter("ordnConc"));
            int codiPlant = Integer.parseInt(request.getParameter("codiPlant"));
            String valorString = request.getParameter("valor");
            BigDecimal valor = valorString != null && !valorString.isEmpty() ? new BigDecimal(valorString) : null;
            String formula = request.getParameter("formula");
            Boolean visible = Boolean.parseBoolean(request.getParameter("visible"));

            // Create Concepto entity and set values
            Concepto concepto = new Concepto();
            concepto.setNombConc(nombConc);
            concepto.setTipoConc(tipoConc);
            concepto.setOrdnConc(ordnConc);
            concepto.setCateConc(cateConc);
            concepto.setValor(valor);
            concepto.setFormula(formula);
            concepto.setVisible(visible);
            concepto.setCodiPlant(codiPlant);

            conceptoDAO.create(concepto);

            // Return success message
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("{\"message\":\"Registro creado exitosamente\"}");
        } catch (Exception e) {
            // Return error message in case of exception
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al crear el registro: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String contentType = request.getContentType();

            // Parsear los datos del cuerpo como parámetros form-url-encoded
            Map<String, String> paramMap = new HashMap<>();
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String[] pairs = body.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                String key = URLDecoder.decode(keyValue[0], "UTF-8");
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], "UTF-8") : "";
                paramMap.put(key, value);
            }

            // Mapear parámetros al objeto Concepto
            Concepto concepto = new Concepto();
            concepto.setCodiConc(Integer.parseInt(paramMap.get("codiConc")));
            concepto.setNombConc(paramMap.get("nombConc"));
            concepto.setTipoConc(Integer.parseInt(paramMap.get("tipoConc")));
            concepto.setCateConc(Integer.parseInt(paramMap.get("cateConc")));
            concepto.setOrdnConc(Integer.parseInt(paramMap.get("ordnConc")));
            concepto.setCodiPlant(Integer.parseInt(paramMap.get("codiPlant")));

            String valorString = paramMap.get("valor");
            BigDecimal valor = (valorString != null && !valorString.isEmpty()) ? new BigDecimal(valorString) : null;
            concepto.setValor(valor);

            concepto.setFormula(paramMap.get("formula"));
            concepto.setVisible(Boolean.parseBoolean(paramMap.get("visible")));

            // Actualizar el concepto en la base de datos
            conceptoDAO.edit(concepto);

            // Responder con éxito
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\":\"Registro actualizado exitosamente\"}");

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al actualizar el registro: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String pathInfo = request.getPathInfo();
        try {
            if (pathInfo != null && pathInfo.length() > 1) {
                int id = Integer.parseInt(pathInfo.substring(1));
                conceptoDAO.destroy(id);
                response.getWriter().write("{\"message\":\"Registro eliminado exitosamente\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"ID de registro no válido\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al eliminar el registro: " + e.getMessage() + "\"}");
        }
    }
}
