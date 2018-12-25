package restaurant.data.impl;

import restaurant.business.menu.CartItem;
import restaurant.business.menu.Dish;
import restaurant.data.CartItemDao;
import restaurant.data.DishDao;
import restaurant.data.HelperDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleCartItemDao implements CartItemDao {
    private DishDao dishDao;

    /**
     * Maybe there is a point in passing a singleton value into the constructor
     * to avoid unnecessary object allocations.
     * @param dishDao
     */
    public SimpleCartItemDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }

    public SimpleCartItemDao() {
        this(new SimpleDishDao());
    }

    @Override
    public long insert(long invoiceID, CartItem item) {
        final String query = "INSERT INTO CartItem(invoiceId, dishId, quantity) VALUES (?, ?, ?)";

        try (var con = HelperDB.getConnection();
             var prepStat = con.prepareStatement(query)) {

            System.out.println("insert cartItemDao, invoiceID = " + invoiceID + " dishId: " + item.getDish().getId() + " quan: " + item.getQuantity());
            prepStat.setLong(1, invoiceID);
            prepStat.setLong(2, item.getDish().getId());
            prepStat.setInt(3, item.getQuantity());

            return prepStat.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("insert CartItemDao: " + ex);
            return 0;
        }
    }

    @Override
    public  List<CartItem> selectCartItems(long invoiceID) {
        System.out.println("selectCartItems");

        final String query = "SELECT * FROM CartItem WHERE InvoiceID = ?";

        try (var con = HelperDB.getConnection();
             var prepStat = con.prepareStatement(query)) {
            System.out.println("in try block");

            prepStat.setLong(1, invoiceID);
            try (var resSet = prepStat.executeQuery()) {
                final List<CartItem> cartItems = new ArrayList<>();

                while (resSet.next()) {
                    System.out.println("in while()");
                    int dishID = resSet.getInt("DishID");
                    Dish dish = dishDao.selectDish(dishID);
                    System.out.println("selected dish: " + dish);
                    Integer quantity = resSet.getInt("Quantity");
                    CartItem cartItem = new CartItem(dish, quantity);
                    cartItems.add(cartItem);
                }
                return cartItems;
            }
        } catch (SQLException ex) {
            System.err.println("selectCartItems: " + ex);
        }
        return List.of();
    }
}
