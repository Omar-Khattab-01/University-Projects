package com.dcms.dcms.models;

import com.dcms.dcms.mappers.BranchRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.DataAccessException;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private Integer appointmentID;
    private Integer patientID;
    private Integer dentistID;
    private String appointmentDate;
    private String startTime;
    private String endTime;
    private String appointmentType;
    private String appointmentStatus;
    private Integer room;
    private String appointmentLocation;
    private String dentistsName;
    private String patientName;

    public Appointment() {

    }



    public Appointment(Integer appointmentID, Integer patientID, Integer dentistID, String appointmentDate,
                       String startTime, String endTime, String appointmentType, String appointmentStatus, Integer room,
                       String appointmentLocation) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.dentistID = dentistID;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appointmentType = appointmentType;
        this.appointmentStatus = appointmentStatus;
        this.room = room;
        this.appointmentLocation = appointmentLocation;
    }

    public Integer getAppointmentId() {
        return appointmentID;
    }

    public void setAppointmentId(Integer appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Integer getPatientId() {
        return patientID;
    }

    public void setPatientId(Integer patientID) {
        this.patientID = patientID;
    }

    public Integer getDentistId() {
        return dentistID;
    }

    public void setDentistId(Integer dentistID) {
        this.dentistID = dentistID;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public String getAppointmentLocation() {
        return appointmentLocation;
    }

    public void setAppointmentLocation(String appointmentLocation) {
        this.appointmentLocation = appointmentLocation;
    }

    public String getDentistName() {
        return dentistsName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setDentistsName(String dentistsName) {
        this.dentistsName = dentistsName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void addToDatabase(JdbcTemplate jdbcTemplate) {

        String idQuery = "SELECT max(appointment_id) FROM appointment;";
        this.appointmentID = jdbcTemplate.queryForObject(idQuery, Integer.class) + 1;

        String sql = String.format("INSERT INTO appointment " +
                        "(appointment_id,patient_id, dentist_id, appointment_date, start_time, end_time, appointment_type, appointment_status, room, appointment_location) "
                        + "VALUES ('%d','%d', '%d', '%s', '%s', '%s', '%s', '%s', '%d', '%s')",
                appointmentID,patientID, dentistID, appointmentDate.toString(), startTime.toString(), endTime.toString(), appointmentType,
                appointmentStatus, room, appointmentLocation);
        jdbcTemplate.execute(sql);

        //Retrieve and set the appointmentID

        this.appointmentID = jdbcTemplate.queryForObject(idQuery, Integer.class);
    }

    public void updateInDatabase(JdbcTemplate jdbcTemplate){
        String sql = String.format("UPDATE appointment SET dentist_id='%d', appointment_date='%s', start_time='%s', end_time='%s', appointment_type='%s', appointment_status='%s', room='%d', appointment_location='%s';",
                dentistID, appointmentDate.toString(), startTime.toString(), endTime.toString(), appointmentType, appointmentStatus,
                room, appointmentLocation );
        jdbcTemplate.execute(sql);
    }
}


