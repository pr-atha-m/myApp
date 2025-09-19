package com.example;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class HelloServlet extends HttpServlet {
    private Properties dfcProps = new Properties();

    @Override
    public void init() throws ServletException {
        try {
            String configPath = getServletContext().getInitParameter("dfc.config");
            InputStream in = getServletContext().getResourceAsStream(configPath);
            if (in != null) {
                dfcProps.load(in);
            }
        } catch (Exception e) {
            throw new ServletException("Failed to load dfc.properties", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        out.println("Hello from HelloServlet!");
        out.println("Loaded dfc.properties:");
        dfcProps.forEach((k, v) -> out.println(k + "=" + v));
    }
}
