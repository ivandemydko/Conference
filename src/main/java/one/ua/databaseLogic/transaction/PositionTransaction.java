package one.ua.databaseLogic.transaction;

import one.ua.databaseLogic.connection.ConnectionPool;
import one.ua.databaseLogic.dao.PositionDao;
import one.ua.databaseLogic.dao.SpeakerDao;
import one.ua.databaseLogic.factory.DaoFactory;
import one.ua.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is used for java.one.ua.databaseLogic.transaction.
 */
public class PositionTransaction {
    private Logger logger = Logger.getLogger(PositionTransaction.class);

    public int setPositionForUser(User user, String position) {
        int result = 0;
        if (user.getPosition().equals(position)) {
            return result;
        }
        Connection connection = ConnectionPool.getConnection();
        SpeakerDao speakerDao = DaoFactory.getSpeakerDao(connection);
        PositionDao positionDao = DaoFactory.getPositionDao(connection);
        try {
            connection.setAutoCommit(false);
            if (user.getPosition().equals("Speaker")) {
                speakerDao.deleteSpeaker(user.getId());
            }
            result = positionDao.setPositionForUser(user, position);
            if (position.equals("Speaker")) {
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
        }finally {
            ConnectionPool.closeConnection(connection);
        }
        return result;
    }
}
