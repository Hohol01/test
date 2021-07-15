package servlet;

import db.DAOUsers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/adduser")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            req.getRequestDispatcher("WEB-INF/jsp/admin/adduser.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("add") != null) {


            String surname = req.getParameter("surname");
            String name = req.getParameter("name");
            String patronymic = req.getParameter("patronymic");
            String role = req.getParameter("role");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            DAOUsers daOusers = new DAOUsers();
            daOusers.addUser(surname, name, patronymic, role, login, password);
            resp.sendRedirect("home");
        }
    }
}
