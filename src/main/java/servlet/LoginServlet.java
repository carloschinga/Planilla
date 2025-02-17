/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.EmpresaDAO;
import dao.VistaUsuarioRolDAO;
import dto.Empresa;
import dto.VistaUsuarioRol;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author USER
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/loginservlet"})
public class LoginServlet extends HttpServlet {

    private final VistaUsuarioRolDAO vistaUsuarioRolDAO;
    private final EmpresaDAO empresaDAO;
    private final EntityManagerFactory emf;

    public LoginServlet() {
        this.emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
        this.vistaUsuarioRolDAO = new VistaUsuarioRolDAO(emf);
        this.empresaDAO = new EmpresaDAO(emf);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String logiUsua = request.getParameter("logiUsua");
        String passUsua = request.getParameter("passUsua");

        VistaUsuarioRol usuario = vistaUsuarioRolDAO.validarUsuario(logiUsua, passUsua);

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        if (usuario != null) {
            // Cargar la empresa por defecto
            List<Empresa> listaEmpresa = empresaDAO.listaEmpresaActivas();

            if (listaEmpresa != null && !listaEmpresa.isEmpty()) {
                Empresa empresa = listaEmpresa.get(0);

                HttpSession session = request.getSession();
                session.setAttribute("codiUsua", usuario.getCodiUsua()); // Código real
                session.setAttribute("logiUsua", usuario.getLogiUsua());
                session.setAttribute("nombUsua", usuario.getNombUsua());
                session.setAttribute("codiRol", usuario.getCodiRol()); // Código de rol real
                session.setAttribute("nombRol", usuario.getNombRol()); // Nombre de rol real
                session.setAttribute("admiRol", usuario.getAdmiRol()); // ¿Es administrador?
                session.setAttribute("codiEmpr", empresa.getCodiEmpr());
                session.setAttribute("nrucEmpr", empresa.getNrucEmpr());
                session.setAttribute("nombEmpr", empresa.getNombEmpr());

                out.print("success");
            } else {
                out.print("No hay empresas activas");
            }
        } else {
            out.print("error");
        }
    }
}
