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
    DAOtest t = new DAOtest();
    int k = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("test");


        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else {
            dispalltest(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        k=0;
        ArrayList<test> tests;
        String search = req.getParameter("search");
        if (req.getParameter("search1") != null) {


        } else if (req.getParameter("select") != null) {
            String subject = req.getParameter("subject");
            if (subject.equals("все"))
                tests = t.getlistoftests();
            else
                tests = t.getlistoftestswithsubdgect(subject);
            req.setAttribute("tests", tests);
            req.getRequestDispatcher("test.jsp").forward(req, resp);
        } else
            while (true) {
                if (req.getParameter(String.valueOf(k)) != null) {

                    req.getSession().setAttribute("testid", k);
                    System.out.println(k + "id");

                    resp.sendRedirect("passingtest");
                    break;
                }
                k++;
            }
    }
    private void dispalltest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<test> tests = t.getlistoftests();
        req.setAttribute("tests", tests);
        req.getSession().setAttribute("list", tests);
        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }
}
