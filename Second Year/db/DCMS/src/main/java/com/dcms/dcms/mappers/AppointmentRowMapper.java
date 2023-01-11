package com.dcms.dcms.mappers;

import com.dcms.dcms.models.Appointment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentRowMapper implements RowMapper<Appointment> {

    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(rs.getInt("appointment_id"));
        appointment.setPatientId(rs.getInt("patient_id"));
        appointment.setDentistId(rs.getInt("dentist_id"));
        appointment.setAppointmentDate(rs.getString("appointment_date"));
        appointment.setStartTime(rs.getString("start_time"));
        appointment.setEndTime(rs.getString("end_time"));
        appointment.setAppointmentType(rs.getString("appointment_type"));
        appointment.setAppointmentStatus(rs.getString("appointment_status"));
        appointment.setRoom(rs.getInt("room"));
        appointment.setAppointmentLocation(rs.getString("appointment_location"));

        return appointment;
    }
}
