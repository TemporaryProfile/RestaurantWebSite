package restaurant.data;

import restaurant.business.User;
import restaurant.business.menu.Dish;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelperDB {
    public static Connection getConnection() {
        return ConnectionPool.getInstance().getConnection();
    }

    public static User mapUser(ResultSet rs) {
        User user = null;
        try {
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("UserID"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
            }
        } catch(SQLException ex) {
            System.err.println("mapUser: " + ex);
        }
        return user;
    }

    public static Dish mapDish(ResultSet resSet) throws SQLException {
        final String id = resSet.getString(1);
        final String name = resSet.getString(2);
        final String calories = resSet.getString(3);
        final String type = resSet.getString(4);
        final boolean isVegeterian = resSet.getString(5).equalsIgnoreCase("YES");
        final BigDecimal price = new BigDecimal(resSet.getString(6));

        return new Dish(Integer.parseInt(id), name, Integer.parseInt(calories), Dish.Type.valueOf(type), isVegeterian, price);
    }

    public static Long getId() {
        final String identityQuery = "SELECT @@IDENTITY AS IDENTITY";

        try (var identityStatement = HelperDB.getConnection().createStatement();
             var identityResultSet = identityStatement.executeQuery(identityQuery)) {

            identityResultSet.next();
            return identityResultSet.getLong("IDENTITY");
        } catch (SQLException ex) {
            System.err.println("getId HelperDB: " + ex);
            return 0L;
        }
    }
}
