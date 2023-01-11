package com.dcms.dcms.models;

import com.dcms.dcms.mappers.PersonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class User {

    //=== Class Variables ===//

    public enum Role {
        PATIENT,
        RECEPTIONIST,
        DENTIST,
        HYGIENIST
    }

    //=== Constants ===//

    public final String ACCOUNT_TABLE = "user_account";

    private String email;
    private String password;
    private Integer personID;

    private Person accountHolder;

    public User(String email, String password, Integer personID, Person accountHolder) {

        this.email = email;
        this.password = password;
        this.personID = personID;
        this.accountHolder = accountHolder;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    /**
     * Retrieves the name of the user from the database.
     *
     * @param jdbcTemplate Used to interact with the database
     * @return The name of the user if it could be found, null otherwise
     */
    public String getName(JdbcTemplate jdbcTemplate) {

        if (personID == null)
            return null;

        String sql = String.format("SELECT first_name FROM person WHERE p_id=%d;", personID);
        String name = jdbcTemplate.queryForObject(sql, String.class);
        return name;
    }

    public void setAccountHolder(JdbcTemplate jdbcTemplate) {

        if (personID == null)
            return;

        String sql = String.format("SELECT * from person WHERE p_id=%d;", personID);

        Person person;

        try {
            person = jdbcTemplate.queryForObject(sql, new PersonRowMapper());
        } catch (DataAccessException e) {
            return;
        }

        this.accountHolder = person;

        // Set person roles
        person.setEmployeeRole(jdbcTemplate);
        person.setPatientRole(jdbcTemplate);
    }

    //=== Methods ===//

    /**
     * Checks if the user has the specified role.
     *
     * @param role The user role to look for
     *
     * @return True if the user has the role, false otherwise
     */
    public boolean hasRole(Role role) {

        if (role == null)
            throw new NullPointerException("Invalid user role.");

        if (role.equals(Role.PATIENT)) {
            Patient patientRole = accountHolder.getPatientRole();

            return (patientRole != null);
        }

        Employee employeeRole = accountHolder.getEmployeeRole();

        if (employeeRole == null)
            return false;

        switch (role) {
            case RECEPTIONIST:
                return employeeRole.getJobTitle().equals("receptionist");
            case DENTIST:
                return employeeRole.getJobTitle().equals("dentist");
            case HYGIENIST:
                return employeeRole.getJobTitle().equals("hygienist");
        }

        return false;
    }

    /**
     * Verifies that the user with the given email and password can be found in the
     * application's database. Upon successful validation, the personID of the user is
     * set to the value found in the database.
     *
     * @return True if the user details are valid, false otherwise
     */
    public boolean authenticate(JdbcTemplate jdbcTemplate) {

        String sql = String.format("SELECT p_id FROM %s WHERE email='%s' AND password_hash='%s';",
                ACCOUNT_TABLE, getEmail(), getPassword());

        // Ensure that there is one user with the given details
        Integer personID;

        try {
            personID = jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            personID = null;
        }

        if (personID != null) {
            this.personID = personID;

            // Get and set personal details
            setAccountHolder(jdbcTemplate);
            return true;
        } else {
            return false;
        }
    }

}
