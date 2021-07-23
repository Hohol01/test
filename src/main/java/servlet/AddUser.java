package servlet;

import db.DAOUsers;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/adduser")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("WEB-INF/jsp/admin/adduser.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("add") != null) {


            DAOUsers daOusers = new DAOUsers();
            String surname = req.getParameter("surname");
            String name = req.getParameter("name");
            String patronymic = req.getParameter("patronymic");
            String role = req.getParameter("role");
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            if (surname == null || name == null || patronymic == null || role == null || login == null || password == null) {


                System.out.println(daOusers.checkLogin(login));

                ArrayList<User> users = new ArrayList<>();
                User user = new User();
                user.setName(name);
                user.setPatronymic(patronymic);
                user.setSurname(surname);
                user.setLogin(login);
                user.setRole(role);
                user.setPassword(password);
                users.add(user);

                req.setAttribute("valid", "valid");
                req.setAttribute("user", users);
                req.getRequestDispatcher("WEB-INF/jsp/admin/adduser.jsp").forward(req, resp);

            } else if (daOusers.checkLogin(login)) {
                ArrayList<User> users = new ArrayList<>();
                User user = new User();
                user.setName(name);
                user.setPatronymic(patronymic);
                user.setSurname(surname);
                user.setLogin(login);
                user.setRole(role);
                user.setPassword(password);
                users.add(user);

                req.setAttribute("error", "error");
                req.setAttribute("user", users);
                req.getRequestDispatcher("WEB-INF/jsp/admin/adduser.jsp").forward(req, resp);
            } else {
                daOusers.addUser(surname, name, patronymic, role, login, password);
                resp.sendRedirect("home");
            }
        }
    }
}
