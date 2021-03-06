package one.ua.databaseLogic.factory;

import one.ua.databaseLogic.dao.*;
import one.ua.databaseLogic.dao.impl.*;

import java.sql.Connection;

public class DaoFactory {

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static UserDao getUserDao(Connection connection) {
        return new UserDaoImpl(connection);
    }

    public static AddressDao getAddressDao(Connection connection) {
        return new AddressDaoImpl(connection);
    }

    public static ReportDao getReportDao() {
        return new ReportDaoImpl();
    }

    public static ReportDao getReportDao(Connection connection) {
        return new ReportDaoImpl(connection);
    }

    public static RegisterDao getRegisterDao() {
        return new RegisterDaoImpl();
    }

    public static PresenceDao getPresenceDao() {
        return new PresenceDaoImpl();
    }

    public static PositionDao getPositionDao(Connection connection) {
        return new PositionDaoImpl(connection);
    }

    public static SpeakerDao getSpeakerDao() {
        return new SpeakerDaoImpl();
    }

    public static SpeakerDao getSpeakerDao(Connection connection) {
        return new SpeakerDaoImpl(connection);
    }

    public static LanguageDao getLanguageDao() {
        return new LanguageDaoImpl();
    }

    public static LanguageDao getLanguageDao(Connection connection) {
        return new LanguageDaoImpl(connection);
    }

}
