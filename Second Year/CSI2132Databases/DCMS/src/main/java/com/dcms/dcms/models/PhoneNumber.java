package com.dcms.dcms.models;

import org.springframework.jdbc.core.JdbcTemplate;

public class PhoneNumber{
    private int p_id;
    private int phone_number;

    public PhoneNumber(int p_id,int phone_number){
        this.p_id=p_id;
        this.phone_number=phone_number;
    }
    public PhoneNumber(){

    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }


    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }


    public void addToDatabase(JdbcTemplate jdbcTemplate){
        String sql = String.format("INSERT INTO phone_number" + "phone_number"+"VALUES('%s)", phone_number);
        jdbcTemplate.execute(sql);
    }
    public void updateInDatabase(JdbcTemplate jdbcTemplate){
        String sql = String.format("UPDATE phone_number SET p_id = '%d ',phone_number='%d '", p_id, phone_number);
        jdbcTemplate.execute(sql);
    }
}

