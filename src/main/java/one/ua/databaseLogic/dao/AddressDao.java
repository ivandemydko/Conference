package one.ua.databaseLogic.dao;

import one.ua.entity.Address;

import java.sql.Connection;

public interface AddressDao {

    Long addAddress(String city, String street, String building, String room);

    Long getAddressId(Address address);

    Address getAddressById(Long id);

    void closeConnection();
}
