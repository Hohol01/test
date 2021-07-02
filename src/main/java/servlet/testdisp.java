package servlet;

import db.DAOtest;
import entity.test;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

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
        }else {
            dispalltest(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ArrayList<test> tests;
        tests = t.getListOfTests();
        req.setAttribute("list", tests);
        tests = null;

        if (req.getParameter("search1") != null) {
            tests = t.getListByName(req.getParameter("search"));
            req.removeAttribute("tests");
            req.setAttribute("tests", tests);
            req.getRequestDispatcher("test.jsp").forward(req, resp);


        } else if (req.getParameter("select") != null) {
            String subject = req.getParameter("subject");
            if (subject.equals("все"))
                tests = t.getListOfTests();
            else
                tests = t.getListOfTestsWithSubdgect(subject);
            req.removeAttribute("tests");
            req.setAttribute("tests", tests);
            req.getRequestDispatcher("test.jsp").forward(req, resp);

        } else if (req.getParameter("sort") != null) {
            if (req.getParameter("sort").equals("название теста")) {
                System.out.println("1");
                tests = t.sortByValue("name");
            } else if (req.getParameter("sort").equals("сложность")) {
                tests = t.sortByValue("hardnest");
                System.out.println("2");
            } else {
                tests = t.sortByValue("time");
                System.out.println("3");
            }
            req.removeAttribute("tests");
            req.setAttribute("tests", tests);
            req.getRequestDispatcher("test.jsp").forward(req, resp);
        }
    }
    private void dispalltest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<test> tests = t.getListOfTests();
        req.removeAttribute("tests");
        req.setAttribute("tests", tests);
        req.setAttribute("list", tests);
        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }
}
