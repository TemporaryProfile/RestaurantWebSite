package restaurant.controller;

import restaurant.business.cart.Invoice;
import restaurant.data.InvoiceDao;
import restaurant.data.impl.SimpleInvoiceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminController", urlPatterns = {"/adminController/*"})
public class AdminController extends HttpServlet {
    private InvoiceDao invoiceDao;

    @Override
    public void init() {
        invoiceDao = new SimpleInvoiceDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final var requestURI = request.getRequestURI();
        final String url;

        if (requestURI.endsWith("/viewInvoices")) {
            url = viewInvoices(request, response);
        } else {
            url = "/admin/index.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String processInvoice(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

    private String viewInvoices(HttpServletRequest request, HttpServletResponse response) {
        final List<Invoice> unprocessed = invoiceDao.selectUnprocessedInvoices();
        System.out.println("viewInvoices.admin: " + unprocessed);

        request.getSession().setAttribute("unprocessedInvoices", unprocessed);
        return "/admin/invoices.jsp";
    }
}
