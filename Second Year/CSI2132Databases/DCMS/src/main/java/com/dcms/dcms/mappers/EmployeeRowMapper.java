package com.dcms.dcms.mappers;

import com.dcms.dcms.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

        Employee employee = new Employee();
        employee.setPersonID(rs.getInt("p_id"));
        employee.setEmployer(rs.getString("employer"));
        employee.setJobTitle(rs.getString("job_title"));
        employee.setSalary(rs.getDouble("salary"));
        return employee;
    }
}
