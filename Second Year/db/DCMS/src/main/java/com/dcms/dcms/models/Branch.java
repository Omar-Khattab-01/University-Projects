package com.dcms.dcms.models;

import com.dcms.dcms.mappers.PersonRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;

public class Branch {

    private String city;
    private Integer manager_id;


    public Branch() {
    }

    public Branch(String city, Integer manager_id) {
        this.city = city;
        this.manager_id = manager_id;

    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }


    /**
     * Checks if the manager id is valid
     */

    public boolean isValidManagerID(JdbcTemplate jdbcTemplate) {

        if (manager_id == null)
            return false;

        String sql = String.format("SELECT * from person WHERE p_id=%d;", manager_id);

        Person person;

        try {
            person = jdbcTemplate.queryForObject(sql, new PersonRowMapper());
        } catch (DataAccessException e) {

            return false;
        }
        return true;

    }

    /**
     * Adds the details of the calling instance of branch to the database if they are valid.
     */
    public void addToDatabase(JdbcTemplate jdbcTemplate) {


        /*Checking if manager id is valid*/
        if(isValidManagerID(jdbcTemplate)) {
            String sql = String.format("INSERT INTO branch VALUES ('%s', '%d');",
                    city, manager_id);

            jdbcTemplate.execute(sql);
        }
    }



}
