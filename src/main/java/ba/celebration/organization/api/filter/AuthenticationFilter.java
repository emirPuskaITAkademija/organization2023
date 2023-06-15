package ba.celebration.organization.api.filter;

import ba.celebration.organization.user.ejb.User;
import ba.celebration.organization.user.ejb.UserServiceLocal;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Base64;

@WebFilter(urlPatterns = { "/dashboard/*", "/hello-servlet", "/api/*"})
public class AuthenticationFilter implements Filter {

    @Inject
    private UserServiceLocal userServiceLocal;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if(request.getMethod().equals("OPTIONS")){
            chain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader("Authorization");
        //ivica:ivica123  -> encoder -> aXZpY2E6aXZpY2ExMjM=
        //aXZpY2E6aXZpY2ExMjM=  -> decode -> ivica:ivica123
        //Basic aXZpY2E6aXZpY2ExMjM=
        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            //Basic aXZpY2E6aXZpY2ExMjM=
            String encodedCredentials = authorizationHeader.substring("Basic ".length());//aXZpY2E6aXZpY2ExMjM=
            String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials), "UTF-8");//ivica:ivica123
            String[] credentials = decodedCredentials.split(":");//{ "ivica", "ivica123"}
            String username = credentials[0];
            String plainPassword = credentials[1];
            if (isValidCredentials(username, plainPassword)) {
                //nek dalje pristupi ovaj zahtjev servisnim metodama koje se nalaze iza /api/***
                chain.doFilter(request, response);
            } else {
                HttpSession session = request.getSession(false);
                response.sendRedirect(request.getContextPath() + "/authentication");
            }
        } else {
            HttpSession session = request.getSession(false);
            response.sendRedirect(request.getContextPath() + "/authentication");
        }
    }

    private boolean isValidCredentials(String username, String plainPassword) {
        User user = userServiceLocal.findByUsername(username);
        if (user == null) {
            return false;
        }
        String hashedPassword = user.getPassword();
        boolean matchingPlainPasswordWithHashedPassword = passwordHash.verify(plainPassword.toCharArray(), hashedPassword);
        return matchingPlainPasswordWithHashedPassword;
    }
}
