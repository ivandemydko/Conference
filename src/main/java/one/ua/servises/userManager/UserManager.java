package one.ua.servises.userManager;



import one.ua.databaseLogic.dao.UserDao;
import one.ua.databaseLogic.factory.DaoFactory;
import one.ua.entity.User;
import one.ua.databaseLogic.transaction.PositionTransaction;
import one.ua.databaseLogic.transaction.UserTransaction;

/**
 * This class encapsulated some methods from {@link UserDao}
 */

public class UserManager {

    public User getUserByEmail(String email) {
        UserDao userDao = DaoFactory.getUserDao();
        User user = userDao.getUserByEmail(email);
        userDao.closeConnection();
        return user;
    }

    public int setUserPosition(User user, String position) {
        PositionTransaction transaction = new PositionTransaction();
        return transaction.setPositionForUser(user, position);
    }

    public Long addUser(User user) {
        UserTransaction transaction = new UserTransaction();
        return transaction.addUser(user);
    }
}
