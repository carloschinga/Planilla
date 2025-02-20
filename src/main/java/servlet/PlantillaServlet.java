package servlet;

import dao.PlantillaDAO;
import dto.Plantilla;
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
 * Servlet that handles CRUD operations for Plantilla entity.
 *
 */
@WebServlet(name = "PlantillaServlet", urlPatterns = {"/plantillaservlet/*"})
public class PlantillaServlet extends HttpServlet {

    private final PlantillaDAO plantillaDAO;
    private final EntityManagerFactory emf;

    public PlantillaServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.plantillaDAO = new PlantillaDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();

        try (PrintWriter out = response.getWriter()) {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Obtener todos los registros
                List<Plantilla> plantillaList = plantillaDAO.findPlantillaEntities();
                JSONArray jsonArray = new JSONArray();
                for (Plantilla plantilla : plantillaList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiPlant", plantilla.getCodiPlant());
                    jsonObject.put("nombPlant", plantilla.getNombPlant());
                    jsonObject.put("actvPlant", plantilla.getActvPlant());
                    jsonArray.put(jsonObject);
                }
                out.write(jsonArray.toString());
            } else if (pathInfo.equals("/activos")) {
                List<Plantilla> plantillaList = plantillaDAO.listarActivos();
                JSONArray jsonArray = new JSONArray();
                for (Plantilla plantilla : plantillaList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiPlant", plantilla.getCodiPlant());
                    jsonObject.put("nombPlant", plantilla.getNombPlant());
                    jsonObject.put("actvPlant", plantilla.getActvPlant());
                    jsonArray.put(jsonObject);
                }
                out.write(jsonArray.toString());
            }
            else
            {
               
                try {
                    int id = Integer.parseInt(pathInfo.substring(1));
                    Plantilla plantilla = plantillaDAO.findPlantilla(id);
                    if (plantilla != null) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("codiPlant", plantilla.getCodiPlant());
                        jsonObject.put("nombPlant", plantilla.getNombPlant());
                        jsonObject.put("actvPlant", plantilla.getActvPlant());
                        out.write(jsonObject.toString());
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        out.write(new JSONObject().put("error", "Registro no encontrado").toString());
                    }
                } catch (NumberFormatException nfe) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.write(new JSONObject().put("error", "Formato de ID no válido").toString());
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject()
                    .put("error", "Error al obtener los datos: " + e.getMessage()).toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            String nombPlant = request.getParameter("nombPlant");
            // Se podría agregar validación adicional según las reglas de negocio
            if (nombPlant == null || nombPlant.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write(new JSONObject().put("error", "El campo nombPlant es obligatorio").toString());
                return;
            }

            Plantilla plantilla = new Plantilla();
            plantilla.setNombPlant(nombPlant);
            // Opcional: establecer actvPlant si se recibe o un valor por defecto
            plantilla.setActvPlant(Boolean.TRUE);

            plantillaDAO.create(plantilla);
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.write(new JSONObject().put("message", "Registro creado exitosamente").toString());
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

        try (PrintWriter out = response.getWriter()) {
            // Parsear el cuerpo de la solicitud (application/x-www-form-urlencoded)
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

            if (!parameters.containsKey("codiPlant")) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write(new JSONObject().put("error", "El campo codiPlant es obligatorio").toString());
                return;
            }
            int codiPlant = Integer.parseInt(parameters.get("codiPlant"));
            String nombPlant = parameters.get("nombPlant");
            String actvPlantStr = parameters.get("actvPlant");
            Boolean actvPlant = actvPlantStr != null ? Boolean.valueOf(actvPlantStr) : null;

            Plantilla plantilla = plantillaDAO.findPlantilla(codiPlant);
            if (plantilla == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write(new JSONObject().put("error", "Registro no encontrado").toString());
                return;
            }

            // Actualizar campos
            if (nombPlant != null) {
                plantilla.setNombPlant(nombPlant);
            }
            if (actvPlant != null) {
                plantilla.setActvPlant(actvPlant);
            }

            plantillaDAO.edit(plantilla);
            out.write(new JSONObject().put("message", "Registro actualizado exitosamente").toString());
        } catch (NumberFormatException nfe) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new JSONObject().put("error", "Formato de ID no válido").toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject()
                    .put("error", "Error al actualizar el registro: " + e.getMessage()).toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        String pathInfo = request.getPathInfo();
        try (PrintWriter out = response.getWriter()) {
            if (pathInfo != null && pathInfo.length() > 1) {
                try {
                    int id = Integer.parseInt(pathInfo.substring(1));
                    plantillaDAO.destroy(id);
                    out.write(new JSONObject().put("message", "Registro eliminado exitosamente").toString());
                } catch (NumberFormatException nfe) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.write(new JSONObject().put("error", "Formato de ID no válido").toString());
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.write(new JSONObject().put("error", "ID de registro no válido").toString());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject()
                    .put("error", "Error al eliminar el registro: " + e.getMessage()).toString());
        }
    }
}
