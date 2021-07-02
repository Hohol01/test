package servlet;

import db.DAOusers;
import entity.user;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/users")
public class users extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int count = 1;

        if (req.getParameter("back") != null) {
            resp.sendRedirect("home");
        } else
            while (true) {
                if (req.getParameter(String.valueOf(count)) != null) {
                    DAOusers daOusers = new DAOusers();
                    daOusers.deleteUser(count);
                    System.out.println(count);
                    resp.sendRedirect("users");
                    break;
                }
                count++;
            }

        System.out.println(req.getInputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id") && !ses.getAttribute("role").equals("teacher")) {
            resp.sendRedirect("login");
        } else if(ses.getAttribute("role").equals("teacher")){
            DAOusers daOusers = new DAOusers();
            ArrayList<user> userlist = daOusers.usersList();
            req.setAttribute("user", userlist);
            req.getRequestDispatcher("users.jsp").forward(req, resp);
        }else
            resp.sendRedirect("home");

    }
}
