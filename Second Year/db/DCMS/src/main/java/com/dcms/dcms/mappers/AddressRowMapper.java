package com.dcms.dcms.mappers;

import com.dcms.dcms.models.Address;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRowMapper implements RowMapper<Address>{
    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        Address address = new Address();
        address.setPersonId(rs.getInt("p_id"));
        address.setHouseNumber(rs.getString("house_number"));
        address.setStreet(rs.getString("street"));
        address.setStreet(rs.getString("city"));
        address.setProvince(rs.getString("province"));
        return address;
    }
}
