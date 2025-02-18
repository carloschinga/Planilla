/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.AfpDAO;
import dto.Afp;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.math.BigDecimal;
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
@WebServlet(name = "AfpServlet", urlPatterns = {"/afpservlet/*"})
public class AfpServlet extends HttpServlet {

    private final AfpDAO afpDAO;
    private final EntityManagerFactory emf;

    public AfpServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.afpDAO = new AfpDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Obtener todos los registros
                List<Afp> afpList = afpDAO.findAfpEntities();
                afpList.removeIf(afp -> afp.getCodiAFP() == 1);  // Filtrar si es necesario
                JSONArray jsonArray = new JSONArray();
                for (Afp afp : afpList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiAFP", afp.getCodiAFP());
                    jsonObject.put("nombAFP", afp.getNombAFP());
                    jsonObject.put("montAFP", afp.getMontAFP());
                    jsonObject.put("seguAFP", afp.getSeguAFP());
                    jsonObject.put("comiAFP", afp.getComiAFP());
                    jsonArray.put(jsonObject);
                }
                response.getWriter().write(jsonArray.toString());
            } else {
                // Obtener un registro por ID
                int id = Integer.parseInt(pathInfo.substring(1));  // Obtener ID después del '/'
                Afp afp = afpDAO.findAfp(id);
                if (afp != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiAFP", afp.getCodiAFP());
                    jsonObject.put("nombAFP", afp.getNombAFP());
                    jsonObject.put("montAFP", afp.getMontAFP());
                    jsonObject.put("seguAFP", afp.getSeguAFP());
                    jsonObject.put("comiAFP", afp.getComiAFP());
                    response.getWriter().write(jsonObject.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(new JSONObject().put("error", "AFP no encontrado").toString());
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al obtener los datos: " + e.getMessage()).toString());
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

            int codiAFP = Integer.parseInt(parameters.get("codiAFP"));
            String nombAFP = String.valueOf(parameters.get("nombAFP"));
            BigDecimal montAFP = new BigDecimal(parameters.get("montAFP"));
            BigDecimal seguAFP = new BigDecimal(parameters.get("seguAFP"));
            BigDecimal comiAFP = new BigDecimal(parameters.get("comiAFP"));

            // Update Periodo object
            Afp afp = new Afp();
            afp.setCodiAFP(codiAFP);
            afp.setNombAFP(nombAFP);
            afp.setMontAFP(montAFP);
            afp.setSeguAFP(seguAFP);
            afp.setComiAFP(comiAFP);

            // Actualizar registro
            afpDAO.edit(afp);
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
                afpDAO.destroy(id);
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
