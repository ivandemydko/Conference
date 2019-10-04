package one.ua.databaseLogic.transaction;

import one.ua.databaseLogic.connection.ConnectionPool;
import one.ua.databaseLogic.dao.PositionDao;
import one.ua.databaseLogic.dao.SpeakerDao;
import one.ua.databaseLogic.dao.UserDao;
import one.ua.databaseLogic.factory.DaoFactory;
import one.ua.entity.User;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is used for java.one.ua.databaseLogic.transaction.
 */
public class UserTransaction {
    private Logger logger = Logger.getLogger(UserTransaction.class);

    public Long addUser(User user) {
        Connection connection = ConnectionPool.getConnection();
        UserDao userDao = DaoFactory.getUserDao(connection);
        PositionDao positionDao = DaoFactory.getPositionDao(connection);
        SpeakerDao speakerDao = DaoFactory.getSpeakerDao(connection);
        Long id = null;
        try {
            connection.setAutoCommit(false);
            id = userDao.addUser(user);
            user.setId(id);
            positionDao.setPositionForUser(user, user.getPosition());
            if (user.getPosition().equals("Speaker")) {
                speakerDao.addSpeaker(user.getId());
            }
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex);
            }
        } finally {
            ConnectionPool.closeConnection(connection);
        }
        return id;
    }
}
