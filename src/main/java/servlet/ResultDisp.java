package servlet;

import db.DAOResult;
import db.DAOUsers;
import entity.Results;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet("/results")
public class ResultDisp extends HttpServlet {
    private final static Logger log = Logger.getLogger(ResultDisp.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DAOResult daOresult = new DAOResult();

        HttpSession ses = req.getSession();

        DAOUsers daOusers = new DAOUsers();
        if (ses.getAttribute("role").equals("teacher")) {
            if (req.getParameter("idtest") != null)
                ses.setAttribute("idtest", Integer.parseInt(req.getParameter("idtest")));
            int testid = (int) ses.getAttribute("idtest");
            List<Results> res = daOresult.getResultsForTeacher(testid);
            req.setAttribute("res", res);
            req.getRequestDispatcher("WEB-INF/jsp/admin/resultforteach.jsp").forward(req, resp);
        } else if (daOusers.getBlock((Integer) ses.getAttribute("userid"))) {
            ses.setAttribute("block", "block");
            resp.sendRedirect("login");
        } else {

            int userid = (int) req.getSession().getAttribute("userid");
            log.debug("userid = " + userid);
            List<Results> res;
            if (req.getParameter("page") == null) {
                ses.setAttribute("curpage",1);
                res = daOresult.getResultsByUserid(userid, 1);
            }else {
                int page= Integer.parseInt(req.getParameter("page"));
                ses.setAttribute("curpage",page);
                if (page != 1){
                    page=(page - 1)*3;
                }
                res = daOresult.getResultsByUserid(userid, page);
            }
            ses.setAttribute("count", daOresult.getCountOfResults(userid)/3);

            req.setAttribute("res", res);
            req.getRequestDispatcher("WEB-INF/jsp/user/results.jsp").forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("back") != null) {
            resp.sendRedirect("home");
        }

    }
}
