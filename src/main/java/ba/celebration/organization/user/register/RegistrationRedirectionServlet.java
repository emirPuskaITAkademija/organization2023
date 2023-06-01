package ba.celebration.organization.user.register;

import ba.celebration.organization.country.town.Town;
import ba.celebration.organization.country.town.TownServiceLocal;
import ba.celebration.organization.utils.Routes;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "registrationRedirectionServlet", urlPatterns = "/registration")
public class RegistrationRedirectionServlet extends HttpServlet {

    @Inject
    private TownServiceLocal townServiceLocal;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //Controller -> model i view
        List<Town> towns = townServiceLocal.findAll();
        req.setAttribute("towns", towns);
        req.setAttribute("message", "");
        RequestDispatcher dispatcher = req.getRequestDispatcher(Routes.REGISTRATION);
        dispatcher.include(req, resp);
    }
}
