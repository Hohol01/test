package servlet;

import db.DAOusers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/adduser")
public class adduser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if (!ses.getAttribute("role").equals("teacher"))
            resp.sendRedirect("home");
        else
            req.getRequestDispatcher("adduser.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("add") != null) {


            String surname = req.getParameter("surname");
            String name = req.getParameter("name");
            String patronymic = req.getParameter("patronymic");
            String role = req.getParameter("role");
            if (role.equals(""))
                role = "student";
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            DAOusers daOusers = new DAOusers();
            daOusers.adduser(surname, name, patronymic, role, login, password);
            resp.sendRedirect("home");
        }
    }
}
