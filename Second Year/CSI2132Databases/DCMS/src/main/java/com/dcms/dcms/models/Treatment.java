package com.dcms.dcms.models;

import com.dcms.dcms.mappers.AppointmentRowMapper;
import com.dcms.dcms.mappers.PersonRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class Treatment {
    private int treatment_id;
    private int procedure_id;
    private int patient_id;
    private String treatment_type;
    private String medication;
    private String symptoms;
    private String tooth;
    public Treatment(){

    }

    public Treatment(int treatment_id, int procedure_id, int patient_id, String treatment_type, String medication, String symptoms, String tooth) {
        this.treatment_id = treatment_id;
        this.procedure_id = procedure_id;
        this.patient_id = patient_id;
        this.treatment_type = treatment_type;
        this.medication = medication;
        this.symptoms = symptoms;
        this.tooth = tooth;
    }

    public int getTreatment_id() {
        return treatment_id;
    }

    public void setTreatment_id(int treatment_id) {
        this.treatment_id = treatment_id;
    }

    public int getProcedure_id() {
        return procedure_id;
    }

    public void setProcedure_id(int procedure_id) {
        this.procedure_id = procedure_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getTreatment_type() {
        return treatment_type;
    }

    public void setTreatment_type(String treatment_type) {
        this.treatment_type = treatment_type;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTooth() {
        return tooth;
    }

    public void setTooth(String tooth) {
        this.tooth = tooth;
    }

    public void addToDatabase(JdbcTemplate jdbcTemplate) {

        String sql = String.format("INSERT INTO treatment VALUES ( %d, %d,%d,%s,,%s,%s, %s);",
               treatment_id,procedure_id,patient_id,treatment_type,medication,symptoms,tooth );

        jdbcTemplate.execute(sql);
    }
    public void updateInDatabase(JdbcTemplate jdbcTemplate){
        String sql = String.format("UPDATE treatment SET treatment_id='%d', procedure_id='%d', patient_id='%d', treatment_type='%s', medication='%s', symptoms='%s', tooth='%d'",
                treatment_id, procedure_id, patient_id, treatment_type,medication, symptoms,
                tooth );
        jdbcTemplate.execute(sql);
    }

}

