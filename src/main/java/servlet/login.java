package servlet;

import db.DAOlogin;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("ok");

        resp.setContentType("text/html");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        System.out.println(login + pass);
        DAOlogin log = new DAOlogin();
        String password = log.get_pass_by_login(req.getParameter("login"));
        System.out.println(password);


        if (pass.equals(password)) {
            resp.sendRedirect("cabinet");
            System.out.println("pass");
        } else {
            resp.sendRedirect("login");
            System.out.println("error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }
}