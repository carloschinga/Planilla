/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.PersonaDAO;
import dao.VistaPersonaDetalleDAO;
import dto.Persona;
import dto.VistaPersonaDetalle;
import java.io.BufferedReader;
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
@WebServlet(name = "PersonaServlet", urlPatterns = {"/personaservlet/*"})
public class PersonaServlet extends HttpServlet {

    private final PersonaDAO personaDAO;
    private final VistaPersonaDetalleDAO vistaPersonaDetalleDAO;
    private final EntityManagerFactory emf;

    public PersonaServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.personaDAO = new PersonaDAO(emf);
        this.vistaPersonaDetalleDAO= new VistaPersonaDetalleDAO(emf);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Obtener todos los registros de personas
                List<VistaPersonaDetalle> personaList = vistaPersonaDetalleDAO.findVistaPersonaDetalleEntities();
                JSONArray jsonArray = new JSONArray();
                for (VistaPersonaDetalle persona : personaList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiPers", persona.getCodiPers());
                    jsonObject.put("numeDocu", persona.getNumeDocu());
                    jsonObject.put("nombPers", persona.getNombPers());
                    jsonObject.put("codiAFP", persona.getCodiAFP()); // Relación con AFP
                    jsonObject.put("nombAFP", persona.getNombAFP()); 
                    jsonObject.put("codiPlant", persona.getCodiPlant());
                    jsonObject.put("nombPlant", persona.getNombPlant());
                    jsonObject.put("actiPers", persona.getActiPers());
                    jsonObject.put("suelPers", persona.getSuelPers());
                    jsonObject.put("asigFamiPers", persona.getAsigFamiPers());
                    jsonArray.put(jsonObject);
                }
                response.getWriter().write(jsonArray.toString());
            } else {
                // Obtener un registro de persona por ID
                int id = Integer.parseInt(pathInfo.substring(1));
                Persona persona = personaDAO.findPersona(id);
                if (persona != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("codiPers", persona.getCodiPers());
                    jsonObject.put("numeDocu", persona.getNumeDocu());
                    jsonObject.put("nombPers", persona.getNombPers());
                    jsonObject.put("codiAFP", persona.getCodiAFP());
                    jsonObject.put("codiPlant", persona.getCodiPlant());
                    jsonObject.put("actiPers", persona.getActiPers());
                    jsonObject.put("suelPers", persona.getSuelPers());
                    jsonObject.put("asigFamiPers", persona.getAsigFamiPers());
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
            // Leer parámetros enviados en el cuerpo de la solicitud
            String codiPers = request.getParameter("codiPers");
            String numeDocu = request.getParameter("numeDocu");
            String nombPers = request.getParameter("nombPers");
            String suelPers = request.getParameter("suelPers");
            String codiAFP = request.getParameter("codiAFP");
            String codiPlant = request.getParameter("codiPlant");
            String actiPers = request.getParameter("actiPers");
            String asigFamiPers = request.getParameter("asigFamiPers");

            // Validar datos obligatorios
            if (numeDocu == null || nombPers == null || suelPers == null || codiPlant == null || actiPers == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new JSONObject().put("error", "Datos incompletos").toString());
                return;
            }

            // Crear instancia de Persona
            Persona persona = new Persona();
            persona.setNumeDocu(numeDocu);
            persona.setNombPers(nombPers);
            persona.setSuelPers(new BigDecimal(suelPers)); // Asegúrate de validar que es un número válido
            persona.setCodiAFP(codiAFP != null ? Integer.parseInt(codiAFP) : 0); // Valor opcional
            persona.setCodiPlant(Integer.parseInt(codiPlant));
            persona.setActiPers(Boolean.parseBoolean(actiPers));
            persona.setAsigFamiPers(Integer.parseInt(asigFamiPers));
            // Validar sueldo positivo
            if (persona.getSuelPers().compareTo(BigDecimal.ZERO) <= 0) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(new JSONObject().put("error", "El sueldo debe ser mayor a 0").toString());
                return;
            }

            // Guardar persona
            personaDAO.create(persona);

            // Respuesta de éxito
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(new JSONObject().put("message", "Registro creado exitosamente").toString());

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new JSONObject().put("error", "Formato de número inválido: " + e.getMessage()).toString());
        } catch (Exception e) {
            log("Error al procesar la solicitud POST", e);
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

            int codiPers = Integer.parseInt(parameters.get("codiPers"));
            String numeDocu = String.valueOf(parameters.get("numeDocu"));
            String nombPers = String.valueOf(parameters.get("nombPers"));
            int codiAFP = Integer.parseInt(parameters.get("codiAFP"));
            int codiPlant = Integer.parseInt(parameters.get("codiPlant"));
            boolean actiPers = Boolean.parseBoolean(parameters.get("actiPers"));
            BigDecimal suelPers = new BigDecimal(parameters.get("suelPers"));
            int asigFamiPers = Integer.parseInt(parameters.get("asigFamiPers"));

            // Crear instancia de Persona y mapear datos
            Persona persona = new Persona();
            persona.setCodiPers(codiPers);
            persona.setNumeDocu(numeDocu);
            persona.setNombPers(nombPers);
            persona.setCodiAFP(codiAFP); // Opcional, convertir si es necesario
            persona.setCodiPlant(codiPlant);
            persona.setActiPers(actiPers);
            persona.setSuelPers(suelPers); // Convertir el sueldo a BigDecimal
            persona.setAsigFamiPers(asigFamiPers);
            // Actualizar registro en la base de datos
            personaDAO.edit(persona);

            // Respuesta exitosa
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(new JSONObject().put("message", "Registro actualizado exitosamente").toString());

        } catch (NumberFormatException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new JSONObject().put("error", "Formato de número inválido: " + ex.getMessage()).toString());
        } catch (Exception e) {
            log("Error al procesar la solicitud PUT", e);
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
                personaDAO.destroy(id);
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
