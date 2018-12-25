package restaurant.controller;

import restaurant.data.HelperDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/sqlGateway")
public class SqlGatewayServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var sqlStatement = request.getParameter("sqlStatement").trim();
        var sqlResult = "";

        try (var con = HelperDB.getConnection();
             var stat = con.createStatement()) {

            if (sqlStatement.length() >= 6) {
                String sqlType = sqlStatement.substring(0, 6);
                if (sqlType.equalsIgnoreCase("select")) {
                    try (var resSet = stat.executeQuery(sqlStatement)) {
                        sqlResult = SQLUtil.getHtmlTable(resSet);
                    }
                } else {
                    int i = stat.executeUpdate(sqlStatement);
                    if (i == 0) {
                        sqlResult = "<p>The statement executed successfully.</p>";
                    } else {
                        sqlResult = String.format("<p>The statement executed successfully.<br>%s row(s) affected.</p>", i);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("doPost sql: " + ex);
        }

        HttpSession session = request.getSession();
        session.setAttribute("sqlResult", sqlResult);
        session.setAttribute("sqlStatement", sqlStatement);

        String url = "/admin/sql/index.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}