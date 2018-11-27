package fr.laerce.webapp;

/*
  Created by laerce on 10/11/2017.
 */

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World !</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello World !</h1>");
        out.println("<p>Vous me sollicitez par un GET </p>");
        out.println("</body>");
        out.println("</html>");
    }
}