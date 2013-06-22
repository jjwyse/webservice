package com.jjw.webservice.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jjw.webservice.dao.AddressBookJdbcDao;
import com.jjw.webservice.dao.rowmapper.PersonRowMapper;
import com.jjw.webservice.pojo.Person;

public class AddressBookJdbcDaoImpl implements AddressBookJdbcDao
{
    /** Logger instance. */
    Logger LOG = Logger.getLogger(AddressBookJdbcDaoImpl.class);

    /** Spring template to give us easy access to our database. */
    @Autowired
    JdbcTemplate myJdbcTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPerson(Long id, Person person)
    {
        LOG.info("Attempting to insert Person: " + person + " into database table ADDRESS_BOOK_TABLE");

        String sql = "INSERT INTO ADDRESS_BOOK_TABLE (NAME,ADDRESS,CITY,STATE,PHONE_NUMBER) VALUES (?,?,?,?,?)";
        LOG.trace(sql);

        myJdbcTemplate.update(sql, person.getName(), person.getAddress(), person.getCity(), person.getState(),
                person.getPhoneNumber());

        LOG.info("Updated database with new person.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person getPerson(Long id)
    {
        LOG.info("Attempting to look up person from address book with ID: " + id);

        String sql = "SELECT * FROM ADDRESS_BOOK_TABLE WHERE PHONE_NUMBER = ?";
        LOG.trace(sql);

        return myJdbcTemplate.queryForObject(sql, new Object[] { id }, new PersonRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePerson(Long id)
    {
        LOG.error("Not implemented.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePerson(Long id, Person person)
    {
        LOG.error("Not implemented.");
    }
}
