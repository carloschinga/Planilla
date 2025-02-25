package servlet;

import dao.FeriadoDAO;
import dto.Feriado;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet(name = "FeriadoServlet", urlPatterns = {"/feriadoservlet/*"})
public class FeriadoServlet extends HttpServlet {

    private final FeriadoDAO feriadoDAO;
    private final EntityManagerFactory emf;

    public FeriadoServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.feriadoDAO = new FeriadoDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Obtener todos los registros de feriados
                List<Feriado> feriadoList = feriadoDAO.findFeriadoEntities();
                JSONArray jsonArray = new JSONArray();
                for (Feriado feriado : feriadoList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiFeri", feriado.getCodiFeri());
                    Date fecha = feriado.getFechFeri();
                    jsonObject.put("fechFeri", (fecha != null) ? sdf.format(fecha) : JSONObject.NULL);
                    jsonArray.put(jsonObject);
                }
                response.getWriter().write(jsonArray.toString());
            } else {
                // Obtener un registro por ID
                int id = Integer.parseInt(pathInfo.substring(1));
                Feriado feriado = feriadoDAO.findFeriado(id);
                if (feriado != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiFeri", feriado.getCodiFeri());
                    Date fecha = feriado.getFechFeri();
                    jsonObject.put("fechFeri", (fecha != null) ? sdf.format(fecha) : JSONObject.NULL);
                    response.getWriter().write(jsonObject.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(new JSONObject().put("error", "Feriado no encontrado").toString());
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al obtener los datos: " + e.getMessage()).toString());
        }
    }

    // Se usa doPut para actualizar un feriado
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Leer los parámetros enviados en la solicitud POST
            // Asumimos que es el código del feriado
            String fechaFeri = request.getParameter("fechFeri");

            if (fechaFeri == null) {
                writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Faltan campos requeridos o están vacíos");
                return;
            }

// Convertir nombFeri a Integer (código del feriado)
            
            try {
                
            } catch (NumberFormatException e) {
                writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Formato de código de feriado inválido: " + e.getMessage());
                return;
            }

// Convertir la fecha de String a Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechFeriDate = null;
            try {
                fechFeriDate = dateFormat.parse(fechaFeri);
            } catch (Exception e) {
                writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Formato de fecha inválido: " + e.getMessage());
                return;
            }

// Crear un objeto Feriado
            Feriado feriado = new Feriado();
            feriado.setFechFeri(fechFeriDate);

// Crear el nuevo feriado en la base de datos
            feriadoDAO.create(feriado);

// Respuesta exitosa
            response.setStatus(HttpServletResponse.SC_CREATED);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("message", "Feriado creado exitosamente");
            jsonResponse.put("fechFeri", feriado.getFechFeri());  // Enviar el código del nuevo feriado
            response.getWriter().write(jsonResponse.toString());
        } catch (Exception e) {
            // Manejo de errores generales
            writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud: " + e.getMessage());
        }
    }

// Método para escribir errores en la respuesta
    private void writeError(HttpServletResponse response, int statusCode, String errorMessage) throws IOException {
        response.setStatus(statusCode);
        JSONObject jsonError = new JSONObject();
        jsonError.put("error", errorMessage);
        response.getWriter().write(jsonError.toString());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Leer datos del cuerpo de la petición en formato URL encoded (ej. codiFeri=1&fechFeri=2025-12-25)
            String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            String[] pairs = body.split("&");
            String codiFeriStr = null;
            String fechFeriStr = null;
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = java.net.URLDecoder.decode(keyValue[1], "UTF-8");
                    if ("codiFeri".equals(key)) {
                        codiFeriStr = value;
                    } else if ("fechFeri".equals(key)) {
                        fechFeriStr = value;
                    }
                }
            }
            if (codiFeriStr == null || fechFeriStr == null) {
                throw new Exception("Parámetros insuficientes");
            }
            int codiFeri = Integer.parseInt(codiFeriStr);
            Date fechFeri = sdf.parse(fechFeriStr);

            // Actualizar el objeto Feriado
            Feriado feriado = new Feriado(codiFeri);
            feriado.setFechFeri(fechFeri);

            // Actualizar el registro en la base de datos
            feriadoDAO.edit(feriado);
            response.getWriter().write(new JSONObject().put("message", "Registro actualizado exitosamente").toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al actualizar el registro: " + e.getMessage()).toString());
        }
    }

    // Se usa doDelete para eliminar un feriado
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo != null && pathInfo.length() > 1) {
                int id = Integer.parseInt(pathInfo.substring(1));
                feriadoDAO.destroy(id);
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
