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
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/passingtest")
public class PassTest extends HttpServlet {
    private final static Logger log = Logger.getLogger(PassTest.class);

    int idtest;
    int time = 0;
    int quantityofquestion = 0;
    int numberofques;
    double marc = 0;
    int userid;
    boolean timeout = false;


    Map<Integer, String> answers;
    DAOQuestion daOquestion = new DAOQuestion();
    DAOAnswer daOanswer = new DAOAnswer();
    DAOTest daOtest = new DAOTest();

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
        userid = (int) ses.getAttribute("userid");
        time = daOtest.getTimeById(idtest) * 60;
        req.setAttribute("time", time);
        numberofques = 1;
        quantityofquestion = daOquestion.getQuantityOfTest(idtest);
        menegerNextPrev(req);
        req.setAttribute("time", time);
        DAOUsers daOusers = new DAOUsers();
        List<Question> ques = daOquestion.getQustions(idtest, 1);
        ArrayList<Answer> ans = daOanswer.getAnwerById(daOquestion.getIdByNumberAndTestId(1, idtest));
        req.setAttribute("ques", ques);
        req.setAttribute("ans", ans);
        req.getRequestDispatcher("WEB-INF/jsp/user/passtest.jsp").forward(req, resp);
        timer.start();


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if (!ses.getAttribute("role").equals("teacher"))
            resp.sendRedirect("home");

        marc = 0;
        Boolean flag = false;
        req.setAttribute("time", time);
        if (req.getParameter("next") != null) {
            menegerNextPrev(req);
            if (!timeout)
                answers.put(numberofques, req.getParameter(String.valueOf(numberofques)));
            numberofques = numberofques + 1;
            flag = true;
        } else if (req.getParameter("previous") != null) {
            menegerNextPrev(req);
            if (!timeout)
                answers.put(numberofques, req.getParameter(String.valueOf(numberofques)));
            numberofques = numberofques - 1;

            flag = true;
        } else if (req.getParameter("finish") != null) {
            answers.put(numberofques, req.getParameter(String.valueOf(numberofques)));
            time = 0;
            checktest();
            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            pw.println("<center>ваш результат " + (int) marc + " балов" +
                    "<br>" +
                    "<a href=\"home\"> вернуться домой <a></center>");
            pw.close();

        }


        if (flag && !timeout) {
            menegerNextPrev(req);
            List<Question> ques = daOquestion.getQustions(idtest, numberofques);
            ArrayList<Answer> ans = daOanswer.getAnwerById(daOquestion.getIdByNumberAndTestId(numberofques, idtest));
            req.setAttribute("ques", ques);
            req.setAttribute("ans", ans);

            req.getRequestDispatcher("WEB-INF/jsp/user/passtest.jsp").forward(req, resp);
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
        DAOResult daOresult = new DAOResult();
        DAOTest daOtest = new DAOTest();
        log.debug("userid ==> " + userid);
        log.debug("insert start");
        daOresult.isertIntoResults((int) marc, userid, daOtest.getTextById(idtest), idtest);
        return (int) marc;
    }

    // проверка можна ли перейти к след прев вопросу
    private void menegerNextPrev(HttpServletRequest req) {
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


