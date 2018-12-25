package restaurant.data;

import restaurant.business.menu.CartItem;

import java.util.List;

public interface CartItemDao {
    long insert(long invoiceID, CartItem item);
    List<CartItem> selectCartItems(long invoiceId);
}
