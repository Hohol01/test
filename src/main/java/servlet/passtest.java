package servlet;

import db.DAOanswer;
import db.DAOquestion;
import db.DAOresult;
import db.DAOtest;
import entity.answer;
import entity.question;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
                    interrupt();
                    checktest(marc);
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
        userid = (Integer) req.getSession().getAttribute("userid");
        answers = new HashMap<>();
        HttpSession ses = req.getSession();
        idtest = Integer.parseInt(req.getParameter("idtest"));
        time = daOtest.gettimebyid(idtest) * 60;

        req.setAttribute("time", time);
        System.out.println(time);
        numberofques = 1;
        quantityofquestion = daOquestion.getquantityoftest(idtest);
        menegernextpreev(req);


        req.setAttribute("time", time);

        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if (ses.getAttribute("role").equals("teacher")) {
            resp.sendRedirect("home");
        } else {


            List<question> ques = daOquestion.getqustions(idtest, 1);
            ArrayList<answer> ans = daOanswer.getanwerbyid(daOquestion.getidbynumberandtestid(1, idtest));
            req.setAttribute("ques", ques);
            req.setAttribute("ans", ans);
            req.getRequestDispatcher("passtest.jsp").forward(req, resp);
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
            checktest(marc);
            timer.interrupt();
            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            pw.println("<center>ваш результат " + (int) marc + " балов" +
                    "<br>" +
                    "<a href=\"home\"> вернуться домой <a></center>");
            pw.close();


        }


        if (flag && !timeout) {
            menegernextpreev(req);
            List<question> ques = daOquestion.getqustions(idtest, numberofques);
            ArrayList<answer> ans = daOanswer.getanwerbyid(daOquestion.getidbynumberandtestid(numberofques, idtest));
            req.setAttribute("ques", ques);
            req.setAttribute("ans", ans);


            req.getRequestDispatcher("passtest.jsp").forward(req, resp);
        }


    }

    //проверка теста
    private void checktest(double marc) {
        if (!answers.isEmpty()) {

            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(numberofques) != null)
                    if (daOanswer.checkanswerbyid(Integer.parseInt(answers.get(i + 1)))) {
                        marc++;
                    }
            }
        }
        marc = Math.ceil((double) 100 / daOquestion.getquantityoftest(idtest) * marc);
        DAOresult daOresult = new DAOresult();
        DAOtest daOtest = new DAOtest();
        daOresult.isertintoresults((int) marc, userid, daOtest.gettextbyid(idtest), idtest);


    }

    // проверка можна ли перейти к след прев вопросу
    private void menegernextpreev(HttpServletRequest req) {
        req.setAttribute("gotonext", null);
        req.setAttribute("gotopre", null);
        if (!daOquestion.getqustions(idtest, numberofques + 1).isEmpty() && !timeout) {
            req.setAttribute("gotonext", "next");
        }
        if (!daOquestion.getqustions(idtest, numberofques - 1).isEmpty() && !timeout) {
            req.setAttribute("gotopre", "prev");
        }
    }
}


