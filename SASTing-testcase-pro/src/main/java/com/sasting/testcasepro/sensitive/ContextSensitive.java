package com.sasting.testcasepro.sensitive;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Context Sensitive Testcase
 */
public class ContextSensitive extends HttpServlet {
    /**
     * bad testcase
     * XSS
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        int index = 1;
        String comment = getComment(index, req);
        try {
            // context-sensitive,comment is tainted
            resp.getWriter().write(comment);  // XSS
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * good testcase
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        int index = -1;
        String comment = getComment(index, req);
        try {
            // context-sensitive,comment is not tainted
            resp.getWriter().write(comment);  // !XSS
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getComment(int index, HttpServletRequest req) {
        if (index > 0) {
            return req.getParameter("comment");
        }
        else {
            return "SASTing's comment";
        }
    }
}
