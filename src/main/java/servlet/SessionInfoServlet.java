import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

@WebServlet(name = "SessionInfoServlet", urlPatterns = {"/sessioninfoservlet"})
public class SessionInfoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        if (session != null) {
            jsonObject.put("codiUsua", getString(session, "codiUsua"));
            jsonObject.put("logiUsua", getString(session, "logiUsua"));
            jsonObject.put("nombUsua", getString(session, "nombUsua"));
            jsonObject.put("codiRol", getString(session, "codiRol"));
            jsonObject.put("nombRol", getString(session, "nombRol"));
            jsonObject.put("admiRol", getBoolean(session, "admiRol"));
            jsonObject.put("codiEmpr", getString(session, "codiEmpr"));
            jsonObject.put("nrucEmpr", getString(session, "nrucEmpr"));
            jsonObject.put("nombEmpr", getString(session, "nombEmpr"));
        } else {
            jsonObject.put("error", "No hay sesión activa");
        }

        out.print(jsonObject.toString());
        out.flush();
    }

    private String getString(HttpSession session, String attribute) {
        Object value = session.getAttribute(attribute);
        return value != null ? value.toString() : "";
    }

    private boolean getBoolean(HttpSession session, String attribute) {
        Object value = session.getAttribute(attribute);
        return value != null && Boolean.parseBoolean(value.toString());
    }
}
