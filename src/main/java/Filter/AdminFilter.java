package Filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;

public class AdminFilter implements Filter {

    private static final Logger log = Logger.getLogger(AdminFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.debug("AdminFilter start");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        String role = (String) req.getSession(false).getAttribute("role");

        if (req.getSession(false).getAttribute("role") == null || !role.equals("teacher")) {
            resp.sendRedirect("home");
            System.out.println(req.getSession(false).getAttribute("role"));
        } else if (req.getSession(false).getAttribute("id") == null) {
            System.out.println("role");
            resp.sendRedirect("login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


}
