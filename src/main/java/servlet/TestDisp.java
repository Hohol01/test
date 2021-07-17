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
        ArrayList<Test> tests = null;
        HttpSession ses = req.getSession();
        String language = String.valueOf(req.getSession().getAttribute("language"));
        if (req.getParameter("page") == null) {
            int cout = 0;
            cout = t.getCountOfTests();
            if(cout/3 !=0)
                cout = (cout / 3) +1;
            ses.setAttribute("count",cout);
            tests = t.getListOfTests(language, 0);
            req.removeAttribute("tests");
            ses.setAttribute("curpage", 1);

            req.getSession().setAttribute("last", "getListOfTests");

            req.setAttribute("list", t.getSubject(language));
        }
        if (req.getParameter("page") != null) {
            req.setAttribute("list", t.getSubject(language));
            int page = Integer.parseInt(req.getParameter("page"));
            ses.setAttribute("curpage", page);
            if (page != 1) {
                page = (page - 1) * 3;
            } else if (page == 1)
                page=0;

            String last = String.valueOf(ses.getAttribute("last"));
            System.out.println(last);
            if (last.equals("getListOfTests")) {
                System.out.println("page = " + page);
                tests = t.getListOfTests(language, page);
            } else if (last.equals("getListOfTestsWithSubdgect")) {
                tests = t.getListOfTestsWithSubdgect((String) ses.getAttribute("getListOfTestsWithSubdgect"), language, page);
            } else if (last.equals("getListByName")) {
                tests = t.getListByName((String) ses.getAttribute("search"), language, page);
            } else if (last.equals("sortByValue")) {
                String sort = (String) ses.getAttribute("sort");
                if (sort.equals("name"))
                    tests = t.sortByValue("name", language, page);
                else if (sort.equals("hardnest"))
                    tests = t.sortByValue("hardnest", language, page);
                else
                    tests = t.sortByValue("time", language, page);


            }
        }
        req.setAttribute("tests", tests);

        req.getRequestDispatcher("WEB-INF/jsp/test.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 0;
        ArrayList<Test> tests = null;
        HttpSession ses = req.getSession();
        String language = req.getParameter("language");
        System.out.println(language);

        if (req.getParameter("language") != null) {
            page = (int) ses.getAttribute("curpage");
            req.setAttribute("list", t.getSubject(language));
            String last = String.valueOf(ses.getAttribute("last"));
            System.out.println(last);
            if (last.equals("getListOfTests")) {
                System.out.println("frdfghj");
                tests = t.getListOfTests(language, page);
                System.out.println("ok");
            } else if (last.equals("getListOfTestsWithSubdgect")) {
                tests = t.getListOfTestsWithSubdgect((String) ses.getAttribute("getListOfTestsWithSubdgect"), language, page);
            } else if (last.equals("getListByName")) {
                tests = t.getListByName((String) ses.getAttribute("search"), language, page);
            } else if (last.equals("sortByValue")) {
                String sort = (String) ses.getAttribute("sort");
                if (sort.equals("name"))
                    tests = t.sortByValue("name", language, page);
                else if (sort.equals("hardnest"))
                    tests = t.sortByValue("hardnest", language, page);
                else
                    tests = t.sortByValue("time", language, page);
            }
        } else {
            ses.setAttribute("curpage", 1);
            language = String.valueOf(ses.getAttribute("language"));
            req.setAttribute("list", t.getSubject(language));
            if (req.getParameter("search1") != null) {
                req.getSession().setAttribute("last", "getListByName");
                req.getSession().setAttribute("search", req.getParameter("search"));
                int cout = 0;
                cout = t.getCountOfTestsWhereName(req.getParameter("search"));
                if(cout/3 !=0)
                    cout = (cout / 3) +1;
                ses.setAttribute("count",cout);
                tests = t.getListByName(req.getParameter("search"), language, 0);

            } else if (req.getParameter("select") != null) {
                int cout = 0;


                String subject = req.getParameter("subject");
                if (subject.equals("все") || subject.equals("all")) {
                    cout = t.getCountOfTests();
                    req.getSession().setAttribute("last", "getListOfTests");
                    tests = t.getListOfTests(language, 1);
                } else {
                    req.getSession().setAttribute("last", "getListOfTestsWithSubdgect");
                    req.getSession().setAttribute("getListOfTestsWithSubdgect", subject);
                    tests = t.getListOfTestsWithSubdgect(subject, language, 0);
                    cout = t.getCountOfTestsWhereSub(subject);
                }

                if(cout/3 !=0)
                    cout = (cout / 3) +1;
                ses.setAttribute("count",cout);


            } else if (req.getParameter("sort") != null) {
                req.getSession().setAttribute("last", "sortByValue");
                if (req.getParameter("sort").equals("название теста") || req.getParameter("sort").equals("test name")) {
                    System.out.println("1");
                    req.getSession().setAttribute("sort", "name");
                    tests = t.sortByValue("name", language, 0);
                } else if (req.getParameter("sort").equals("сложность") || req.getParameter("sort").equals("hardness")) {
                    req.getSession().setAttribute("sort", "hardnest");
                    tests = t.sortByValue("hardnest", language, 0);
                    System.out.println("2");
                } else {
                    req.getSession().setAttribute("sort", "time");
                    tests = t.sortByValue("time", language, 0);
                    System.out.println("3");
                }
                int cout = 0;
                cout = t.getCountOfTests();
                if(cout/3 !=0)
                    cout = (cout / 3) +1;
                ses.setAttribute("count",cout);

            }
        }
        System.out.println(tests);
        req.setAttribute("tests", tests);

        req.getRequestDispatcher("WEB-INF/jsp/test.jsp").forward(req, resp);
    }


}