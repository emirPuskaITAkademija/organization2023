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

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        User user = userServiceLocal.findByUsername(username);
        if (user != null) {
            request.setAttribute("message", "Username is already in use");
            RequestDispatcher dispatcher = request.getRequestDispatcher(Routes.REGISTRATION);
            dispatcher.forward(request, response);
        }else{
            char townId = request.getParameter("town").split("\\[")[1].charAt(0);
            Long townIdNumber = Long.parseLong(townId+"");
            Town town = townServiceLocal.find(townIdNumber);
            User newUser = new User();
            newUser.setTown(town);
            newUser.setUsername(username);
            String plainPassword = request.getParameter("password");
            String hashedPassword = pbkdf2PasswordHash.generate(plainPassword.toCharArray());
            newUser.setPassword(hashedPassword);
            newUser.setName(request.getParameter("name"));
            newUser.setSurname(request.getParameter("surname"));
            newUser.setEmail(request.getParameter("email"));
            newUser.setContact(request.getParameter("contact"));
            newUser.setStatus(User.ACTIVE);
            newUser.setPrivilege(privilegeServiceLocal.find(Long.valueOf(3l)));
            userServiceLocal.create(newUser);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Routes.AUTH_LOGIN);
            dispatcher.forward(request, response);
        }
    }
}
