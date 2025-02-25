/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dto.Marcacion;
import java.util.Calendar;
import dto.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
@WebServlet(name = "AsistenciaServlet", urlPatterns = {"/asistencia"})
public class AsistenciaServlet extends HttpServlet {

    private final EntityManagerFactory emf;

    public AsistenciaServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonResponse = new JSONObject();

        try {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
            }
            JSONObject jsonRequest = new JSONObject(sb.toString());
            String numeDocu = jsonRequest.getString("numeDocu").trim();  // Aseguramos que no tenga espacios adicionales

            EntityManager em = emf.createEntityManager();
            try {
                // Buscar la persona por número de documento
                List<Persona> personas = em.createNamedQuery("Persona.findByNumeDocu", Persona.class)
                        .setParameter("numeDocu", numeDocu)
                        .getResultList();

                if (!personas.isEmpty()) {
                    Persona persona = personas.get(0);  // Tomamos el primer resultado si hay múltiples

                    // Verificar si ya existe una marcación para hoy
                    Date fechaHoy = new Date();  // Fecha actual

// Usamos Calendar para configurar la fecha a medianoche
                    Calendar calendar = Calendar.getInstance();
                    fechaHoy = calendar.getTime();
                    calendar.setTime(fechaHoy);
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    String sql = "SELECT * FROM Marcacion m WHERE m.codiPers = ? "
                            + "AND m.marcIngr IS NOT NULL "
                            + "AND m.marcSald IS NULL "
                            + "AND CAST(m.fechMarc AS DATE) = CAST(? AS DATE)";
                    Query query = em.createNativeQuery(sql, Marcacion.class);
                    query.setParameter(1, persona.getCodiPers());
                    query.setParameter(2, fechaHoy);  // Pasamos la fecha directamente
                    List<Marcacion> marcaciones = query.getResultList();

                    if (!marcaciones.isEmpty()) {
                        // Si ya existe un registro de entrada para hoy, se registrará la hora de salida
                        Marcacion marcacionExistente = marcaciones.get(0);
                        if (marcacionExistente.getMarcSald() == null) {  // Si la hora de salida es nula
                            marcacionExistente.setMarcSald(new Date()); // Registrar hora de salida
                            em.getTransaction().begin();
                            em.merge(marcacionExistente);
                            em.getTransaction().commit();

                            jsonResponse.put("status", "success");
                            jsonResponse.put("message", "Hora de salida registrada");
                            jsonResponse.put("codiMarc", marcacionExistente.getCodiMarc());
                            jsonResponse.put("nombre", persona.getNombPers());
                            jsonResponse.put("hora", marcacionExistente.getMarcSald().toString());  // Hora de salida
                            jsonResponse.put("marcacion", "salida");  // Enviar "salida"
                        } else {
                            jsonResponse.put("status", "error");
                            jsonResponse.put("message", "Ya se registró la salida para este día");
                        }
                    } else {
                        // Si no existe marcación para hoy, crear un nuevo registro de entrada
                        Marcacion nuevaMarcacion = new Marcacion();
                        nuevaMarcacion.setCodiPers(persona.getCodiPers());
                        nuevaMarcacion.setFechMarc(fechaHoy);
                        nuevaMarcacion.setMarcIngr(new Date());  // Registrar la hora de entrada

                        em.getTransaction().begin();
                        em.persist(nuevaMarcacion);
                        em.getTransaction().commit();

                        jsonResponse.put("status", "success");
                        jsonResponse.put("message", "Entrada registrada");
                        jsonResponse.put("codiMarc", nuevaMarcacion.getCodiMarc());
                        jsonResponse.put("nombre", persona.getNombPers());
                        jsonResponse.put("hora", nuevaMarcacion.getMarcIngr().toString());  // Hora de entrada
                        jsonResponse.put("marcacion", "entrada");  // Enviar "entrada"
                    }
                } else {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Persona no encontrada para el documento: " + numeDocu);
                }
            } catch (Exception e) {
                e.printStackTrace();  // Imprimir detalles de la excepción
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "Error El DNI no figura en la base de datos: " + e.getMessage());
            }

            out.print(jsonResponse.toString());
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            jsonResponse.put("status", "error");
            jsonResponse.put("message", ex.getMessage());
            out.print(jsonResponse.toString());
        } finally {
            out.close();
        }
    }
}
