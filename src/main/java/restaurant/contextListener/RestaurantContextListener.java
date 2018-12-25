package restaurant.contextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import restaurant.business.menu.Dish;
import restaurant.business.menu.Menu;
import restaurant.data.HelperDB;
import restaurant.data.impl.SimpleDishDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.math.BigDecimal;
import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class RestaurantContextListener implements ServletContextListener {
    private static Logger log = LogManager.getLogger(RestaurantContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.
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

//        final List<Dish> menu = new ArrayList<>();

//        final String query = "select * from Dish";
//        try (var con = HelperDB.getConnection();
//             var stat = con.createStatement();
//             var resSet = stat.executeQuery(query)) {
//
//            while (resSet.next()) {
//                final String id = resSet.getString(1);
//                final String name = resSet.getString(2);
//                final String calories = resSet.getString(3);
//                final String type = resSet.getString(4);
//                final boolean isVegeterian = resSet.getString(5).equalsIgnoreCase("YES");
//                final BigDecimal price = new BigDecimal(resSet.getString(6));
//
//                menu.add(new Dish(Integer.parseInt(id), name, Integer.parseInt(calories), Dish.Type.valueOf(type), isVegeterian, price));
//            }
//        } catch (SQLException ex) {
//            System.err.println();
//        }
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            final var dbURL = "jdbc:mysql://localhost:3306/restaurantMenu";
//            String username = "restaurantMenu_user";
//            String password = "sesame";
//            final String sqlStatement = "select * from Dish";
//
//            try (Connection connection = DriverManager.getConnection(dbURL, username, password);
//                 Statement statement = connection.createStatement();
//                 ResultSet resultSet = statement.executeQuery(sqlStatement)) {
//                    while (resultSet.next()) {
//                        final String id = resultSet.getString(1);
//                        System.out.println("id: " + id);
//                        final String name = resultSet.getString(2);
//                        System.out.println("name " + name);
//                        final String calories = resultSet.getString(3);
//                        System.out.println("cal " + calories);
//                        final String type = resultSet.getString(4);
//                        System.out.println("type " + type);
//                        final boolean isVegeterian = resultSet.getString(5).equalsIgnoreCase("YES");
//                        System.out.println("veg " + isVegeterian);
//                        final BigDecimal price = new BigDecimal(resultSet.getString(6));
//                        System.out.println("pr " + price);
//                        menu.add(new Dish(Integer.parseInt(id), name, Integer.parseInt(calories), Dish.Type.valueOf(type), isVegeterian, price));
//                    }
//                System.out.println(menu);
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            System.err.println("error in delete(email)");
//        }
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