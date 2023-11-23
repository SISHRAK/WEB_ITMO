import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import aaaa.TableRow;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@WebServlet(urlPatterns = "/c")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        HttpSession session = request.getSession();

        response.setContentType("text/html;charset=UTF-8");

        List tableRows = (List) session.getAttribute("tableRows");
        if (tableRows == null) {
            tableRows = new ArrayList<TableRow>();
            session.setAttribute("tableRows", tableRows);
        }
        int x = Integer.parseInt(request.getParameter("x"));
        float y = Float.parseFloat(request.getParameter("y"));
        int r = Integer.parseInt(request.getParameter("r"));
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("kk:mm:ss"));
        if (checkValues(x, y, r)) {
            TableRow point = new TableRow();
            point.setR(r);
            point.setY(y);
            point.setX(x);
            point.setInside(checkArea(x, y, r));
            point.setLocalTime(currentTime);
            tableRows.add(point);
            request.setAttribute("lastPoint", point);
            response.sendRedirect(request.getContextPath() + "/result.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
        } catch (NumberFormatException e){
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }



    private boolean checkArea(int x, float y, int r) {
        if (x == 0) {
            return (y <= r && y >= -r / 2);
        } else if (x > 0 && y <= 0) {
            return (x * x + y * y <= r * r / 4); //окружность
        } else return (y <= x + r && y >= 0) //треугольник
                || (x <=0 && x >= -r && y >= -r / 2 && y <= 0); //прямоугольник
    }

    private boolean checkValues(int x, float y, int r) {
        return Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3).contains(x) &&
                (y < 3 && y > -5) &&
                (r > 2 && r < 5);
    }
}