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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/edit")
public class edit extends HttpServlet {
    DAOtest daOtest = new DAOtest();
    DAOquestion daOquestion = new DAOquestion();
    DAOanswer daOanswer = new DAOanswer();
    int idtest;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        idtest = Integer.parseInt(req.getParameter("idtest"));

        System.out.println(req.getParameter("idtest"));
        ArrayList<test> testArrayList= daOtest.gettesttsbyid(Integer.parseInt(req.getParameter("idtest")));
        List<question> ques = daOquestion.getqustions(idtest,1);
        ArrayList<answer> ans = daOanswer.getanwerbyid(daOquestion.getidtestbyid(idtest));
        req.setAttribute("ques", ques);
        req.setAttribute("ans", ans);

        req.setAttribute("test", testArrayList);
        req.setAttribute("displaying", "test");


        req.getRequestDispatcher("edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
