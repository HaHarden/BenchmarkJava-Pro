package com.sasting.testcasepro.sensitive;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.sql.Statement;

import static com.sasting.testcasepro.conf.DatabaseConfig.getStatement;

/**
 * Flow Sensitive Testcase
 */
public class FlowSensitive extends HttpServlet {

    /**
     * bad testcase
     * SQL-injection
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String username;
        username = "SASTing";
        username = req.getParameter("name"); // source
        String sql = "select * from user where username = " + username;  // flow-sensitive,username is tainted
        Statement statement = getStatement();
        try {
            // sink
            statement.execute(sql);  // SQLI
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * good testcase
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String username;
        username = req.getParameter("name"); // source
        username = "SASTing";
        String sql = "select * from user where username = " + username; // flow-sensitive,username is not tainted
        Statement statement = getStatement();
        try {
            statement.execute(sql);  // !SQLI
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
