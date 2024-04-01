package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/travel")
public class TravelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理 GET 请求
        response.setContentType("text/html");
        response.getWriter().println("<html><body><h1>Welcome to sydTravel Website!</h1>");
        response.getWriter().println("<form action='http://www.google.com/search' method='get'>");
        response.getWriter().println("<input type='text' name='q' placeholder='Search Google'>");
        response.getWriter().println("<input type='submit' value='Google Search'>");
        response.getWriter().println("</form></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 处理 POST 请求
        doGet(request, response);
    }
}
