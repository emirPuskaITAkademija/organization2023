package ba.celebration.organization;

import java.io.*;
import java.util.List;

import ba.celebration.organization.user.ejb.User;
import ba.celebration.organization.user.ejb.UserService;
import ba.celebration.organization.user.ejb.UserServiceLocal;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
// browser -> http get request ->  HelloServlet(Servlet Container) -> EJB(JPA) Hibernate JPA -> DB MySQL
// HelloServlet -> http response -> ja nisam view -> al ću dati view users.jsp ->
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {


    @Inject
    private UserServiceLocal userServiceLocal;//Local Stateless SessionBean


    /**
     HelloServlet helloServlet  = new HelloServlet();
     helloServlet.doGet(req, res);
     */

    //http://localhost:8080/organization-1.0-SNAPSHOT/

    //HTTP GET request
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> users = userServiceLocal.findAll();
        request.setAttribute("korisnici", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users.jsp");
        dispatcher.forward(request, response);
    }
}