package servlet;

import db.DAOAnswer;
import db.DAOQuestion;
import db.DAOTest;
import entity.Answer;
import entity.Question;
import entity.Test;
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
public class EditTest extends HttpServlet {
    DAOTest daOtest = new DAOTest();
    DAOQuestion daOquestion = new DAOQuestion();
    DAOAnswer daOanswer = new DAOAnswer();
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
        String language = String.valueOf(req.getSession().getAttribute("language"));
        System.out.println(language);
        ArrayList<Test> testArrayList = daOtest.getTestsById(Integer.parseInt(req.getParameter("idtest")), language);
        req.setAttribute("test", testArrayList);
        req.setAttribute("lang", daOtest.getSubjectAdd(language));
        req.setAttribute("displaying", "Test");
        req.getRequestDispatcher("WEB-INF/jsp/admin/edittest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if (!ses.getAttribute("role").equals("teacher"))
            resp.sendRedirect("home");

        req.removeAttribute("next");
        if (daOquestion.checkNext(numberofquestion + 1))
            req.setAttribute("next", "next");

        if (req.getParameter("next") != null) {
            String nane = req.getParameter("name");
            int subdgect = Integer.parseInt(req.getParameter("subdgect"));
            int hardnest = Integer.parseInt(req.getParameter("hardnest"));
            int time = Integer.parseInt(req.getParameter("time"));
            daOtest.update(nane, subdgect, hardnest, time, idtest);
            numberofquestion = daOquestion.getQuantityOfTest(idtest);

            ArrayList<Answer> ans = daOanswer.getAnwerById(daOquestion.getIdTestById(idtest));

            req.setAttribute("ans", ans);

            List<Question> ques = daOquestion.getQustions(idtest, number);
            req.setAttribute("ques", ques);
            req.getRequestDispatcher("WEB-INF/jsp/admin/editqustion.jsp").forward(req, resp);

        } else if (req.getParameter("edit") != null) {
            updatequs(req);
            number++;
            ArrayList<Answer> ans = daOanswer.getAnwerById(daOquestion.getIdByNumberAndTestId(number, idtest));
            req.setAttribute("ans", ans);
            List<Question> ques = daOquestion.getQustions(idtest, number);
            req.setAttribute("ques", ques);
            req.getRequestDispatcher("WEB-INF/jsp/admin/editqustion.jsp").forward(req, resp);
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
