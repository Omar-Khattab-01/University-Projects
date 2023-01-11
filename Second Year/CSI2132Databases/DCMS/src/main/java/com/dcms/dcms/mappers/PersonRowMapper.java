package com.dcms.dcms.mappers;

import com.dcms.dcms.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class
PersonRowMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        Person person = new Person();
        person.setPersonID(rs.getInt("p_id"));
        person.setFirstName(rs.getString("first_name"));
        person.setMiddleName(rs.getString("middle_name"));
        person.setLastName(rs.getString("last_name"));
        person.setGender(rs.getString("gender"));
        person.setDateOfBirth(rs.getDate("date_of_birth"));

        return person;
    }
}
