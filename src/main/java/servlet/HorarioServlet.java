/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.HorarioDAO;
import dto.Horario;
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
 * @author san21
 */
@WebServlet(name = "HorarioServlet", urlPatterns = {"/horarioservlet"})
public class HorarioServlet extends HttpServlet {

    private final HorarioDAO horarioDAO;
    private final EntityManagerFactory emf;

    public HorarioServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.horarioDAO = new HorarioDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Obtener todos los registros de Horario
                List<Horario> horarioList = horarioDAO.findHorarioEntities();
                JSONArray jsonArray = new JSONArray();
                for (Horario horario : horarioList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiHora", horario.getCodiHora());
                    jsonObject.put("nombHora", horario.getNombHora());
                    jsonArray.put(jsonObject);
                }
                response.getWriter().write(jsonArray.toString());
            } else {
                // Obtener un registro de Horario por ID
                int id = Integer.parseInt(pathInfo.substring(1));
                Horario horario = horarioDAO.findHorario(id);
                if (horario != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiHora", horario.getCodiHora());
                    jsonObject.put("nombHora", horario.getNombHora());
                    response.getWriter().write(jsonObject.toString());
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().write(new JSONObject().put("error", "Horario no encontrado").toString());
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
            Map<String, String> parameters = new HashMap<>();
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String[] pairs = body.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8.name());
                String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.name());
                parameters.put(key, value);
            }

            String nombHora = parameters.get("nombHora");

            Horario horario = new Horario();
            horario.setNombHora(nombHora);

            horarioDAO.create(horario);
            response.getWriter().write(new JSONObject().put("message", "Horario creado exitosamente").toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al crear el Horario: " + e.getMessage()).toString());
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

            int codiHora = Integer.parseInt(parameters.get("codiHora"));
            String nombHora = parameters.get("nombHora");

            Horario horario = new Horario();
            horario.setCodiHora(codiHora);
            horario.setNombHora(nombHora);

            horarioDAO.edit(horario);
            response.getWriter().write(new JSONObject().put("message", "Horario actualizado exitosamente").toString());
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error al actualizar el Horario: " + e.getMessage()).toString());
        }
    }

}
