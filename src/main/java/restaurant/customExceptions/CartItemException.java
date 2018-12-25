package restaurant.customExceptions;

public class CartItemException extends RuntimeException {
    private final int quantity;

    public CartItemException(int quantity) {
        super(String.format("CartItem exceptional condition: quantity = %s", quantity));
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
