/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.PlanillaConceptoDAO;
import dao.VistaPlanillaConceptoDAO;
import dto.PlanillaConcepto;
import dto.VistaPlanillaConcepto;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "PlanillaConceptoServlet", urlPatterns = {"/planillaconceptoservlet/*"})
public class PlanillaConceptoServlet extends HttpServlet {

    private final PlanillaConceptoDAO planillaConceptoDAO;
    private final VistaPlanillaConceptoDAO vistaPlanillaConceptoDAO;
    private final EntityManagerFactory emf;

    public PlanillaConceptoServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.planillaConceptoDAO = new PlanillaConceptoDAO(emf);
        this.vistaPlanillaConceptoDAO= new VistaPlanillaConceptoDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        
        emf.getCache().evict(VistaPlanillaConcepto.class);
        
        String pathInfo = request.getPathInfo();

        String periodoParam = request.getParameter("periodo");
        int periodoId = 1; // Valor predeterminado
        int personaId = 1;
        try {
            if (pathInfo == null || pathInfo.equals("/")) {

                if (periodoParam != null && !periodoParam.isEmpty()) {
                    try {
                        periodoId = Integer.parseInt(periodoParam); // Convertir a entero
                        String personaParam = request.getParameter("persona");
                        //corregir aqui
                        if(personaParam.equals("null")){
                            personaParam="1";
                        }
                        personaId = Integer.parseInt(personaParam);
                    } catch (NumberFormatException e) {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getWriter().write("{\"error\":\"El parámetro 'plantilla' debe ser un número entero válido\"}");
                        return;
                    }
                }
                // Obtener todos los registros
                List<VistaPlanillaConcepto> conceptos = vistaPlanillaConceptoDAO.findPlanillaConceptosByPeriodoAndPersona(periodoId, personaId);
                JSONArray jsonArray = new JSONArray();
                for (VistaPlanillaConcepto concepto : conceptos) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiDeta", concepto.getCodiDeta());
                    jsonObject.put("codiPlani", concepto.getCodiPlani());
                    jsonObject.put("codiConc", concepto.getCodiConc());
                    jsonObject.put("nombConc", concepto.getNombConc());
                    jsonObject.put("ordnConc", concepto.getOrdnConc());
                    jsonObject.put("tipoConc", concepto.getTipoConc());
                    jsonObject.put("cateConc", concepto.getCateConc());
                    jsonObject.put("valor", concepto.getValor());
                    jsonObject.put("formula", concepto.getFormula());
                    jsonObject.put("visible", concepto.getVisible());
                    jsonObject.put("actvDeta", concepto.getActvDeta());
                    jsonArray.put(jsonObject);
                }
                response.getWriter().write(jsonArray.toString());
            } else {
                // Obtener un registro por ID
                int id = Integer.parseInt(pathInfo.substring(1));
                PlanillaConcepto concepto = planillaConceptoDAO.findPlanillaConcepto(id);
                if (concepto != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiDeta", concepto.getCodiDeta());
                    jsonObject.put("codiPlani", concepto.getCodiPlani());
                    jsonObject.put("codiConc", concepto.getCodiConc());
                    jsonObject.put("nombConc", concepto.getNombConc());
                    jsonObject.put("ordnConc", concepto.getOrdnConc());
                    jsonObject.put("tipoConc", concepto.getTipoConc());
                    jsonObject.put("cateConc", concepto.getCateConc());
                    jsonObject.put("valor", concepto.getValor());
                    jsonObject.put("formula", concepto.getFormula());
                    jsonObject.put("visible", concepto.getVisible());
                    jsonObject.put("actvDeta", concepto.getActvDeta());
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

        try {
            // Obtener los parámetros del formulario
            String codiPlani = request.getParameter("codiPlani");
            String codiConc = request.getParameter("codiConc");
            String nombConc = request.getParameter("nombConc");
            String ordnConc = request.getParameter("ordnConc");
            String tipoConc = request.getParameter("tipoConc");
            String cateConc = request.getParameter("cateConc");
            String valor = request.getParameter("valor");
            String formula = request.getParameter("formula");
            String visible = request.getParameter("visible");
            String actvDeta = request.getParameter("actvDeta");

            // Crear el objeto PlanillaConcepto y asignar valores
            PlanillaConcepto concepto = new PlanillaConcepto();
            concepto.setCodiPlani(Integer.parseInt(codiPlani));
            concepto.setCodiConc(Integer.parseInt(codiConc));
            concepto.setNombConc(nombConc);
            concepto.setOrdnConc(Integer.parseInt(ordnConc));
            concepto.setTipoConc(Integer.parseInt(tipoConc));
            concepto.setCateConc(Integer.parseInt(cateConc));
            concepto.setValor(new BigDecimal(valor));
            concepto.setFormula(formula);
            concepto.setVisible(Boolean.parseBoolean(visible));
            concepto.setActvDeta(Boolean.parseBoolean(actvDeta));

            // Crear registro en la base de datos
            planillaConceptoDAO.create(concepto);

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

            int codiDeta = Integer.parseInt(parameters.get("codiDeta"));
            String codiPlani = parameters.get("codiPlani");
            String codiConc = parameters.get("codiConc");
            String nombConc = parameters.get("nombConc");
            String ordnConc = parameters.get("ordnConc");
            String tipoConc = parameters.get("tipoConc");
            String cateConc = parameters.get("cateConc");
            String valor = parameters.get("valor");
            String formula = parameters.get("formula");
            String visible = parameters.get("visible");
            String actvDeta = parameters.get("actvDeta");

            // Actualizar el objeto PlanillaConcepto
            PlanillaConcepto concepto = planillaConceptoDAO.findPlanillaConcepto(codiDeta);
            if (concepto != null) {
                concepto.setCodiPlani(Integer.parseInt(codiPlani));
                concepto.setCodiConc(Integer.parseInt(codiConc));
                concepto.setNombConc(nombConc);
                concepto.setOrdnConc(Integer.parseInt(ordnConc));
                concepto.setTipoConc(Integer.parseInt(tipoConc));
                concepto.setCateConc(Integer.parseInt(cateConc));
                concepto.setValor(new BigDecimal(valor));
                concepto.setFormula(formula);
                concepto.setVisible(Boolean.parseBoolean(visible));
                concepto.setActvDeta(Boolean.parseBoolean(actvDeta));

                planillaConceptoDAO.edit(concepto);
                response.getWriter().write(new JSONObject().put("message", "Registro actualizado exitosamente").toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(new JSONObject().put("error", "Registro no encontrado").toString());
            }
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
                planillaConceptoDAO.destroy(id);
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
