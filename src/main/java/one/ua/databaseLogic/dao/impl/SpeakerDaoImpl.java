package one.ua.databaseLogic.dao.impl;

import one.ua.databaseLogic.connection.ConnectionPool;
import one.ua.databaseLogic.dao.LanguageDao;
import one.ua.databaseLogic.dao.PositionDao;
import one.ua.databaseLogic.dao.SpeakerDao;
import one.ua.databaseLogic.factory.DaoFactory;
import one.ua.entity.Speaker;
import one.ua.exceptions.DataException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpeakerDaoImpl implements SpeakerDao {
    private Logger logger = Logger.getLogger(SpeakerDaoImpl.class);
    private Connection connection;

    public SpeakerDaoImpl() {
        connection = ConnectionPool.getConnection();
    }

    public SpeakerDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Speaker getSpeakerById(Long id) {
        Speaker speaker = null;
        PositionDao positionDao = DaoFactory.getPositionDao(connection);
        LanguageDao languageDao = DaoFactory.getLanguageDao(connection);
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, name, surname, email, password, position,language,rating " +
                "FROM users u join speakerratings s on u.id=s.speakerId WHERE id=? AND position=?")) {
            statement.setLong(1, id);
            statement.setInt(2, positionDao.getPositionId("Speaker"));
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                speaker = new Speaker();
                speaker.setId(id);
                speaker.setName(rs.getString("name"));
                speaker.setSurname(rs.getString("surname"));
                speaker.setEmail(rs.getString("email"));
                speaker.setPassword("password");
                speaker.setPosition("Speaker");
                speaker.setLanguage(languageDao.getLanguageById(rs.getInt("language")));
                speaker.setRating(rs.getInt("rating"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
        return speaker;
    }

    @Override
    public Speaker getSpeakerByEmail(String email) {
        Speaker speaker = null;
        PositionDao positionDao = DaoFactory.getPositionDao(connection);
        LanguageDao languageDao = DaoFactory.getLanguageDao(connection);
        try (PreparedStatement statement = connection.prepareStatement("SELECT id,name,surname,password,language,rating " +
                "from users u join speakerratings s " +
                "on u.id = s.speakerId where position=? and email=?")) {
            int position = positionDao.getPositionId("Speaker");
            statement.setInt(1, position);
            statement.setString(2, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                speaker = new Speaker();
                speaker.setId(rs.getLong("id"));
                speaker.setName(rs.getString("name"));
                speaker.setSurname(rs.getString("surname"));
                speaker.setPassword(rs.getString("password"));
                speaker.setLanguage(languageDao.getLanguageById(rs.getInt("language")));
                speaker.setRating(rs.getInt("rating"));
                speaker.setEmail(email);
                speaker.setPosition("Speaker");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
        return speaker;
    }

    @Override
    public void deleteSpeaker(Long speakerId) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE from speakerratings where speakerId=?")) {
            statement.setLong(1, speakerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
    }

    @Override
    public int addSpeakerRating(Speaker speaker, int rating) {
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE speakerratings SET rating=? WHERE speakerId=?")) {
            statement.setInt(1, rating);
            statement.setLong(2, speaker.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
        return result;
    }

    @Override
    public int addBonusesToSpeaker(Speaker speaker, int bonuses) {
        int result = 0;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE speakerratings " +
                "set bonuses=? where speakerId=?")) {
            statement.setInt(1, getSpeakerBonuses(speaker) + bonuses);
            statement.setLong(2, speaker.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
        return result;
    }

    @Override
    public int getSpeakerBonuses(Speaker speaker) {
        int bonuses = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT bonuses " +
                "FROM speakerratings where speakerId=?")) {
            statement.setLong(1, speaker.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                bonuses = rs.getInt("bonuses");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
        return bonuses;
    }

    @Override
    public void addSpeaker(Long speakerId) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT speakerratings(speakerId) values (?)")) {
            statement.setLong(1, speakerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DataException(e);
        }
    }


    @Override
    public void closeConnection() {
        ConnectionPool.closeConnection(connection);
    }
}
