package servlet;

import db.DAOanswer;
import db.DAOquestion;
import entity.answer;
import entity.question;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/passingtest")
public class passtest extends HttpServlet {

    int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession ses = req.getSession();
        id = (int) req.getSession().getAttribute("testid");
        System.out.println(id);

        if (ses != ses.getAttribute("id")) {
            resp.sendRedirect("login");
        } else {

        DAOquestion daOquestion = new DAOquestion();
        DAOanswer daOanswer = new DAOanswer();
        ArrayList<question> ques = daOquestion.getqustions(id);
        ArrayList<answer> ans = daOanswer.getanwerbyid(daOquestion.getidtestbyid(id));
        req.setAttribute("ques", ques);
        req.setAttribute("ans", ans);

        req.getRequestDispatcher("passtest.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOquestion daOquestion = new DAOquestion();
        DAOanswer daOanswer = new DAOanswer();
        Boolean flag = false;
        if (req.getParameter("next") != null) {
            id = getId() + 1;
            flag = true;
        } else if (req.getParameter("previous") != null) {
            id = getId() - 1;
            flag = true;
        } else if(req.getParameter("finish")!=null){
            resp.sendRedirect("home");

        }

        if (flag) {
            ArrayList<question> ques = daOquestion.getqustions(id);
            ArrayList<answer> ans = daOanswer.getanwerbyid(id);
            req.setAttribute("ques", ques);
            req.setAttribute("ans", ans);
            setId(id);
            req.getRequestDispatcher("passtest.jsp").forward(req, resp);
        }
    }
}
