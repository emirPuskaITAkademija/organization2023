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

@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

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

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(Routes.AUTH_LOGIN);
        requestDispatcher.forward(req, resp);
    }
}
