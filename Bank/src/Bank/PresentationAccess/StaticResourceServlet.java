package Bank.PresentationAccess;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class StaticResourceServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        if (path == null || path.equals("/")) {
            path = "/index.html";
        }

        ServletContext context = getServletContext();
        String fullPath = "/frontend" + path;
        RequestDispatcher dispatcher = context.getRequestDispatcher(fullPath);
        dispatcher.forward(request, response);
    }
}
