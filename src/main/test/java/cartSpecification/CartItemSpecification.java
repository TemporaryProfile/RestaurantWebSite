package cartSpecification;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import restaurant.business.menu.CartItem;
import restaurant.business.menu.Dish;
import restaurant.customExceptions.CartItemException;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartItemSpecification {
    @Mock
    private Dish dish1;

    private CartItem cartItem;

    private static BigDecimal dishPrice1;

    private static Integer quantity1, quantity2;

    @BeforeAll
    public static void setUp() {
        quantity1 = 10;
        dishPrice1 = BigDecimal.valueOf(25);

        quantity2 = 0;
    }

    @BeforeEach
    public void init() {
        dish1 = mock(Dish.class);
        when(dish1.getPrice()).thenReturn(dishPrice1);
    }

    @Test
    @DisplayName("10 items which cost 25 each, will return 250")
    public void totalPriceIsCorrectFor10Items() {
        cartItem = new CartItem(dish1, quantity1);

        final BigDecimal expectedTotalPrice = dishPrice1.multiply(BigDecimal.valueOf(quantity1));

        assertThat(cartItem.getPrice()).isEqualTo(expectedTotalPrice);
    }

    @Test
    @DisplayName("if CartItem constructor is passed quantity <= 0, then CartItemException is thrown")
    public void exceptionThrownWhenQuantityZero() {
        assertThrows(
                CartItemException.class,
                () -> new CartItem(dish1, quantity2)
        );
    }
}
