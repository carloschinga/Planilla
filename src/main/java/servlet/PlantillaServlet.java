package servlet;

import dao.PlantillaDAO;
import dto.Plantilla;
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
 * Servlet that handles CRUD operations for Plantilla entity.
 *
 */
@WebServlet(name = "PlantillaServlet", urlPatterns = {"/plantillaservlet/*"})
public class PlantillaServlet extends HttpServlet {

    private final PlantillaDAO plantillaDAO;
    private final EntityManagerFactory emf;

    public PlantillaServlet() {
        // Initialize EntityManagerFactory and PlantillaDAO
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.plantillaDAO = new PlantillaDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Get all records
                List<Plantilla> plantillaList = plantillaDAO.findPlantillaEntities();
                JSONArray jsonArray = new JSONArray();
                for (Plantilla plantilla : plantillaList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiPlant", plantilla.getCodiPlant());
                    jsonObject.put("nombPlant", plantilla.getNombPlant());
                    jsonArray.put(jsonObject);
                }
                response.getWriter().write(jsonArray.toString());
            } else {
                // Get record by ID
                int id = Integer.parseInt(pathInfo.substring(1));
                Plantilla plantilla = plantillaDAO.findPlantilla(id);
                if (plantilla != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiPlant", plantilla.getCodiPlant());
                    jsonObject.put("nombPlant", plantilla.getNombPlant());
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
            String nombPlant = request.getParameter("nombPlant");

            // Validate required fields
            if (nombPlant == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new JSONObject()
                        .put("error", "Missing required fields").toString());
                return;
            }

            // Map to Plantilla object
            Plantilla plantilla = new Plantilla();
            plantilla.setNombPlant(nombPlant);

            // Persist the Plantilla object
            plantillaDAO.create(plantilla);

            // Respond with success
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(new JSONObject()
                    .put("message", "Registro creado exitosamente").toString());
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
            int codiPlant = Integer.parseInt(parameters.get("codiPlant"));
            String nombPlant = parameters.get("nombPlant");

            // Update Plantilla object
            Plantilla plantilla = new Plantilla();
            plantilla.setCodiPlant(codiPlant);
            plantilla.setNombPlant(nombPlant);

            // Persist changes
            plantillaDAO.edit(plantilla);

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
                plantillaDAO.destroy(id);
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
