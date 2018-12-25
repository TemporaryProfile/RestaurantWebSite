package restaurant.business.cart;

import restaurant.business.User;
import restaurant.business.menu.CartItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Invoice implements Serializable {
    private Cart cart;
    private User user;
    private Long id;
    private Date date;

    public Invoice() {}

    public BigDecimal totalPrice() {
        return cart.totalPrice();
    }

    public List<CartItem> getCart() {
        return cart.getCart();
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("Order: [Cart: %s, User: %s, Date: %s]", cart, user, date);
    }
}
