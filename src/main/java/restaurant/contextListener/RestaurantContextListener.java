package restaurant.contextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.business.menu.Dish;
import restaurant.business.menu.Menu;
import restaurant.data.impl.SimpleDishDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RestaurantContextListener implements ServletContextListener {
    private static Logger log = LogManager.getLogger(RestaurantContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {

        final ServletContext context = event.getServletContext();
        final int currentYear = Year.now().getValue();
        context.setAttribute("currentYear", currentYear);

        final var menu = readFromDatabase();
        context.setAttribute("menu", menu);

        context.setAttribute("creditCardMonths", getCreditCardMonths());
        context.setAttribute("creditCardExpirationDates", getCreditCardExpirationDates(currentYear));
        context.setAttribute("creditCardTypes", getCreditCardTypes());

        context.setAttribute("customerEmail", getCustomerEmail());

        System.out.println(getCreditCardExpirationDates(currentYear));

        final int maxMenuItemsInPair = 10;
        context.setAttribute("maxUniqueItemsInPair", getMaxMenuItemsInPair(maxMenuItemsInPair));
    }

    private Menu readFromDatabase() {
        final List<Dish> menu = new SimpleDishDao().selectAll();
        return initMenu(menu);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        /**
         * No cleanup necessary yet.
         */
    }

    private Menu initMenu(List<Dish> menu) {
        return new Menu() {
            @Override
            public List<Dish> getMenu() {
                return menu;
            }

            @Override
            public Optional<Dish> getByName(String name) {
                return getMenu().stream()
                        .filter(dish -> dish.getName().equalsIgnoreCase(name))
                        .findFirst();
            }
        };
    }

    private final List<String> getCreditCardMonths() {
        return Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
                .map(i -> i.toString())
                .collect(toList());
    }

    private final List<String> getCreditCardExpirationDates(final int year) {
        return Stream.iterate(year, next -> next + 1)
                .map(intYear -> Integer.toString(intYear))
                .limit(4)
                .collect(toList());
    }

    private final List<String> getMaxMenuItemsInPair(final int max) {
        return Stream.iterate(1, i -> i <= max, i -> ++i)
                .map(i -> Integer.toString(i))
                .collect(toList());
    }

    private final List<String> getCreditCardTypes() {
        return List.of("Visa", "Master Card");
    }

    private final String getCustomerEmail() {
        return "customerQuestions@gmail.com";
    }
}