package com.dcms.dcms.models;

import com.dcms.dcms.mappers.PersonRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class Patient {

    private int personID;
    private int SSN;
    private String insuranceID;
    private Person responsibleParty;

    public Patient() {

    }

    public Patient(int personID, int SSN, String insuranceID, Person responsibleParty) {

        this.personID = personID;
        this.SSN = SSN;
        this.insuranceID = insuranceID;
        this.responsibleParty = responsibleParty;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public String getInsuranceID() {
        return insuranceID;
    }

    public void setInsuranceID(String insuranceID) {
        this.insuranceID = insuranceID;
    }

    public Person getResponsibleParty() {
        return responsibleParty;
    }

    public Person getPerson(JdbcTemplate jdbcTemplate){
        String sql = String.format("SELECT * FROM person WHERE p_id=%d;", personID);
        Person person;
        try {
            person = jdbcTemplate.queryForObject(sql, new PersonRowMapper());
        } catch (Exception e){
            return null;
        }
        return person;
    }

    public void setResponsibleParty(Person responsibleParty) {
        this.responsibleParty = responsibleParty;
    }

    public String getFullNameAndID(JdbcTemplate jdbcTemplate) {
        return String.format("%d - %s", personID, getPerson(jdbcTemplate).getFullName());
    }

    /**
     * Retrieves the responsible party of the patient, if one exists, from the database.
     */
    public void setResponsibleParty(JdbcTemplate jdbcTemplate) {

        String sql = String.format("SELECT responsible_party FROM patient WHERE p_id=%d;", personID);

        Integer responsiblePartyID;

        try {
            responsiblePartyID = jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (DataAccessException e) {
            return;
        }

        sql = String.format("SELECT * from person WHERE p_id=%d;", responsiblePartyID);

        try {
            this.responsibleParty = jdbcTemplate.queryForObject(sql, new PersonRowMapper());
        } catch (DataAccessException e) {
            return;
        }
    }

    /**
     * Adds the details of the calling instance of Patient to the database if they are valid.
     */
    public void addToDatabase(JdbcTemplate jdbcTemplate) {

        Integer responsiblePartyID = (responsibleParty == null) ? null : responsibleParty.getPersonID();
        String insuranceName =
                (insuranceID == null || insuranceID.equals("")) ? null : String.format("'%s'", insuranceID);

        String sql = String.format("INSERT INTO patient VALUES (%d, '%s', %s, %d);",
                personID, SSN, insuranceName, responsiblePartyID);

        jdbcTemplate.execute(sql);
    }

    /**
     * Updates the details of the calling instance of Patient in the database.
     */
    public void updateInDatabase(JdbcTemplate jdbcTemplate) {

        Integer responsiblePartyID = (responsibleParty == null) ? null : responsibleParty.getPersonID();
        String insuranceName =
                (insuranceID == null || insuranceID.equals("")) ? null : String.format("'%s'", insuranceID);

        String sql = String.format("UPDATE patient SET ssn='%s', insurance_id=%s, "
                + "responsible_party=%d WHERE p_id=%d",
                SSN, insuranceName, responsiblePartyID, personID);

        jdbcTemplate.execute(sql);
    }
}