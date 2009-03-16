package pl.kungfoo.grizzly.osgi.httpservice.gitorial.impl;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.service.http.HttpContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

public class RegisterServletComponent {

    public void setHttp(HttpService http) throws ServletException, NamespaceException {
        http.registerServlet("/hello", new RegisterServlet(), null, null);
        http.registerResources("/", "", null);
        http.registerResources("/auth", "", new AuthHttpContext(http.createDefaultHttpContext()));
    }
}

class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest rq, HttpServletResponse rsp) throws IOException {
        PrintWriter pw = rsp.getWriter();
        pw.println("Hello World");
        rsp.setContentType("text/plain");
    }
}

class AuthHttpContext implements HttpContext {
    private HttpContext delegate;

    public AuthHttpContext(HttpContext delegate) {
        this.delegate = delegate;
    }

    public boolean handleSecurity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String auth = request.getHeader("Authorization");
        if (auth != null) {
            return "YXV0aDpzZWNyZXQ=".equals(auth.substring(6)); // Base64 encoded "auth:secret"
        } else {
            response.setHeader("WWW-Authenticate", "BASIC realm=\"Testing Grizzly OSGi Http Service\"");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    public URL getResource(String s) {
        return delegate.getResource(s);
    }

    public String getMimeType(String s) {
        return delegate.getMimeType(s);
    }
}