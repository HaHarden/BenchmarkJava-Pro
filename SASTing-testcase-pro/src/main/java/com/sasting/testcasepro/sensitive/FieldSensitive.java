package com.sasting.testcasepro.sensitive;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Field Sensitive Testcase of Object
 */
public class FieldSensitive extends HttpServlet {
    /**
     * bad testcase
     * XSS
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        String comment = req.getParameter("comment"); // source
        user.setComment(comment);
        try {
            // field-sensitive,user.getComment() is tainted
            resp.getWriter().write(user.getComment());  // XSS
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * good testcase
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        String comment = req.getParameter("comment"); // source
        user.setComment(comment);
        try {
            // field-sensitive,user.getName() is not tainted
            resp.getWriter().write(user.getName()); // !XSS
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * Field Sensitive Testcase of Container
 */
class FieldSensitiveContainer extends HttpServlet {
    /**
     * bad testcase
     * XSS
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<String> list = new ArrayList<>();
        String[] strs = new String[3];
        Map<String, String> map = new HashMap<>();

        String comment = req.getParameter("comment"); // source

        addElements(list, strs, map, comment);

        try {
            // field-sensitive,list.get(1) is tainted
            resp.getWriter().write(list.get(1));  // XSS
            // field-sensitive,strs[0] is tainted
            resp.getWriter().write(strs[0]);  // XSS
            // field-sensitive,map.get("key3") is tainted
            resp.getWriter().write(map.get("key3"));  // XSS
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * good testcase
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        List<String> list = new ArrayList<>();
        String[] strs = new String[3];
        Map<String, String> map = new HashMap<>();

        String comment = req.getParameter("comment"); // source

        addElements(list, strs, map, comment);

        try {
            // field-sensitive,list.get(2) is not tainted
            resp.getWriter().write(list.get(2));  // !XSS
            // field-sensitive,strs[1] is not tainted
            resp.getWriter().write(strs[1]);  // !XSS
            // field-sensitive,map.get("key") is not tainted
            resp.getWriter().write(map.get("key"));  // !XSS
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addElements(List<String> list, String[] strs, Map<String, String> map, String comment) {
        list.add("safe comment");
        list.add(comment);
        list.add("safe comment2");

        strs[0] = comment;
        strs[1] = "safe comment";
        strs[2] = "safe comment2";

        map.put("key", "safe comment");
        map.put("key2", "safe comment2");
        map.put("key3", comment);
    }
}