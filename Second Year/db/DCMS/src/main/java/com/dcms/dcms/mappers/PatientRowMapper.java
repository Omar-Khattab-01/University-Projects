package com.dcms.dcms.mappers;

import com.dcms.dcms.models.Patient;
import com.dcms.dcms.models.Person;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRowMapper implements RowMapper<Patient>{


    @Override
    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
        Patient patient = new Patient();

        patient.setPersonID(rs.getInt("p_id"));
        patient.setInsuranceID(rs.getString("insurance_id"));
        patient.setSSN(rs.getInt("ssn"));

        return patient;
    }

    /**
     * returns User object that have the respective userID, returns null if ID does not exist in DB
     */
    public Person getResponsibleParty(Integer userID, JdbcTemplate jdbcTemplate) {

        Person person;
        if (userID == null)
            return null;

        String sql = String.format("SELECT * from person WHERE p_id=%d;", userID);

        try {
            person = jdbcTemplate.queryForObject(sql, new PersonRowMapper());
        } catch (DataAccessException e) {
            return null;
        }

        return person;
    }
}
