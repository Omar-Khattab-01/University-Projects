package com.dcms.dcms.controllers;

import com.dcms.dcms.mappers.PatientRowMapper;
import com.dcms.dcms.mappers.PersonRowMapper;
import com.dcms.dcms.models.Patient;
import com.dcms.dcms.models.Person;
import com.dcms.dcms.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class PatientController {

    /**
     * Stores temporary details about a patient.
     */
    class TemporaryPatientDetails {

        String responsiblePartyID;

        public TemporaryPatientDetails() {

        }

        public String getResponsiblePartyID() {
            return responsiblePartyID;
        }

        public void setResponsiblePartyID(String responsiblePartyID) {
            this.responsiblePartyID = responsiblePartyID;
        }
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Serves a page containing a list of all patients in the system, with options to
     * view and edit patient details.
     */
    @GetMapping("/patient/all")
    public String listPatients(Model model, HttpSession session) {

        if (!isAuthorizedUser(session, User.Role.RECEPTIONIST)
                && !isAuthorizedUser(session, User.Role.HYGIENIST))
            return "redirect:/";

        model.addAttribute("patients", getPatients());

        return "patient_list";
    }

    /**
     * Serves a form for adding a new patient to the database, which is only accessible by
     * logged-in receptionists. Other users will be redirected to the index page.
     */
    @GetMapping("/patient/add")
    public String addPatient(Model model, HttpSession session) {

        if (!isAuthorizedUser(session, User.Role.RECEPTIONIST))
            return "redirect:/";

        // Create a new Person model for storing the entered patient details
        model.addAttribute("person", new Person());
        model.addAttribute("patient", new Patient());
        model.addAttribute("insuranceOptions", getInsuranceCompanies());
        model.addAttribute("responsibleParties", getPotentialResponsibleParties());
        model.addAttribute("temp", new TemporaryPatientDetails());
        return "add_patient";
    }

    /**
     * Handles submitted patient forms. If the provided details are valid, the patient is added
     * to the database. Only receptionists can add a new patient to the database.
     */
    @PostMapping("/patient/add")
    public String addPatient(@ModelAttribute Person person, @ModelAttribute Patient patient,
                             @ModelAttribute TemporaryPatientDetails temp, Model model,
                             HttpSession session) {

        if (!isAuthorizedUser(session, User.Role.RECEPTIONIST))
            return "redirect:/";

        setResponsiblePerson(patient, temp);

        // Save patient details in database
        person.addToDatabase(jdbcTemplate);
        patient.setPersonID(person.getPersonID());
        patient.addToDatabase(jdbcTemplate);

        return "redirect:/patient/all";
    }

    /**
     * Serves a form which can be used to edit a patient's details.
     *
     * @param id The ID of the patient to edit
     */
    @GetMapping("/patient/edit")
    public String editPatient(@RequestParam(name="id") int id, Model model,
                              HttpSession session) {

        if (!isAuthorizedUser(session, User.Role.RECEPTIONIST))
            return "redirect:/";

        model.addAttribute("person", getPerson(id));
        model.addAttribute("patient", getPatient(id));
        model.addAttribute("insuranceOptions", getInsuranceCompanies());
        model.addAttribute("responsibleParties", getPotentialResponsibleParties());
        model.addAttribute("temp", new TemporaryPatientDetails());

        return "edit_patient";
    }

    /**
     * Updates the details of a patient based on the data in the submitted form.
     */
    @PostMapping("/patient/edit")
    public String editPatient(@ModelAttribute Person person, @ModelAttribute Patient patient,
                              @ModelAttribute TemporaryPatientDetails temp, Model model,
                              HttpSession session) {

        if (!isAuthorizedUser(session, User.Role.RECEPTIONIST))
            return "redirect:/";

        setResponsiblePerson(patient, temp);

        // Update patient details in database
        person.updateInDatabase(jdbcTemplate);
        patient.setPersonID(person.getPersonID());
        patient.updateInDatabase(jdbcTemplate);
        return "redirect:/patient/all";
    }

    @GetMapping("/patient/view")
    /**
     * Serves a page containing patient information.
     */
    public String viewPatient(@RequestParam(name="id") int id, Model model,
                              HttpSession session) {

        if (!isAuthorizedUser(session, User.Role.RECEPTIONIST) &&
                !isAuthorizedUser(session, User.Role.DENTIST) &&
                !isAuthorizedUser(session, User.Role.HYGIENIST) &&
                !isAuthorizedPatient(session, id)) {
            return "redirect:/";
        }

        Person person = getPerson(id);

        Patient patient = getPatient(id);
        patient.setResponsibleParty(jdbcTemplate);

        List<Map<String, Object>> records = getMedicalHistory(id);

        model.addAttribute("person", person);
        model.addAttribute("patient", patient);
        model.addAttribute("records", records);

        return "patient_records";
    }

    //=== Helper Methods ===//

    /**
     * Checks if the user is logged in.
     *
     * @return True if the user is logged in, false otherwise
     */
    public boolean isLoggedIn(HttpSession session) {
        return (session.getAttribute("USER_SESSION") != null);
    }

    public boolean isAuthorizedUser(HttpSession session, User.Role expectedRole) {

        if (!isLoggedIn(session))
            return false;

        User user = (User) session.getAttribute("USER_SESSION");
        return user.hasRole(expectedRole);
    }

    /**
     * Checks if the user is a patient whose ID matches the specified ID.
     *
     * @param session The user's session containing user data
     * @param id The ID to look for in the user's details
     *
     * @return True if the patient's ID matches the given ID, false otherwise
     */
    public boolean isAuthorizedPatient(HttpSession session, int id) {

        if (!isAuthorizedUser(session, User.Role.PATIENT))
            return false;

        User user = (User) session.getAttribute("USER_SESSION");
        return (user.getPersonID() == id);
    }

    /**
     * Gets the personal details of the person with the given ID.
     *
     * @param id The ID of the person
     *
     * @return A new instance of Person
     */
    public Person getPerson(int id) {

        String sql = String.format("SELECT * FROM person WHERE p_id=%d;", id);
        Person person = jdbcTemplate.queryForObject(sql, new PersonRowMapper());
        return person;
    }

    /**
     * Gets the details of the patient with the given ID.
     *
     * @param id The ID of the patient
     *
     * @return A new instance of Patient
     */
    public Patient getPatient(int id) {

        String sql = String.format("SELECT * FROM patient WHERE p_id=%d;", id);
        Patient patient = jdbcTemplate.queryForObject(sql, new PatientRowMapper());
        return patient;
    }

    /**
     * Produces a list of patients in the system.
     *
     * @return A list of Person instances containing patient information
     */
    public List<Person> getPatients() {

        String sql = "SELECT * FROM person NATURAL JOIN patient ORDER BY p_id ASC;";
        List<Person> patients = jdbcTemplate.query(sql, new PersonRowMapper());
        return patients;
    }

    public List<Map<String, Object>> getMedicalHistory(int patientID) {

        String sql = String.format("SELECT treatment.patient_id,appointment_date,appointment_type,procedure_code,treatment_type,medication,symptoms,tooth FROM appointment_procedure,appointment,treatment\n" +
                        "WHERE appointment_procedure.appointment_id = appointment.appointment_id AND appointment_date < now() AND treatment.procedure_id = appointment_procedure.procedure_id AND treatment.patient_id=%d",
                patientID);
        List<Map<String, Object>> medicalHistory = jdbcTemplate.queryForList(sql);
        return medicalHistory;
    }

    /**
     * Produces a list of the names of all insurance companies in the database.
     */
    public List<String> getInsuranceCompanies() {

        String sql = "SELECT insurance_id FROM insurance_company;";
        List<String> companies = jdbcTemplate.queryForList(sql, String.class);
        return companies;
    }

    public List<Person> getPotentialResponsibleParties() {

        String sql = "SELECT * FROM person potential_responsible_parties;";
        List<Person> results = jdbcTemplate.query(sql, new PersonRowMapper());

        return results;
    }

    /**
     * Sets the responsible person of the given patient if a temporary responsible party ID
     * can be found in the given list of temporary details.
     *
     * @param patient The patient whose details need to be updated
     * @param temp Stores the ID of the responsible party
     */
    public void setResponsiblePerson(Patient patient, TemporaryPatientDetails temp) {

        if (temp.getResponsiblePartyID() == null || !temp.getResponsiblePartyID().equals("")) {
            List<Person> responsibleParties = getPotentialResponsibleParties();
            Iterator<Person> iter = responsibleParties.iterator();

            while (iter.hasNext()) {
                Person p = iter.next();

                if (Integer.parseInt(temp.getResponsiblePartyID()) == p.getPersonID())
                    patient.setResponsibleParty(p);

                break;
            }
        }
    }
}
