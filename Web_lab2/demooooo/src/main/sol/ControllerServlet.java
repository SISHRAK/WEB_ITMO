import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@MultipartConfig
@WebServlet(name = "controllerServlet", urlPatterns = "/result")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String x = request.getParameter("x");
        String y = request.getParameter("y");
        String r = request.getParameter("r");
        if (x != null && y != null && r != null)
            request.getServletContext().getNamedDispatcher("AreaCheckServlet").forward(request, response);
    }

}