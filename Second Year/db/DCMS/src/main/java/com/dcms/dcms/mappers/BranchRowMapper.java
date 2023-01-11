package com.dcms.dcms.mappers;

import com.dcms.dcms.models.Branch;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchRowMapper implements RowMapper<Branch>{
    @Override
    public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
        Branch branch = new Branch();

        branch.setCity(rs.getString("city"));
        branch.setManager_id(rs.getInt("manager"));
        return branch;
    }
}
