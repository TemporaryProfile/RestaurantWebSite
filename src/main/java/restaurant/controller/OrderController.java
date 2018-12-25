package restaurant.controller;

import restaurant.business.User;
import restaurant.business.cart.Cart;
import restaurant.business.cart.Invoice;
import restaurant.business.menu.CartItem;
import restaurant.business.menu.Dish;
import restaurant.business.menu.Menu;
import restaurant.data.HelperDB;
import restaurant.data.InvoiceDao;
import restaurant.data.UserDao;

import restaurant.data.impl.SimpleInvoiceDao;
import restaurant.data.impl.SimpleUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "OrderController", urlPatterns = {"/order/*"})
public class OrderController extends HttpServlet {
    private final static String DEFAULT_URL = "/cart/";

    private UserDao userDao;
    private InvoiceDao invoiceDao;

    @Override
    public void init() {
        userDao = new SimpleUserDao();
        invoiceDao = new SimpleInvoiceDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final var requestURI = request.getRequestURI();

        final String url;

        if (requestURI.endsWith("/addMenuItem")) {
            url = addMenuItem(request, response);
        } else if (requestURI.endsWith("/checkout")) {
            url = checkIn(request, response);
        } else if (requestURI.endsWith("/processUser")) {
            url = processUser(request, response);
        } else if (requestURI.endsWith("/displayOrder")) {
            url = displayOrder(request, response);
        } else if (requestURI.endsWith("/submitOrder")) {
            url = submitOrder(request, response);
        } else {
            url = DEFAULT_URL;
        }

        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final var requestURI = request.getRequestURI();

        final String url;
        if (requestURI.endsWith("/showCart")) {
            url = showCart(request, response);
        } else {
            url = DEFAULT_URL;
        }

        getServletContext().getRequestDispatcher(url)
                .forward(request, response);
    }

    private String addMenuItem(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null)
            cart = new Cart();

        String menuItemName = request.getParameter("menuItemName");

        Optional<Dish> optionalDish = ((Menu)getServletContext().getAttribute("menu")).getByName(menuItemName);

        final Integer amount = Integer.parseInt(request.getParameter("quantity"));
        CartItem pair = new CartItem(optionalDish.get(), amount);

        cart.addMenuItem(pair);
        session.setAttribute("cart", cart);

        return DEFAULT_URL;
    }

    private String showCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            request.setAttribute("emptyCart", "emptyCart");
        }

        return DEFAULT_URL;
    }

    private String checkIn(HttpServletRequest request, HttpServletResponse response) {
//        User user = new User();
//        request.getSession().setAttribute("user", user);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("in checkin. User: " + user);


        return "/cart/checkIn.jsp";
    }

    private String processUser(HttpServletRequest request, HttpServletResponse response) {
        var firstName = request.getParameter("firstName");
        var lastName = request.getParameter("lastName");
        var email = request.getParameter("email");

        User user = new User(firstName, lastName, email);

        if (!userDao.emailExists(email)) {
            userDao.insert(user);
            user.setId(HelperDB.getId());
        } else {
            User u = userDao.selectUser(email);
            System.out.println("user selected from db: " + u);
            user.setId(u.getId());
        }

        request.getSession().setAttribute("user", user);
//        request.getSession().removeAttribute("cart");

        return "/order/displayOrder";
    }

    private String displayOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        Invoice order = new Invoice();
        order.setCart(cart);
        order.setUser(user);



        session.setAttribute("order", order);
        session.setAttribute("totalPrice", order.totalPrice().toString());
        return "/cart/order.jsp";
    }

    private String submitOrder(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Invoice order = (Invoice) session.getAttribute("order");
        if (order != null) {
            System.out.println("[submit order]: " + order.getUser());
            invoiceDao.insert(order);
            System.out.println("have inserted order: " + order);
        }

        session.removeAttribute("cart");

        return "/cart/orderAccepted.jsp";
    }
}
