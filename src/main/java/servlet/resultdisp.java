package servlet;

import db.DAOresult;
import entity.results;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/results")
public class resultdisp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DAOresult daOresult = new DAOresult();

        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if(ses.getAttribute("role").equals("teacher")) {
            int testid = Integer.parseInt(req.getParameter("idtest"));
            List<results> res = daOresult.getresultsfortracher(testid);
            req.setAttribute("res", res);

           req.getRequestDispatcher("resultforteach.jsp").forward(req,resp);
        } else{

            int userid = (int) req.getSession().getAttribute("userid");
            System.out.println("userid = " + userid);
            List<results> res = daOresult.getresultsbyuserid(userid);
            System.out.println(res);
            req.setAttribute("res", res);
            req.getRequestDispatcher("results.jsp").forward(req,resp);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("back") != null){
            resp.sendRedirect("home");
        }
    }
}
