package restaurant.business.menu;

import java.util.List;
import java.util.Optional;

public interface Menu {
    List<Dish> getMenu();
    Optional<Dish> getByName(String name);
}
