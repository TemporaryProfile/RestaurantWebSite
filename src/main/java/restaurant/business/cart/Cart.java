package restaurant.business.cart;

import restaurant.business.menu.CartItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cart implements Serializable {
    private List<CartItem> cart;

    public Cart(List<CartItem> cart) {
        this.cart = cart;
    }

    public Cart() {
        this(new ArrayList<>());
    }

    public int size() {
        return cart.size();
    }

    public List<CartItem> getCart() {
        return Collections.unmodifiableList(cart);
    }

    public boolean addMenuItem(CartItem cartItem) {
        final String dishName = cartItem.getDish().getName();
        for (var item : cart) {
            if (item.getDish().getName().equals(dishName)) {
                item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                return true;
            }
        }
        return cart.add(cartItem);
    }

    public BigDecimal totalPrice() {
        return cart.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Cart: " + cart.stream().map(CartItem::toString).collect(Collectors.joining("\n"));
    }
}
