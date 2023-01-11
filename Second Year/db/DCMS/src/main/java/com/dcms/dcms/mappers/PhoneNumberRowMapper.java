package com.dcms.dcms.mappers;

import com.dcms.dcms.models.Person;
import com.dcms.dcms.models.PhoneNumber;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneNumberRowMapper implements RowMapper<PhoneNumber> {

    @Override
    public PhoneNumber mapRow(ResultSet rs, int rowNum) throws SQLException{
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setP_id(rs.getInt("p_id"));
        phoneNumber.setPhone_number(rs.getInt("phone_number"));
        return phoneNumber;
    }
}
