package pl.kungfoo.grizzly.osgi.httpservice.gitorial.impl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.osgi.service.http.*;

public class RegisterServletComponent {

    public void setHttp(HttpService http) throws ServletException, NamespaceException {
        http.registerServlet("/hello", new RegisterServlet(), null, null);
        http.registerResources("/", "", null);
    }
}

class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest rq, HttpServletResponse rsp) throws IOException {
        PrintWriter pw = rsp.getWriter();
        pw.println("Hello World");
        rsp.setContentType("text/plain");
    }
}

