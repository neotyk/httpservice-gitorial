package pl.kungfoo.grizzly.osgi.httpservice.gitorial.impl;

import org.osgi.service.http.*;

public class RegisterServletComponent {

    public void setHttp(HttpService http) {
        System.out.println("Got Http Service: " + http);
    }
}

