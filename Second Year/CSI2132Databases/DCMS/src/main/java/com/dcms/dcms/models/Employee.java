package com.dcms.dcms.models;

import com.dcms.dcms.mappers.BranchRowMapper;
import com.dcms.dcms.mappers.PersonRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class Employee {

    //=== Instance Variables ===//

    private Integer personID;

    private String employer;
    private String jobTitle;
    private double salary;

    //=== Constructors ===//

    public Employee() {

    }

    public Employee(int personID, String employer, String jobTitle, double salary) {
        this.personID = personID;
        this.employer = employer;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    //=== Getters and Setters ===//

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public void addToDatabase(JdbcTemplate jdbcTemplate) {

        String sql = String.format("INSERT INTO employee VALUES (%d, '%s', '%s', %d);",
                personID, employer, jobTitle, salary);

        jdbcTemplate.execute(sql);

    }
    public void updateInDatabase(JdbcTemplate jdbcTemplate){
        String sql = String.format("UPDATE employee SET p_id='%d', employer='%s', job_title='%s', salary='%d'",
                personID, employer, jobTitle, salary);
        jdbcTemplate.execute(sql);
    }
}
