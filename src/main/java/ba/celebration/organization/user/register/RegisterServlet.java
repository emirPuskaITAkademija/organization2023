package ba.celebration.organization.user.register;

import ba.celebration.organization.country.town.Town;
import ba.celebration.organization.country.town.TownServiceLocal;
import ba.celebration.organization.user.ejb.User;
import ba.celebration.organization.user.ejb.UserServiceLocal;
import ba.celebration.organization.user.privilege.ejb.Privilege;
import ba.celebration.organization.user.privilege.ejb.PrivilegeServiceLocal;
import ba.celebration.organization.utils.Routes;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "registerServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {


    @Inject
    private UserServiceLocal userServiceLocal;

    @Inject
    private PrivilegeServiceLocal privilegeServiceLocal;
    @Inject
    private TownServiceLocal townServiceLocal;

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

        String username = req.getParameter("username");
        User user = userServiceLocal.findByUsername(username);
        if (user != null) {
            req.setAttribute("message", "Username is already in use");
            RequestDispatcher dispatcher = req.getRequestDispatcher(Routes.REGISTRATION);
            dispatcher.forward(req, resp);
        }else{
            char townId = req.getParameter("town").split("\\[")[1].charAt(0);
            Long townIdNumber = Long.parseLong(townId+"");
            Town town = townServiceLocal.find(townIdNumber);
            User newUser = new User();
            newUser.setTown(town);
            newUser.setUsername(username);
            String hashedPassword = pbkdf2PasswordHash.generate(req.getParameter("password").toCharArray());
            newUser.setPassword(hashedPassword);
            newUser.setName(req.getParameter("name"));
            newUser.setSurname(req.getParameter("surname"));
            newUser.setEmail(req.getParameter("email"));
            newUser.setContact(req.getParameter("contact"));
            newUser.setStatus(User.ACTIVE);
            newUser.setPrivilege(privilegeServiceLocal.find(Privilege.CLIENT_PRIVILEGE));
            userServiceLocal.create(newUser);
            RequestDispatcher dispatcher = req.getRequestDispatcher(Routes.AUTH_LOGIN);
            dispatcher.forward(req, resp);
        }
    }
}
