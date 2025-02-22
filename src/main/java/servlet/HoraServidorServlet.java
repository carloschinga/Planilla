/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "HoraServidorServlet", urlPatterns = {"/horaServidor"})
public class HoraServidorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        // Formatear la fecha/hora del servidor (por ejemplo, HH:mm:ss)
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String horaActual = sdf.format(new Date());

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("hora", horaActual);

        try (PrintWriter out = response.getWriter()) {
            out.print(jsonResponse.toString());
        }
    }
}
