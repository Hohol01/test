package servlet;

import db.DAOusers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home")
public class homepage extends HttpServlet {

    DAOusers daOusers = new DAOusers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if (daOusers.getBlock((Integer) ses.getAttribute("userid"))) {
            ses.setAttribute("block", "block");
            resp.sendRedirect("login");
        } else
            req.getRequestDispatcher("homepage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //обработка кнопок
        if (req.getParameter("test") != null) {
            resp.sendRedirect("test");
            System.out.println("Test");
        } else if (req.getParameter("add") != null) {
            System.out.println("add");
            resp.sendRedirect("addtest");
        } else if (req.getParameter("results") != null) {
            resp.sendRedirect("results");
        } else if (req.getParameter("out") != null) {
            HttpSession ses = req.getSession();
            ses.setAttribute("id", null);
            resp.sendRedirect("login");
        } else if (req.getParameter("addusers") != null) {
            resp.sendRedirect("adduser");
        } else if (req.getParameter("users") != null) {
            resp.sendRedirect("users");
        }
    }
}
