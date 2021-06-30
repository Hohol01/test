package servlet;

import db.DAOanswer;
import db.DAOquestion;
import db.DAOtest;
import entity.answer;
import entity.question;
import entity.test;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/edit")
public class edittest extends HttpServlet {
    DAOtest daOtest = new DAOtest();
    DAOquestion daOquestion = new DAOquestion();
    DAOanswer daOanswer = new DAOanswer();
    int idtest;
    int number;
    int numberofquestion;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        number = 1;
        numberofquestion = 1;
        if (daOquestion.checkNext(numberofquestion + 1))
            req.setAttribute("next", "next");
        idtest = Integer.parseInt(req.getParameter("idtest"));
        System.out.println(req.getParameter("idtest"));
        ArrayList<test> testArrayList = daOtest.gettesttsbyid(Integer.parseInt(req.getParameter("idtest")));
        req.setAttribute("test", testArrayList);
        req.setAttribute("displaying", "test");


        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if (!ses.getAttribute("role").equals("teacher"))
            resp.sendRedirect("home");
        else
            req.getRequestDispatcher("edittest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.removeAttribute("next");
        if (daOquestion.checkNext(numberofquestion + 1))
            req.setAttribute("next", "next");

        if (req.getParameter("next") != null) {
            String nane = req.getParameter("name");
            String subdgect = req.getParameter("subdgect");
            int hardnest = Integer.parseInt(req.getParameter("hardnest"));
            int time = Integer.parseInt(req.getParameter("time"));
            daOtest.update(nane, subdgect, hardnest, time, idtest);
            numberofquestion = daOquestion.getQuantityOfTest(idtest);

            ArrayList<answer> ans = daOanswer.getAnwerById(daOquestion.getIdTestById(idtest));

            req.setAttribute("ans", ans);

            List<question> ques = daOquestion.getQustions(idtest, number);
            req.setAttribute("ques", ques);
            req.getRequestDispatcher("editqustion.jsp").forward(req, resp);

        } else if (req.getParameter("edit") != null) {
            updatequs(req);
            number++;
            ArrayList<answer> ans = daOanswer.getAnwerById(daOquestion.getIdByNumberAndTestId(number, idtest));
            req.setAttribute("ans", ans);
            List<question> ques = daOquestion.getQustions(idtest, number);
            req.setAttribute("ques", ques);
            req.getRequestDispatcher("editqustion.jsp").forward(req, resp);
        } else if (req.getParameter("delete") != null) {
            daOtest.delete(idtest);
            daOquestion.delete(idtest);
            daOanswer.delete(idtest);
            resp.sendRedirect("home");
        } else {
            resp.sendRedirect("home");
        }

    }

    private void updatequs(HttpServletRequest req) {
        String question = req.getParameter("question");
        int idqus = Integer.parseInt(req.getParameter("id"));
        daOquestion.updateQus(question, idqus);

        for (int i = 1; i < 3; i++) {
            boolean corect = false;

            String answers = req.getParameter("ans" + i);
            if (req.getParameter("correct" + i) != null)
                corect = true;

            System.out.println(answers);
            daOanswer.update(answers, corect, i, idqus);
        }
    }
}
