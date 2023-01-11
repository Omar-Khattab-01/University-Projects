package com.dcms.dcms.controllers;


import com.dcms.dcms.mappers.AppointmentRowMapper;
import com.dcms.dcms.mappers.EmployeeRowMapper;
import com.dcms.dcms.mappers.PatientRowMapper;
import com.dcms.dcms.mappers.PersonRowMapper;
import com.dcms.dcms.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller

public class AppointmentController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Serves a form to add appointment details
     * Only accessible by receptionists
     */
    @GetMapping("/appointment/add")
    public String addAppointment(Model model, HttpSession session){

        if (!isAuthorizedUser(session, User.Role.RECEPTIONIST))
            return "redirect:/";

        model.addAttribute("appointment",new Appointment());
        model.addAttribute("patients", getPatients());
        model.addAttribute("dentists", getDentists());
        model.addAttribute("appointmentTypes",getAppointmentType());
        model.addAttribute("appointmentStatus",getAppointmentStatus());
        model.addAttribute("appointmentLocations",getAppointmentLocation());
        return "add_appointment";
    }

    /**
     * Handles submitted appointment forms. If the provided details are valid, the appointment is added
     * to the database. Only receptionists can add a new appointment to the database.
     */
    @PostMapping("/appointment/add")
    public ModelAndView addAppointment(@ModelAttribute Appointment appointment,
                                       Model model, HttpServletRequest request){

        appointment.addToDatabase(jdbcTemplate);


        return new ModelAndView("redirect:/appointment/add",(Map<String, ?>) model);
    }

    /**
     *Serves a page containing appointment details
     * For receptionist, all appointments displayed
     * For dentist/hygienist, only those with corresponding dentist_id
     * For patient, only those with corresponding patient_id
     */
    @GetMapping("/appointment/all")
    public String appointmentsList(Model model, HttpSession session){

        User user = (User) session.getAttribute("USER_SESSION");


        model.addAttribute("appointments",getAppointmentList(user));

        return("appointment_list");
    }

    /**
     * Serves a page containing details about an appointment
     *
     */
    @GetMapping("/appointment/view")
    public String appointmentsList(@RequestParam(name="id") int id,Model model, HttpSession session){

        Appointment appointment = getAppointment(id);
        model.addAttribute("appointment",appointment);

        return("view_appointment");
    }

    /**
     * Returns a list of appointments from the DB depending on the user
     * returns all appointments for receptionist,
     * and appointments corresponding to dentist_id/patient_id
     */
    public List<Appointment> getAppointmentList(User user){
        int userID = user.getPersonID();
        List<Appointment> appointments = null;

        if (user.hasRole(User.Role.RECEPTIONIST)){

            String sql = "SELECT * FROM appointment;";
            appointments = jdbcTemplate.query(sql, new AppointmentRowMapper());
        } else if (user.hasRole(User.Role.DENTIST) || user.hasRole(User.Role.HYGIENIST)){

            String sql = String.format("SELECT * FROM appointment WHERE dentist_id=%d;", userID);
            appointments = jdbcTemplate.query(sql, new AppointmentRowMapper());
        } else if (user.hasRole(User.Role.PATIENT)){

            String sql = String.format("SELECT * FROM appointment WHERE patient_id=%d;", userID);
            appointments = jdbcTemplate.query(sql, new AppointmentRowMapper());
        }

        for (Appointment appointment: appointments
             ) {
            setNames(appointment);

        }
        return appointments;
    }

    /**
     * Returns an appointment given the corresponding appointment_id
     */
    public Appointment getAppointment(int id) {

        String sql = String.format("SELECT * FROM appointment WHERE appointment_id=%d;", id);
        Appointment appointment = jdbcTemplate.queryForObject(sql, new AppointmentRowMapper());
        setNames(appointment);
        return appointment;
    }

    /**
     *
     * Sets the patient name and the dentist name
     */
    public void setNames(Appointment appointment){
        String patientFirstName,patientLastName,dentistFirstName,dentistLastName,patientName,dentistName;

        String sql1 = String.format("select first_name from person where p_id=%d",appointment.getPatientId());
        patientFirstName = jdbcTemplate.queryForObject(sql1,String.class);
        String sql2 = String.format("select last_name from person where p_id=%d",appointment.getPatientId());
        patientLastName = jdbcTemplate.queryForObject(sql2,String.class);
        String sql3 = String.format("select first_name from person where p_id=%d",appointment.getDentistId());
        dentistFirstName = jdbcTemplate.queryForObject(sql3,String.class);
        String sql4 = String.format("select last_name from person where p_id=%d",appointment.getDentistId());
        dentistLastName = jdbcTemplate.queryForObject(sql4,String.class);

        patientName = String.format("%s %s", patientFirstName, patientLastName);
        dentistName = String.format("%s %s",dentistFirstName,dentistLastName);
        appointment.setPatientName(patientName);
        appointment.setDentistsName(dentistName);
    }

    /**
     * Produces a list of the names of all insurance companies in the database.
     */
    public List<String> getAppointmentType() {

        String sql = "SELECT DISTINCT appointment_type FROM appointment;";
        List<String> companies = jdbcTemplate.queryForList(sql, String.class);
        return companies;
    }

    /**
     * Produces a list of the unique appointment status in the database.
     */
    public List<String> getAppointmentStatus() {

        String sql = "SELECT DISTINCT appointment_status FROM appointment;";
        List<String> companies = jdbcTemplate.queryForList(sql, String.class);

        return companies;
    }

    /**
     * Produces a list of the unique locations status in the database.
     */
    public List<String> getAppointmentLocation() {

        String sql = "SELECT DISTINCT appointment_location FROM appointment;";
        List<String> companies = jdbcTemplate.queryForList(sql, String.class);

        return companies;
    }

    /**
     * Produces a list of the patients in the database.
     */
    public List<Person> getPatients() {

        String sql = "SELECT *\n" +
                "FROM patient\n" +
                "INNER JOIN person\n" +
                "ON person.p_id=patient.p_id";
        List<Person> results = jdbcTemplate.query(sql, new PersonRowMapper());

        return results;
    }

    /**
     * Produces a list of the dentists in the database.
     */
    public List<Person> getDentists() {

        String sql = "SELECT * FROM person WHERE p_id IN (SELECT p_id FROM employee WHERE job_title='dentist');";
        List<Person> results = jdbcTemplate.query(sql, new PersonRowMapper());

        // Add employee role for each dentist
        for (Person person : results) {
            person.setEmployeeRole(jdbcTemplate);
        }

        return results;
    }



    public boolean isLoggedIn(HttpSession session) {
        return (session.getAttribute("USER_SESSION") != null);
    }
    public boolean isAuthorizedUser(HttpSession session, User.Role expectedRole) {

        if (!isLoggedIn(session))
            return false;

        User user = (User) session.getAttribute("USER_SESSION");
        return user.hasRole(expectedRole);
    }
}
