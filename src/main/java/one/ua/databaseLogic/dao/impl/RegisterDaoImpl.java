package one.ua.databaseLogic.dao.impl;

import one.ua.databaseLogic.connection.ConnectionPool;
import one.ua.databaseLogic.dao.RegisterDao;
import one.ua.databaseLogic.dao.UserDao;
import one.ua.databaseLogic.factory.DaoFactory;
import one.ua.entity.User;
import one.ua.exceptions.DataException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterDaoImpl implements RegisterDao {
    private Logger logger = Logger.getLogger(RegisterDaoImpl.class);
    private Connection connection;

    public RegisterDaoImpl() {
        connection = ConnectionPool.getConnection();
    }

    @Override
    public int userRegister(Long userId, Long reportId) {
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement("INSERT registeredlist(reportId, userId) values (?,?)")) {
            statement.setLong(1, reportId);
            statement.setLong(2, userId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
        return result;
    }

    @Override
    public List<Long> getReportsIdByUserId(Long userId) {
        List<Long> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT reportId from registeredlist where userId =?")) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getInt("reportId");
                list.add(id);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
        return list;
    }

    @Override
    public List<User> getAllRegisteredUsers(Long reportId) {
        List<User> userList = new ArrayList<>();
        UserDao userDao = DaoFactory.getUserDao(connection);
        try (PreparedStatement statement = connection.prepareStatement("SELECT userId from registeredlist where reportId=?")) {
            statement.setLong(1, reportId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getInt("userId");
                User user = userDao.getUserById(id);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
        return userList;
    }

    @Override
    public int getCountOfVisitors(Long reportId) {
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT count(userId) as sum from registeredlist where reportId =? ");) {
            statement.setLong(1, reportId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = rs.getInt("sum");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
        return result;
    }

    @Override
    public void closeConnection() {
        ConnectionPool.closeConnection(connection);
    }
}
