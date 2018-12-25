package restaurant.data.impl;

import restaurant.business.User;
import restaurant.business.cart.Cart;
import restaurant.business.cart.Invoice;
import restaurant.business.menu.CartItem;
import restaurant.data.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleInvoiceDao implements InvoiceDao {

    private CartItemDao cartItemDao;
    private UserDao userDao;

    public SimpleInvoiceDao() {
        this.cartItemDao = new SimpleCartItemDao();
        this.userDao = new SimpleUserDao();
    }

    @Override
    public boolean insert(Invoice invoice) {
        final String query = "INSERT INTO Invoice (UserID, TotalAmount, InvoiceDate, IsProcessed) VALUES (?, ?, now(), 'no')";

        try (var con = HelperDB.getConnection();
             var prepStat = con.prepareStatement(query)) {

            prepStat.setLong(1, invoice.getUser().getId());
            prepStat.setFloat(2, invoice.totalPrice().floatValue());
//            prepStat.setDate(3, invoice.getDate());
            prepStat.executeUpdate();

//            Long id = HelperDB.getId();
            //Get the InvoiceID from the last INSERT statement.
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = con.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
            identityResultSet.next();
            long id = identityResultSet.getLong("IDENTITY");
            identityResultSet.close();
            identityStatement.close();

            System.out.println("insert InvoiceDao, id: " + id);

            invoice.getCart().stream()
                    .forEach(item -> cartItemDao.insert(id, item));
            return true;
        } catch (SQLException ex) {
            System.err.println("insert InvoiceDao: " + ex);
            return false;
        }
    }

    @Override
    public List<Invoice> selectUnprocessedInvoices() {
        System.out.println("selectUnprocessedInvoices.invoiceDao");

        final String query = "SELECT * FROM User u INNER JOIN Invoice i ON u.UserID = i.UserID WHERE i.IsProcessed = 'NO'";

        try (var con = HelperDB.getConnection();
             var prepStat = con.prepareStatement(query);
             var resSet = prepStat.executeQuery()) {
            System.out.println("in try block");

            List<Invoice> unprocessedInvoices = new ArrayList<>();
            while (resSet.next()) {
                User user = userDao.selectUser(resSet.getString("Email"));
                System.out.println("user: " + user);
                long invoiceId = resSet.getLong("i.InvoiceID");
                System.out.println("invoiceId: " + invoiceId);
                List<CartItem> cartItems = cartItemDao.selectCartItems(invoiceId);

                System.out.println("cartItems: " + cartItems);
                Invoice invoice = new Invoice();
                invoice.setUser(user);
                invoice.setCart(new Cart(cartItems));
                invoice.setId(invoiceId);
                unprocessedInvoices.add(invoice);
            }
            return unprocessedInvoices;
        } catch (SQLException ex) {
            System.err.println("selectUnprocessed: " + ex);
        }
        return List.of();
    }
}