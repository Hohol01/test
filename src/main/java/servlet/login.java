package servlet;

import db.DAOusers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("ok");

        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        System.out.println(login + pass);
        DAOusers daOusers = new DAOusers();
        String password = daOusers.get_pass_by_login(req.getParameter("login"));
        System.out.println(password);


        if (pass.equals(password)) {
            int userid = daOusers.getidbylogin(login);

            if (daOusers.getblock(userid)) {
                req.setAttribute("block", "block");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            } else {
                HttpSession ses = req.getSession();
            ses.setAttribute("id", ses);

            ses.setAttribute("userid", userid);
            ses.setAttribute("role", daOusers.getrolebyid(userid));
            System.out.println(daOusers.getrolebyid(userid));
            resp.sendRedirect("home");
            System.out.println("pass");
        }
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