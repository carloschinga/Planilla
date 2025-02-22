/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dto.Marcacion;
import dto.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        // Configurar el response como JSON
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject jsonResponse = new JSONObject();

        try {
            // Leer el parámetro (se espera JSON, por ejemplo: {"numeDocu": "12345678"})
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
            }
            JSONObject jsonRequest = new JSONObject(sb.toString());
            String numeDocu = jsonRequest.getString("numeDocu");

            EntityManager em = emf.createEntityManager();
            try {
                // Buscar la persona por número de documento usando JPQL
                Persona persona = (Persona) em.createNamedQuery("Persona.findByNumeDocu")
                        .setParameter("numeDocu", numeDocu)
                        .getSingleResult();

                if (persona != null) {
                    // Crear la marcación de asistencia
                    Marcacion marcacion = new Marcacion();
                    marcacion.setCodiPers(persona.getCodiPers());
                    marcacion.setFechMarc(new Date());
                    marcacion.setMarcIngr(new Date());
                    // Por ejemplo, marcSald se dejará nulo para la salida; en un escenario real se puede actualizar al cerrar jornada

                    em.getTransaction().begin();
                    em.persist(marcacion);
                    em.getTransaction().commit();

                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Asistencia registrada");
                    jsonResponse.put("codiMarc", marcacion.getCodiMarc());
                }
            } catch (Exception e) {
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "Persona no encontrada");
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
