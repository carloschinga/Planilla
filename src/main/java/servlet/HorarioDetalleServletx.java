/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.VistaHorarioDetalleDAO;
import dto.Periodo;
import dto.VistaHorarioDetalle;
import java.io.IOException;
import java.text.ParseException;
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

/**
 *
 * @author USER
 */
@WebServlet(name = "HorarioDetalleServlet", urlPatterns = {"/horariodetalleservlet/*", "/horariodetalleservlet//nombres"})
public class HorarioDetalleServletx extends HttpServlet {

    private final VistaHorarioDetalleDAO vistaHorarioDetalleDAO;
    private final EntityManagerFactory emf;

    public HorarioDetalleServletx() {
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
            if ("/nombres".equals(pathInfo)) {
                List<String> nombres = vistaHorarioDetalleDAO.findUniqueNombres();
                JSONArray jsonArray = new JSONArray();
                for (String nombre : nombres) {
                    jsonArray.put(nombre);
                }
                response.getWriter().write(jsonArray.toString());
            } else {
                String nombHoraFilter = request.getParameter("nombHora");
                List<VistaHorarioDetalle> lista;
                if (nombHoraFilter != null && !nombHoraFilter.isEmpty()) {
                    lista = vistaHorarioDetalleDAO.findVistaHorarioDetalleByNombre(nombHoraFilter);
                } else {
                    lista = vistaHorarioDetalleDAO.findVistaHorarioDetalleEntities();
                }

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
                    jsonObject.put("horaIngr", detalle.getHoraIngr() != null ? new SimpleDateFormat("HH:mm:ss").format(detalle.getHoraIngr()) : JSONObject.NULL);
                    jsonObject.put("horaSald", detalle.getHoraSald() != null ? new SimpleDateFormat("HH:mm:ss").format(detalle.getHoraSald()) : JSONObject.NULL);

                    jsonArray.put(jsonObject);
                }
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(jsonArray.toString());
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Error al procesar la solicitud: " + e.getMessage() + "\"}");
        }
    }

    @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Obtener parámetros del formulario
            String codiHora = request.getParameter("codiHora");
            String nombHora = request.getParameter("nombHora");
           //String codiHoraDeta = request.getParameter("codiHoraDeta");
            String codiDia = request.getParameter("codiDia");
            String nombDia = request.getParameter("nombDia");
            String codiTurn = request.getParameter("codiTurn");
            String descTurn = request.getParameter("descTurn");
            String horaIngrStr = request.getParameter("horaIngr");
            String horaSaldStr = request.getParameter("horaSald");

            // Verificar si faltan campos requeridos
            if (isEmpty(codiHora) || isEmpty(nombHora) || isEmpty(codiDia)
                    || isEmpty(nombDia) || isEmpty(codiTurn) || isEmpty(descTurn) || isEmpty(horaIngrStr) || isEmpty(horaSaldStr)) {
                writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Faltan campos requeridos");
                return;
            }

            // Validación de valores numéricos
            if (!isValidNumber(codiHora) || !isValidNumber(codiDia) || !isValidNumber(codiTurn)) {
                writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Formato numérico inválido");
                return;
            }

            // Validar y parsear las horas
            SimpleDateFormat sdfFull = new SimpleDateFormat("HH:mm:ss");
            SimpleDateFormat sdfPartial = new SimpleDateFormat("HH:mm");

            Date horaIngr = parseHora(horaIngrStr, sdfFull, sdfPartial);
            Date horaSald = parseHora(horaSaldStr, sdfFull, sdfPartial);

            // Crear objeto VistaHorarioDetalle
            VistaHorarioDetalle horario = new VistaHorarioDetalle();
            horario.setCodiHora(Integer.parseInt(codiHora));
            horario.setNombHora(nombHora);
           // horario.setCodiHoraDeta(Integer.parseInt(codiHoraDeta));
            horario.setCodiDia(Integer.parseInt(codiDia));
            horario.setNombDia(nombDia);
            horario.setCodiTurn(Integer.parseInt(codiTurn));
            horario.setDescTurn(descTurn);
            horario.setHoraIngr(horaIngr);
            horario.setHoraSald(horaSald);

            // Guardar en la base de datos
            vistaHorarioDetalleDAO.create(horario);

            // Respuesta exitosa
            response.setStatus(HttpServletResponse.SC_CREATED);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("message", "Horario creado exitosamente");
            response.getWriter().write(jsonResponse.toString());

        } catch (NumberFormatException e) {
            writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Formato numérico inválido: " + e.getMessage());
        } catch (ParseException e) {
            writeError(response, HttpServletResponse.SC_BAD_REQUEST, "Formato de hora inválido: " + e.getMessage());
        } catch (Exception e) {
            writeError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al crear el registro: " + e.getMessage());
        }
    }

    // Verificar si un valor es vacío
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    // Verificar si un valor es un número válido
    private boolean isValidNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Parsear la hora en formato HH:mm o HH:mm:ss
    private Date parseHora(String horaStr, SimpleDateFormat sdfFull, SimpleDateFormat sdfPartial) throws ParseException {
        Date hora = null;
        try {
            // Intentamos parsear con el formato HH:mm:ss
            hora = sdfFull.parse(horaStr);
        } catch (ParseException e) {
            // Si falla, intentamos parsear con el formato HH:mm añadiendo :00
            try {
                hora = sdfPartial.parse(horaStr + ":00");
            } catch (ParseException ex) {
                throw new ParseException("Formato de hora inválido: " + horaStr, e.getErrorOffset());
            }
        }
        return hora;
    }

    // Escribir error en la respuesta
    private void writeError(HttpServletResponse response, int statusCode, String errorMessage) throws IOException {
        response.setStatus(statusCode);
        JSONObject errorResponse = new JSONObject();
        errorResponse.put("error", errorMessage);
        response.getWriter().write(errorResponse.toString());
    } 

}
