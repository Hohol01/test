package servlet;

import db.*;
import entity.Answer;
import entity.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/passingtest")
public class passtest extends HttpServlet {

    int idtest;
    int time = 0;
    int quantityofquestion = 0;
    int numberofques;
    double marc = 0;
    int userid;
    boolean timeout = false;


    Map<Integer, String> answers;
    DAOquestion daOquestion = new DAOquestion();
    DAOanswer daOanswer = new DAOanswer();
    DAOtest daOtest = new DAOtest();

    Thread timer = new Thread() {
        @Override
        public void run() {
            while (time > 0) {
                time--;

                try {
                    sleep(1000);
                    System.out.println(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (time == 0) {

                    checktest();
                    timeout = true;
                }
            }
        }
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        marc = 0;
        timer.interrupt();
        timeout = false;
        answers = new HashMap<>();
        HttpSession ses = req.getSession();
        idtest = Integer.parseInt(req.getParameter("idtest"));
        time = daOtest.getTimeById(idtest) * 60;
        req.setAttribute("time", time);
        System.out.println(time);
        numberofques = 1;
        quantityofquestion = daOquestion.getQuantityOfTest(idtest);
        menegernextpreev(req);
        req.setAttribute("time", time);
        DAOusers daOusers = new DAOusers();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if (ses.getAttribute("role").equals("teacher")) {
            resp.sendRedirect("home");
        } else if (daOusers.getBlock((Integer) ses.getAttribute("userid"))) {
            ses.setAttribute("block", "block");
            resp.sendRedirect("login");
        } else {


            List<Question> ques = daOquestion.getQustions(idtest, 1);
            ArrayList<Answer> ans = daOanswer.getAnwerById(daOquestion.getIdByNumberAndTestId(1, idtest));
            req.setAttribute("ques", ques);
            req.setAttribute("ans", ans);
            req.getRequestDispatcher("passtest.jsp").forward(req, resp);
            timer.start();
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        marc = 0;
        Boolean flag = false;
        System.out.println(answers);

        req.setAttribute("time", time);

        if (req.getParameter("next") != null) {
            menegernextpreev(req);
            if (!timeout)
                answers.put(numberofques, req.getParameter(String.valueOf(numberofques)));
            numberofques = numberofques + 1;
            flag = true;
        } else if (req.getParameter("previous") != null) {
            menegernextpreev(req);
            if (!timeout)
                answers.put(numberofques, req.getParameter(String.valueOf(numberofques)));
            numberofques = numberofques - 1;

            flag = true;
        } else if (req.getParameter("finish") != null) {
            answers.put(numberofques, req.getParameter(String.valueOf(numberofques)));


            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            pw.println("<center>ваш результат " + checktest() + " балов" +
                    "<br>" +
                    "<a href=\"home\"> вернуться домой <a></center>");
            pw.close();

        }


        if (flag && !timeout) {
            menegernextpreev(req);
            List<Question> ques = daOquestion.getQustions(idtest, numberofques);
            ArrayList<Answer> ans = daOanswer.getAnwerById(daOquestion.getIdByNumberAndTestId(numberofques, idtest));
            req.setAttribute("ques", ques);
            req.setAttribute("ans", ans);


            req.getRequestDispatcher("passtest.jsp").forward(req, resp);
        }


    }

    //проверка теста
    private int checktest() {
        time = 0;
        marc = 0;
        if (!answers.isEmpty()) {

            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(numberofques) != null)
                    if (daOanswer.checkAnswerById(Integer.parseInt(answers.get(i + 1)))) {
                        marc++;
                    }
            }
        }
        marc = Math.ceil((double) 100 / daOquestion.getQuantityOfTest(idtest) * marc);
        DAOresult daOresult = new DAOresult();
        DAOtest daOtest = new DAOtest();
        daOresult.isertIntoResults((int) marc, userid, daOtest.getTextById(idtest), idtest);

        return (int) marc;
    }

    // проверка можна ли перейти к след прев вопросу
    private void menegernextpreev(HttpServletRequest req) {
        req.setAttribute("gotonext", null);
        req.setAttribute("gotopre", null);
        if (!daOquestion.getQustions(idtest, numberofques + 1).isEmpty() && !timeout) {
            req.setAttribute("gotonext", "next");
        }
        if (!daOquestion.getQustions(idtest, numberofques - 1).isEmpty() && !timeout) {
            req.setAttribute("gotopre", "prev");
        }
    }
}


