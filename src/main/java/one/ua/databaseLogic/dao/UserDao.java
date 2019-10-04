package one.ua.databaseLogic.dao;

import one.ua.entity.User;

public interface UserDao {

    Long addUser(User user);

    User getUserByEmail(String email);

    User getUserById(Long id);

    void closeConnection();
}
