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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/passingtest")
public class passtest extends HttpServlet {

    int idtest;

    int quantityofquestion = 0;

    int numberofques;

    Map<Integer, String> answers;
    DAOquestion daOquestion = new DAOquestion();
    DAOanswer daOanswer = new DAOanswer();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        answers = new HashMap<>();
        HttpSession ses = req.getSession();
        idtest = (int) req.getSession().getAttribute("testid");
        System.out.println(idtest);
        menegernextpreev(req);
        numberofques = 1;
        quantityofquestion = daOquestion.getquantityoftest(idtest);
        menegernextpreev(req);

        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
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

        double marc = 0;
        Boolean flag = false;
        System.out.println(answers);


        if (req.getParameter("next") != null) {
            answers.put(numberofques, req.getParameter(String.valueOf(numberofques)));
            numberofques = numberofques + 1;
            flag = true;
        } else if (req.getParameter("previous") != null) {
            answers.put(numberofques, req.getParameter(String.valueOf(numberofques)));
            numberofques = numberofques - 1;

            flag = true;
        } else if (req.getParameter("finish") != null) {
            answers.put(numberofques, req.getParameter(String.valueOf(numberofques)));


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
            daOresult.isertintoresults((int) marc, (Integer) req.getSession().getAttribute("userid"), daOtest.gettextbyid(idtest));

            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            pw.println("<center>ваш результат " + (int) marc + " балов" +
                    "<br>" +
                    "<a href=\"home\"> вернуться домой <a></center>");
            pw.close();

        }


        if (flag) {
            menegernextpreev(req);
            List<question> ques = daOquestion.getqustions(idtest, numberofques);
            ArrayList<answer> ans = daOanswer.getanwerbyid(daOquestion.getidbynumberandtestid(numberofques, idtest));
            req.setAttribute("ques", ques);
            req.setAttribute("ans", ans);


            req.getRequestDispatcher("passtest.jsp").forward(req, resp);
        }


    }

    private void menegernextpreev(HttpServletRequest req) {
        req.setAttribute("gotonext", null);
        req.setAttribute("gotopre", null);
        if (!daOquestion.getqustions(idtest, numberofques + 1).isEmpty()) {
            req.setAttribute("gotonext", "next");
        }
        if (!daOquestion.getqustions(idtest, numberofques - 1).isEmpty()) {
            req.setAttribute("gotopre", "prev");
        }
    }
}


