package restaurant.business.menu;

import restaurant.customExceptions.CartItemException;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
    private Dish dish;
    private Integer quantity;

    public CartItem(Dish dish, Integer quantity) {
        setQuantity(quantity);
        this.dish = dish;
    }

    public Dish getDish() {
        return dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setQuantity(Integer quantity) {
        if (quantity <= 0)
            throw new CartItemException(quantity);
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return dish.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return String.format("CartItem: (%s, %s)", dish, quantity);
    }
}
