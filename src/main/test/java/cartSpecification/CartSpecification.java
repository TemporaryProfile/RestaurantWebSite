package cartSpecification;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import restaurant.business.cart.Cart;
import restaurant.business.menu.CartItem;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartSpecification {
    @Mock
    private CartItem cartItem1;

    @Mock
    private CartItem cartItem2;

    private static BigDecimal price1, price2;

    @BeforeAll
    public static void setUp() {
        price1 = BigDecimal.valueOf(25);
        price2 = BigDecimal.valueOf(15);
    }

    @BeforeEach
    public void init() {
        cartItem1 = mock(CartItem.class);
        when(cartItem1.getPrice()).thenReturn(price1);

        cartItem2 = mock(CartItem.class);
        when(cartItem2.getPrice()).thenReturn(price2);
    }

    @Test
    @DisplayName("totalPrice(), invoked on empty cart, will return zero")
    public void emptyCartHasZeroPrice() {
        assertThat(new Cart().totalPrice()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void totalPriceIsCorrect() {
        final BigDecimal expectedTotalPrice = price1.add(price2);
        assertThat(
                new Cart(List.of(cartItem1, cartItem2)).totalPrice())
                    .isEqualTo(expectedTotalPrice);
    }
}
