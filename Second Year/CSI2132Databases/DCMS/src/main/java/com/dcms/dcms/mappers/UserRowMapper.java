package com.dcms.dcms.mappers;

import com.dcms.dcms.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password_hash"));
        user.setPersonID(rs.getInt("p_id"));

        return user;
    }
}
