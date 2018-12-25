package restaurant.data;

import restaurant.business.User;

public interface UserDao {
    boolean emailExists(String email);
    boolean deleteUser(String email);
    boolean insert(User user);
    User selectUser(String email);
    boolean updateUser(User user);

    boolean subscribeUser(String email);
    boolean unsubscribeUser(String email);
}
