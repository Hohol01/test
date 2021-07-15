package servlet;

import db.DAOUsers;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/users")
public class Users extends HttpServlet {
    Boolean flag = false;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if (!ses.getAttribute("role").equals("teacher")) {
            resp.sendRedirect("home");
        } else
            flag=true;

        int count = 1;

        if (req.getParameter("back") != null) {
            resp.sendRedirect("home");
        } else if (flag)
            while (true) {
                if (req.getParameter(String.valueOf(count)) != null) {
                    DAOUsers daOusers = new DAOUsers();
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
            DAOUsers daOusers = new DAOUsers();
            ArrayList<User> userlist = daOusers.usersList();
            req.setAttribute("user", userlist);
            req.getRequestDispatcher("WEB-INF/jsp/admin/users.jsp").forward(req, resp);


    }
}
