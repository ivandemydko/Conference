package databaseLogic.factory;

import databaseLogic.dao.*;
import databaseLogic.dao.impl.*;

import java.sql.Connection;

public class DaoFactory {

    public static UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public static AddressDao getAddressDao() {
        return new AddressDaoImpl();
    }

    public static AddressDao getAddressDao(Connection connection) {
        return new AddressDaoImpl(connection);
    }

    public static ReportDao getReportDao() {
        return new ReportDaoImpl();
    }

    public static RegisterDao getRegisterDao() {
        return new RegisterDaoImpl();
    }

    public static PresenceDao getPresenceDao() {
        return new PresenceDaoImpl();
    }

    public static PositionDao getPositionDao() {
        return new PositionDaoImpl();
    }
    public static SpeakerDao getSpeakerDao() {
        return new SpeakerDaoImpl();
    }

}
