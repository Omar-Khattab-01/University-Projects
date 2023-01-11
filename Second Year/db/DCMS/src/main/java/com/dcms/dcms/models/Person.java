package com.dcms.dcms.models;

import com.dcms.dcms.mappers.EmployeeRowMapper;
import com.dcms.dcms.mappers.PatientRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;

public class Person {

    //=== Instance Variables ===//

    private int personID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;

    //=== Roles ===//

    private Patient patientRole;
    private Employee employeeRole;

    //=== Constructors ===//

    public Person() {

    }

    public Person(int personID, String firstName, String middleName, String lastName, String gender, Date dateOfBirth,
                  Patient patientRole, Employee employeeRole) {
        this.personID = personID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.patientRole = patientRole;
        this.employeeRole = employeeRole;
    }

    //=== Getters and Setters ===//

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return String.format("%s %s %s", firstName, middleName, lastName);
    }

    public String getFullNameAndID() {
        return String.format("%d - %s", personID, getFullName());
    }

    public String getFullNameIDAndEmployer() {

        if (employeeRole == null)
            return null;

        return String.format("%d - %s [%s]", personID, getFullName(), employeeRole.getEmployer());
    }

    //=== Role Methods ===//

    public Employee getEmployeeRole() {
        return employeeRole;
    }

    /**
     * Retrieves the employment-related details of the calling instance of Person using its
     * personID from the database. If these details could be found, a new instance of Employee
     * is created, and assigned to the calling instance of Person as its employee role.
     */
    public void setEmployeeRole(JdbcTemplate jdbcTemplate) {

        String sql = String.format("SELECT * from employee WHERE p_id=%d;", personID);

        Employee employee;

        try {
            employee = jdbcTemplate.queryForObject(sql, new EmployeeRowMapper());
        } catch (DataAccessException e) {
            return;
        }

        this.employeeRole = employee;
    }

    public Patient getPatientRole() {
        return patientRole;
    }

    /**
     * Retrieves the patient-related details of the calling instance of Person using its
     * personID from the database. If these details could be found, a new instance of Patient
     * is created, and assigned to the calling instance of Person as its Patient role.
     */
    public void setPatientRole(JdbcTemplate jdbcTemplate) {

        String sql = String.format("SELECT * from patient WHERE p_id=%d;", personID);

        Patient patient;

        try {
            patient = jdbcTemplate.queryForObject(sql, new PatientRowMapper());
        } catch (DataAccessException e) {
            return;
        }

        this.patientRole = patient;
    }

    /**
     * Adds the details of the calling instance of Person to the database if they are valid.
     * If the person is successfully added to the database, the ID assigned to it will be used
     * to update the ID of the calling instance of Person.
     */
    public void addToDatabase(JdbcTemplate jdbcTemplate) {

        // Format the middle name to add to the database
        String middleName = (this.middleName == null) ? null : String.format("'%s'", this.middleName);

        // Retrieve and set the ID of the person
        String idQuery = "SELECT max(p_id) FROM person;";
        this.personID = jdbcTemplate.queryForObject(idQuery, Integer.class) + 1;

        // Add person to database
        String sql = String.format("INSERT INTO person "
                + "VALUES (%d, '%s', %s, '%s', '%s', '%s');",
                personID, firstName, middleName, lastName, gender, dateOfBirth.toString());
        jdbcTemplate.execute(sql);
    }

    /**
     * Updates the details of the calling instance of Person in the database.
     */
    public void updateInDatabase(JdbcTemplate jdbcTemplate) {

        // Format the middle name to add to the database
        String middleName = (this.middleName == null) ? null : String.format("'%s'", this.middleName);

        String sql = String.format("UPDATE person SET first_name='%s', middle_name=%s, "
                + "last_name='%s', gender='%s', date_of_birth='%s' WHERE p_id=%d;",
                firstName, middleName, lastName, gender, dateOfBirth.toString(), personID);
        jdbcTemplate.execute(sql);
    }
}
