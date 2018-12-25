package restaurant.data;

import restaurant.business.menu.Dish;

import java.util.List;

public interface DishDao {
    Dish selectDish(int dishId);
    List<Dish> selectAll();
}
