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

@WebServlet("/edituser")
public class EditUser extends HttpServlet {
    DAOUsers daOusers = new DAOUsers();
    int iduser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        iduser = Integer.parseInt(req.getParameter("userid"));
        System.out.println(iduser);
        ArrayList<User> user = daOusers.getUserById(iduser);
        req.setAttribute("user", user);
        req.getRequestDispatcher("WEB-INF/jsp/admin/edituser.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if (!ses.getAttribute("role").equals("teacher"))
            resp.sendRedirect("home");

        boolean block =false;
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String patronymic = req.getParameter("patronymic");
        String role = req.getParameter("role");
        if(role.equals(""))
            role ="student";
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (req.getParameter("block")!=null)
            block=true;
        System.out.println(block);
        daOusers.editUser(surname, name, patronymic, role, login, password, block, iduser);
        if (req.getParameter("add") != null)
            resp.sendRedirect("users");
    }
}