/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.VistaHorarioDetalleDAO;
import dto.VistaHorarioDetalle;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "HorarioDetalleServlet", urlPatterns = {"/horariodetalleservlet"})
public class HorarioDetalleServlet extends HttpServlet {

   private final VistaHorarioDetalleDAO vistaHorarioDetalleDAO;
    private final EntityManagerFactory emf;

    public HorarioDetalleServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.vistaHorarioDetalleDAO = new VistaHorarioDetalleDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Listar todos los registros de VistaHorarioDetalle
                List<VistaHorarioDetalle> lista = vistaHorarioDetalleDAO.findVistaHorarioDetalleEntities();
                JSONArray jsonArray = new JSONArray();
                for (VistaHorarioDetalle detalle : lista) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiHora", detalle.getCodiHora());
                    jsonObject.put("nombHora", detalle.getNombHora());
                    jsonObject.put("codiHoraDeta", detalle.getCodiHoraDeta());
                    jsonObject.put("codiDia", detalle.getCodiDia());
                    jsonObject.put("nombDia", detalle.getNombDia());
                    jsonObject.put("codiTurn", detalle.getCodiTurn());
                    jsonObject.put("descTurn", detalle.getDescTurn());
                    // Convertir las horas a un formato adecuado (por ejemplo, "HH:mm:ss")
                    jsonObject.put("horaIngr", detalle.getHoraIngr() != null ? new SimpleDateFormat("HH:mm:ss").format(detalle.getHoraIngr()) : JSONObject.NULL);
                    jsonObject.put("horaSald", detalle.getHoraSald() != null ? new SimpleDateFormat("HH:mm:ss").format(detalle.getHoraSald()) : JSONObject.NULL);
                    jsonArray.put(jsonObject);
                }
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(jsonArray.toString());
            } else {
                // Obtener un registro específico por codiHoraDeta
                int id = Integer.parseInt(pathInfo.substring(1));
                VistaHorarioDetalle detalle = vistaHorarioDetalleDAO.findVistaHorarioDetalle(id);
                if (detalle == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write("{\"error\":\"Registro no encontrado\"}");
                    return;
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("codiHora", detalle.getCodiHora());
                jsonObject.put("nombHora", detalle.getNombHora());
                jsonObject.put("codiHoraDeta", detalle.getCodiHoraDeta());
                jsonObject.put("codiDia", detalle.getCodiDia());
                jsonObject.put("nombDia", detalle.getNombDia());
                jsonObject.put("codiTurn", detalle.getCodiTurn());
                jsonObject.put("descTurn", detalle.getDescTurn());
                jsonObject.put("horaIngr", detalle.getHoraIngr() != null ? new SimpleDateFormat("HH:mm:ss").format(detalle.getHoraIngr()) : JSONObject.NULL);
                jsonObject.put("horaSald", detalle.getHoraSald() != null ? new SimpleDateFormat("HH:mm:ss").format(detalle.getHoraSald()) : JSONObject.NULL);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(jsonObject.toString());
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"El identificador debe ser un número entero válido\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al procesar la solicitud: " + e.getMessage() + "\"}");
        }
    }
    
    // Si se requiere, se pueden agregar métodos POST, PUT o DELETE para manejar otras operaciones,
    // aunque para una entidad vista (read-only) generalmente se limita a operaciones GET.


}
