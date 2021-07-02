package servlet;

import db.DAOusers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebServlet("/login")
public class login extends HttpServlet {
    private static final Logger log = Logger.getLogger(login.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("ok");

        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        System.out.println(login + pass);
        DAOusers daOusers = new DAOusers();
        String password = daOusers.getPassByLogin(req.getParameter("login"));
        log.debug("login=" + login + " pass=" + pass);
        System.out.println(pass);
        HttpSession ses = req.getSession();

        if (pass.equals(password)) {
            int userid = daOusers.getIdByLogin(login);
            ses.removeAttribute("block");
            if (daOusers.getBlock(userid)) {
                ses.setAttribute("block", "block");
                resp.sendRedirect("login");
            } else {

                ses.setAttribute("id", ses);

                ses.setAttribute("userid", userid);
                ses.setAttribute("role", daOusers.getRoleById(userid));
                System.out.println(daOusers.getRoleById(userid));
                resp.sendRedirect("home");
                System.out.println("pass");
            }
        } else {
            req.setAttribute("error","error");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            System.out.println("error");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);


    }
}