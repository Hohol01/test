package servlet;

import db.DAOAnswer;
import db.DAOQuestion;
import db.DAOTest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;

@WebServlet("/addtest")
public class AddTest extends HttpServlet {
    private static final Logger log = Logger.getLogger(AddTest.class);

    int number_qustion = 1;
    int idtest = 0;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        number_qustion = 1;
        System.out.println(req.getContextPath());

        if (req.getParameter("addqus") != null) {
            req.getRequestDispatcher("WEB-INF/jsp/admin/addqus.jsp").forward(req, resp);
        } else {
            DAOTest daoTest = new DAOTest();
            System.out.println(daoTest.getSubjectAdd(String.valueOf(req.getSession().getAttribute("language"))));
            req.setAttribute("lang", daoTest.getSubjectAdd(String.valueOf(req.getSession().getAttribute("language"))));
            req.getRequestDispatcher("WEB-INF/jsp/admin/addtest.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession ses = req.getSession();

        System.out.println("AddTest");


        DAOTest t = new DAOTest();

        DAOQuestion q = new DAOQuestion();
        DAOAnswer a = new DAOAnswer();

        // adding Test
        if (req.getParameter("addtest") != null) {
            String name = req.getParameter("name");
            int subdgect = Integer.parseInt(req.getParameter("subdgect"));
            int time = Integer.parseInt(req.getParameter("time"));
            int hardnest = Integer.parseInt(req.getParameter("hardnest"));
            if ((name.isEmpty()) || (subdgect == 0) || time <= 0 || hardnest > 5 || hardnest < 1) {
                req.setAttribute("error", "error");
                req.getRequestDispatcher("WEB-INF/jsp/admin/addtest.jsp");
            } else {
                if (req.getParameter("addquss") == null)
                    t.setTest(name, subdgect, hardnest, time);
                idtest = t.getIdByName(name);
                req.getSession().setAttribute("idtest", idtest);
                System.out.println(idtest);
                resp.sendRedirect("addtest" + "?addqus=true");
            }
        }


        //adding questions and answers
        if (req.getParameter("add") != null) {
            if (!req.getParameter("question").equals("")) {
                String qution = req.getParameter("question");
                System.out.println(qution);

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
            } else {
                req.setAttribute("error", "error");
                ;
            }
            req.setAttribute("addqus", true);
            req.getRequestDispatcher("WEB-INF/jsp/admin/addqus.jsp").forward(req, resp);
        }
        if (req.getParameter("language") != null) {
            String lang = String.valueOf(req.getParameter("language"));
            req.setAttribute("lang", t.getSubjectAdd(lang));
            log.debug("language sub = " + lang);
            System.out.println(req.getRequestURI());
            if (req.getParameter("addqus") == null)
                req.getRequestDispatcher("WEB-INF/jsp/admin/addtest.jsp").forward(req, resp);
            else {
                req.setAttribute("addqus", true);
                req.getRequestDispatcher("WEB-INF/jsp/admin/addqus.jsp").forward(req, resp);
            }
        }

        if (req.getParameter("finish") != null) {
            resp.sendRedirect("home");

        }
    }

}
