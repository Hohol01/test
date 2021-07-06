package servlet;

import db.DAOanswer;
import db.DAOquestion;
import db.DAOtest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/addtest")
public class AddTest extends HttpServlet {

    int number_qustion = 1;
    int idtest = 0;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        number_qustion = 1;

        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else if (!ses.getAttribute("role").equals("teacher"))
            resp.sendRedirect("home");

        else
            req.getRequestDispatcher("addtest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddTest");


        DAOtest t = new DAOtest();

        DAOquestion q = new DAOquestion();
        DAOanswer a = new DAOanswer();

        // adding Test
        if (req.getParameter("addtest") != null) {
            String name = req.getParameter("name");
            String subdgect = req.getParameter("subdgect");
            int time = Integer.parseInt(req.getParameter("time"));
            int hardnest = Integer.parseInt(req.getParameter("hardnest"));
            t.setTest(name, subdgect, hardnest, time);
            idtest = t.getIdByName(name);
            req.getSession().setAttribute("idtest", idtest);
            System.out.println(idtest);
            req.getRequestDispatcher("addqus.jsp").forward(req, resp);
        }


        //adding questions and answers
        if (req.getParameter("add") != null) {

            String qution = req.getParameter("question");
            System.out.println(idtest);

            q.setQuestion(qution, idtest, number_qustion);
            number_qustion++;

            int idqus = q.getIdByName(qution, idtest);
            while (idqus == 0) {
                idqus = q.getIdByName(qution, idtest);
            }
            for (int i = 1; i < 5; i++) {

                String answer = req.getParameter("ans" + i);
                boolean cor = false;
                if (req.getParameter("correct" + i) != null)
                    cor = true;
                if (!answer.equals(""))
                    a.addAnswer(idqus, answer, cor, i, idtest);
            }
            req.getRequestDispatcher("addqus.jsp").forward(req, resp);
        }

        if (req.getParameter("finish") != null) {
            resp.sendRedirect("home");

        }
    }
}
