package servlet;

import db.DAOanswer;
import db.DAOqutions;
import db.DAOtest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Time;

@WebServlet("/addtest")
public class addtest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addtest.jsp").forward(req,resp);
        System.out.println("add");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String subdgect = req.getParameter("subdgect");
        String qution = req.getParameter("qution");
        int time  = Integer.parseInt(req.getParameter("time"));
        int hardnest = Integer.parseInt(req.getParameter("hardnest"));
        String answer = req.getParameter("answer");
        String answer1 = req.getParameter("answer1");
        DAOtest t =new DAOtest();
        DAOqutions q = new DAOqutions();
        DAOanswer a=new DAOanswer();





    }
}
