package servlet;

import db.DAOtest;
import entity.test;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/test")
public class testdisp extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("test");
        ArrayList<test> testlist = DAOtest.getlistoftests();
        System.out.println(testlist);

        req.setAttribute("testlist", DAOtest.getlistoftests());
        req.getRequestDispatcher("/test.jsp").forward(req,resp);



    }
}
