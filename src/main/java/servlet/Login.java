package servlet;

import db.DAOUsers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final Logger log = Logger.getLogger(Login.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("ok");

        req.setCharacterEncoding("UTF-8");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        DAOUsers daOusers = new DAOUsers();
        String password = daOusers.getPassByLogin(req.getParameter("login"));
        log.debug("login = " + login + " pass = " + pass);
        HttpSession ses = req.getSession();
        ses.removeAttribute("block");

        if (pass.equals(password)) {
            int userid = daOusers.getIdByLogin(login);
            ses.removeAttribute("block");

            ses.setAttribute("id", ses);

            ses.setAttribute("userid", userid);
            ses.setAttribute("role", daOusers.getRoleById(userid));
            System.out.println(daOusers.getRoleById(userid));
            resp.sendRedirect("home");
            System.out.println("pass");

        } else {
            req.setAttribute("error", "error");
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
            System.out.println("error");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DAOUsers daoUsers = new DAOUsers();
        if (req.getSession().getAttribute("userid") != null){
        if (daoUsers.getBlock(Integer.parseInt(String.valueOf(req.getSession().getAttribute("userid"))))) {
            HttpSession ses = req.getSession();
            ses.removeAttribute("id");
            ses.removeAttribute("role");
            ses.removeAttribute("userid");
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
        } else
            resp.sendRedirect("home");
        } else
            req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);


    }
}