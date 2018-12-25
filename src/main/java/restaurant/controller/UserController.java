package restaurant.controller;

import restaurant.business.User;
import restaurant.data.UserDao;
import restaurant.data.impl.SimpleUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "UserController", urlPatterns = {"/userController/*"})
public class UserController extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() {
        this.userDao = new SimpleUserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final var requestURI = request.getRequestURI();
        final String url;
        if (requestURI.endsWith("/subscribe"))
            url = subscribe(request, response);
        else if (requestURI.endsWith("/unsubscribe")) {
            System.out.println("Unsubscribing...");
            url = unsubscribe(request, response);
        } else {
            url = deleteCookies(request, response);
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private String subscribe(HttpServletRequest request, HttpServletResponse response) {
        final var firstName = request.getParameter("firstName");
        final var lastName = request.getParameter("lastName");
        final var email = request.getParameter("email");

        final var user = new User(firstName, lastName, email);
        request.setAttribute("user", user);
        request.getSession().setAttribute("user", user);

        if (!userDao.emailExists(email)) {
            userDao.insert(user);
        }
        userDao.subscribeUser(email);

        return "/emailList/thanks.jsp";
    }

    private String unsubscribe(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("[unsubscribe] start");
        final var email = request.getParameter("email");

        if (userDao.emailExists(email)) {
            userDao.unsubscribeUser(email);
            request.setAttribute("message", "You were unsubscribed.");
            return "/emailList/unsubscribed.jsp";
        } else {
            request.setAttribute("message", "Email you entered does not exist or is not valid. Try again.");
            return "/emailList/unsubscribe.jsp";
        }
    }

    private String deleteCookies(HttpServletRequest request, HttpServletResponse response) {
        Arrays.stream(request.getCookies()).forEach(c -> {
            c.setMaxAge(0);
            c.setPath("/");
            response.addCookie(c);
        });

        request.getSession().invalidate();

        request.getSession().invalidate();
        return "/cookies.jsp";
    }
}