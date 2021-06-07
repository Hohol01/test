package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/home")
public class homepage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession ses = req.getSession();
        if (ses != ses.getAttribute("id")){
            resp.sendRedirect("login");
        }else
        req.getRequestDispatcher("homepage.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("test")!=null){
            resp.sendRedirect("test");
            System.out.println("test");
        }
        else if(req.getParameter("add")!=null){
            System.out.println("add");
            resp.sendRedirect("addtest");
        }
    }
}
