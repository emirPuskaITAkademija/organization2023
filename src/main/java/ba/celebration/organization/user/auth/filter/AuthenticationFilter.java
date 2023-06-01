package ba.celebration.organization.user.auth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginRuta = req.getContextPath()+"/authentication";
        String dashboardRuta = req.getContextPath()+"/dashboardAccess";

        boolean loggedIn = session!=null && session.getAttribute("session_user")!=null;
        boolean loginRequest = req.getRequestURI().equals(loginRuta);
        boolean dasboardRequest = req.getRequestURI().equals(dashboardRuta);
        if(loggedIn || loginRequest || dasboardRequest){
            chain.doFilter(request, response);
        }else{
            resp.sendRedirect(loginRuta);
        }
    }
}
