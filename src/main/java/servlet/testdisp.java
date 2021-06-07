package servlet;

import db.DAOtest;
import entity.test;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/test")
public class testdisp extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("test");
        DAOtest t = new DAOtest();
        ArrayList<test> tests = t.getlistoftests();
        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else {
            req.setAttribute("tests", tests);
            req.getRequestDispatcher("test.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int k = 0;
        while (true) {
            if (req.getParameter(String.valueOf(k)) != null) {
                passtest passtest = new passtest();
                passtest.setId(k);

                req.getSession().setAttribute("testid",k);
                System.out.println(k+"id");

                resp.sendRedirect("passingtest");
                break;
            }
            k++;
        }
    }
}
