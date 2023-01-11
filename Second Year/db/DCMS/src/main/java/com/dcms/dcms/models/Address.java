package com.dcms.dcms.models;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;

public class Address {
    private Integer personId;
    private String houseNumber;
    private String street;
    private String city;
    private String province;

    public Address() {
    }

    public Address(Integer personId, String houseNumber, String street, String city, String province) {
        this.personId = personId;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.province = province;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    /**
     * Adds the details of the calling instance of address to the database if they are valid.
     */
    public void addToDatabase(JdbcTemplate jdbcTemplate) {

    String sql = String.format("INSERT INTO address VALUES ('%d', '%d', '%s', '%s', '%s');",
            personId, houseNumber, street, city,province);

    jdbcTemplate.execute(sql);
    }

}
