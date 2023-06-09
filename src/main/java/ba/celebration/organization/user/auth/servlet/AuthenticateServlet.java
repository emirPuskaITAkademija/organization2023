package ba.celebration.organization.user.auth.servlet;

import ba.celebration.organization.user.ejb.User;
import ba.celebration.organization.user.ejb.UserServiceLocal;
import ba.celebration.organization.utils.Routes;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "authenticateServlet", urlPatterns = "/authenticate")
public class AuthenticateServlet extends HttpServlet {

    //rahima
    //rahima123
    @Inject
    private UserServiceLocal userServiceLocal;

    @Inject
    private Pbkdf2PasswordHash pbkdf2PasswordHash;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String usernameParam = request.getParameter("username");
        String plainPassword = request.getParameter("password");//PLAIN PASSWORD
        User user = userServiceLocal.findByUsername(usernameParam);
        String hashedPassword = user.getPassword();
        request.setAttribute("message", "");
        if(user != null && pbkdf2PasswordHash.verify(plainPassword.toCharArray(), hashedPassword)){
            HttpSession session = request.getSession();
            User userFromSession =(User) session.getAttribute("session_user");
            if(userFromSession == null){
                session.setAttribute("session_user", user);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(Routes.DASHBOARD_ACCES);
            dispatcher.forward(request, response);
        }else{
            String message = "Neispravna kombinacija lozinke i korisničkog naloga";
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Routes.AUTH_LOGIN);
            dispatcher.forward(request, response);
        }
    }

}
