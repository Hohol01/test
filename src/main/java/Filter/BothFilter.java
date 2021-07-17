package Filter;

import db.DAOUsers;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class BothFilter implements Filter {

    DAOUsers daoUsers = new DAOUsers();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession ses = req.getSession();
        if (ses.getAttribute("role")==null){
            resp.sendRedirect("login");
        } else if (daoUsers.getBlock((Integer) ses.getAttribute("userid"))) {
            ses.setAttribute("block", "block");
            ses.removeAttribute("id");
            ses.removeAttribute("role");
            resp.sendRedirect("login");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
