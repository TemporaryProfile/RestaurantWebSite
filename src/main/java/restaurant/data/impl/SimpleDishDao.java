package restaurant.data.impl;

import restaurant.business.menu.Dish;
import restaurant.data.DishDao;
import restaurant.data.HelperDB;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleDishDao implements DishDao {
    @Override
    public Dish selectDish(int dishId) {
        final String query = "select * from Dish where DishID = ?";
        try (var con = HelperDB.getConnection();
             var prepStat = con.prepareStatement(query)) {
            prepStat.setInt(1, dishId);

            try (var resSet = prepStat.executeQuery()) {
                while (resSet.next()) {
                    final String id = resSet.getString(1);
                    final String name = resSet.getString(2);
                    final String calories = resSet.getString(3);
                    final String type = resSet.getString(4);
                    final boolean isVegeterian = resSet.getString(5).equalsIgnoreCase("YES");
                    final BigDecimal price = new BigDecimal(resSet.getString(6));

                    return new Dish(Integer.parseInt(id), name, Integer.parseInt(calories), Dish.Type.valueOf(type), isVegeterian, price);
//                return HelperDB.mapDish(resSet);
                }
            }
        } catch (SQLException ex) {
            System.err.println();
        }
        return new Dish();
    }

    @Override
    public List<Dish> selectAll() {
        final List<Dish> menu = new ArrayList<>();
        final String query = "select * from Dish";
        try (var con = HelperDB.getConnection();
             var stat = con.createStatement();
             var resSet = stat.executeQuery(query)) {

            System.out.println("selectAll");

            while (resSet.next()) {
                Dish dish = HelperDB.mapDish(resSet);
                System.out.println(dish);
                menu.add(dish);
            }
        } catch (SQLException ex) {
            System.err.println();
        }
        return menu;
    }
}
