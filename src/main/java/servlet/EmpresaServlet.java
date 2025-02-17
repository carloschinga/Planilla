/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.EmpresaDAO;
import dto.Empresa;
import java.io.IOException;
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
@WebServlet(name = "EmpresaServlet", urlPatterns = {"/empresas/*"})
public class EmpresaServlet extends HttpServlet {

    private final EmpresaDAO empresaDAO;
    private final EntityManagerFactory emf;

    public EmpresaServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_EmpresaApp_war_1.0-SNAPSHOTPU");
        this.empresaDAO = new EmpresaDAO(emf);
    }
/*
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Obtener todos los registros activos de empresas
                List<Empresa> empresaList = empresaDAO.findEmpresaEntities();
                JSONArray jsonArray = new JSONArray();
                for (Empresa empresa : empresaList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", empresa.getCodiEmpr());
                    jsonObject.put("nombre", empresa.getNombEmpr());
                    jsonObject.put("ruc", empresa.getNrucEmpr());
                    jsonArray.put(jsonObject);
                }
                response.getWriter().write(jsonArray.toString());
            } else {
                // Obtener un registro de empresa por ID
                int id = Integer.parseInt(pathInfo.substring(1));
                Empresa empresa = empresaDAO.findEmpresa(id);
                if (empresa != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", empresa.getCodiEmpr());
                    jsonObject.put("nombre", empresa.getNombEmpr());
                    jsonObject.put("ruc", empresa.getNrucEmpr());
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
        String pathInfo = request.getPathInfo();

        try {
            // Obtener los parámetros del formulario
            String nombre = request.getParameter("nombre");
            String ruc = request.getParameter("ruc");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String activo = request.getParameter("activo");

            // Crear el objeto Empresa y asignar valores
            Empresa empresa = new Empresa();
            empresa.setNombre(nombre);
            empresa.setRuc(ruc);
            empresa.setDireccion(direccion);
            empresa.setTelefono(telefono);
            empresa.setActivo(Boolean.parseBoolean(activo));

            // Crear registro en la base de datos
            empresaDAO.create(empresa);

            // Responder con un mensaje de éxito
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(new JSONObject().put("message", "Empresa creada exitosamente").toString());
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

            int id = Integer.parseInt(parameters.get("id"));
            String nombre = parameters.get("nombre");
            String ruc = parameters.get("ruc");
            String direccion = parameters.get("direccion");
            String telefono = parameters.get("telefono");
            boolean activo = Boolean.parseBoolean(parameters.get("activo"));

            // Update Empresa object
            Empresa empresa = empresaDAO.findEmpresa(id);
            if (empresa != null) {
                empresa.setNombre(nombre);
                empresa.setRuc(ruc);
                empresa.setDireccion(direccion);
                empresa.setTelefono(telefono);
                empresa.setActivo(activo);

                // Actualizar registro
                empresaDAO.edit(empresa);
                response.getWriter().write(new JSONObject().put("message", "Empresa actualizada exitosamente").toString());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write(new JSONObject().put("error", "Empresa no encontrada").toString());
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
                // Eliminar registro de empresa por ID
                int id = Integer.parseInt(pathInfo.substring(1));
                empresaDAO.destroy(id);
                response.getWriter().write(new JSONObject().put("message", "Empresa eliminada exitosamente").toString());
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new JSONObject().put("error", "Debe proporcionar un ID válido").toString());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al eliminar el registro: " + e.getMessage()).toString());
        }
    }
*/
}
