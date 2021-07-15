package servlet;

import db.DAOTest;
import entity.Test;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/test")
public class TestDisp extends HttpServlet {
    DAOTest t = new DAOTest();
    int k = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Test");

        System.out.println(String.valueOf(req.getSession().getAttribute("language")));
        String language = String.valueOf(req.getSession().getAttribute("language"));
        ArrayList<Test> tests = t.getListOfTests(language);
        req.removeAttribute("tests");


        req.getSession().setAttribute("last", "getListOfTests");
        req.setAttribute("tests", tests);

        req.setAttribute("list", t.getSubject(language));
        req.getRequestDispatcher("WEB-INF/jsp/test.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        ArrayList<Test> tests = null;
        HttpSession ses = req.getSession();
        String language = req.getParameter("language");
        System.out.println(language);
        if (req.getParameter("language") != null) {

            req.setAttribute("list", t.getSubject(language));
            String last = String.valueOf(ses.getAttribute("last"));
            System.out.println(last);
            if (last.equals("getListOfTests")) {
                System.out.println("frdfghj");
                tests = t.getListOfTests(language);
            } else if (last.equals("getListOfTestsWithSubdgect")) {
                tests = t.getListOfTestsWithSubdgect((String) ses.getAttribute("getListOfTestsWithSubdgect"), language);
            } else if (last.equals("getListByName")) {
                tests = t.getListByName((String) ses.getAttribute("search"), language);
            } else if (last.equals("sortByValue")) {
                String sort = (String) ses.getAttribute("sort");
                if (sort.equals("name"))
                    tests = t.sortByValue("name", language);
                else if (sort.equals("hardnest"))
                    tests = t.sortByValue("hardnest", language);
                else
                    tests = t.sortByValue("time", language);


            }
        } else {

            language = String.valueOf(ses.getAttribute("language"));
            req.setAttribute("list", t.getSubject(language));
            if (req.getParameter("search1") != null) {
                req.getSession().setAttribute("last", "getListByName");
                req.getSession().setAttribute("search", req.getParameter("search"));
                tests = t.getListByName(req.getParameter("search"), language);

            } else if (req.getParameter("select") != null) {
                String subject = req.getParameter("subject");
                if (subject.equals("все") || subject.equals("all")) {
                    req.getSession().setAttribute("last", "getListOfTests");
                    tests = t.getListOfTests(language);
                } else {
                    req.getSession().setAttribute("last", "getListOfTestsWithSubdgect");
                    req.getSession().setAttribute("getListOfTestsWithSubdgect", subject);
                    tests = t.getListOfTestsWithSubdgect(subject, language);
                }

            } else if (req.getParameter("sort") != null) {
                req.getSession().setAttribute("last", "sortByValue");
                if (req.getParameter("sort").equals("название теста") || req.getParameter("sort").equals("test name")) {
                    System.out.println("1");
                    req.getSession().setAttribute("sort", "name");
                    tests = t.sortByValue("name", language);
                } else if (req.getParameter("sort").equals("сложность") || req.getParameter("sort").equals("hardness")) {
                    req.getSession().setAttribute("sort", "hardnest");
                    tests = t.sortByValue("hardnest", language);
                    System.out.println("2");
                } else {
                    req.getSession().setAttribute("sort", "time");
                    tests = t.sortByValue("time", language);
                    System.out.println("3");
                }

            }
        }
        System.out.println(tests);
        req.setAttribute("tests", tests);

        req.getRequestDispatcher("WEB-INF/jsp/test.jsp").forward(req, resp);
    }


}