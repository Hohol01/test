package Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import db.DAOUsers;

import java.io.IOException;

public class UserFilter implements Filter {

    private static final Logger log = Logger.getLogger(UserFilter.class);
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        DAOUsers daOusers = new DAOUsers();
        log.debug("UserFilter start");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession ses = req.getSession();
        String role = (String) ses.getAttribute("role");
        ses.removeAttribute("block");
        if (role == null || !role.equals("student")) {
            resp.sendRedirect("home");
            System.out.println(req.getSession(false).getAttribute("role"));
        } else if (req.getSession(false).getAttribute("id") == null || req.getSession(false).getAttribute("id")!=req.getSession()) {
            System.out.println("role");
            resp.sendRedirect("login");
        } else if (daOusers.getBlock((Integer) ses.getAttribute("userid"))) {
            ses.setAttribute("block", "block");
            ses.removeAttribute("id");
            ses.removeAttribute("role");
            resp.sendRedirect("login");
        } else
            filterChain.doFilter(servletRequest, servletResponse);

    }
}
