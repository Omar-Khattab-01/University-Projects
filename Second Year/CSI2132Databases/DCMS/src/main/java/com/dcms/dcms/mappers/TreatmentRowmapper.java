package com.dcms.dcms.mappers;

import com.dcms.dcms.models.Person;
import com.dcms.dcms.models.Treatment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TreatmentRowmapper implements RowMapper<Treatment> {

    @Override
    public Treatment mapRow(ResultSet rs, int rowNum) throws SQLException{
        Treatment treatment = new Treatment();
        treatment.setTreatment_id(rs.getInt("treatment_id"));
        treatment.setPatient_id(rs.getInt("patient_id"));
        treatment.setProcedure_id(rs.getInt("procedure_id"));
        treatment.setTreatment_type(rs.getString("treatment_type"));
        treatment.setMedication(rs.getString("medication"));
        treatment.setSymptoms(rs.getString("symptoms"));
        treatment.setTooth(rs.getString("tooth"));

        return treatment;
    }

}
