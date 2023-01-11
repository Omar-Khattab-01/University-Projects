package com.dcms.dcms.controllers;

import com.dcms.dcms.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    //=== Instance Variables ===//

    @Autowired
    JdbcTemplate jdbcTemplate;

    //=== Request Handlers ===//

    /**
     * Serves a login form to the user. Users who are already logged in will be
     * redirected to the index page.
     */
    @GetMapping("/login")
    public String loginForm(Model model, HttpSession session) {

        // Redirect logged-in users to index
        if (session.getAttribute("USER_SESSION") != null)
            return "redirect:/index";

        model.addAttribute("user", new User());
        return "login";
    }

    /**
     * Handles the submitted login request. Users are redirected to the index page
     * if the provided account details are valid, otherwise, redirection to the
     * login page occurs.
     */
    @PostMapping("/login")
    public ModelAndView loginSubmit(@ModelAttribute User user, Model model, HttpServletRequest request) {

        model.addAttribute("user", user);

        // Check if the user's login details are valid
        if (user.authenticate(jdbcTemplate)) {
            // Create a user session, then redirect user to index
            setSessionAttributes(request.getSession(), user);
            model.addAttribute("userName",getPersonName(user.getPersonID()));
            model.addAttribute("userRole",getPersonRole(user));
            model.addAttribute("ID",getPersonID(user.getPersonID()));
            //setSessionAttributes(request.getSession(), user);

            // Set flag to false to hide failed login message
            model.addAttribute("failedLogin", false);

            // Redirect user to home page
            return new ModelAndView("home");
        } else {
            // Set flag to true to display failed login message
            model.addAttribute("failedLogin", true);

            // Delete session cookies
            request.getSession().invalidate();

            // Serves the user the login page with a failed login message
            return new ModelAndView("login", (Map<String, ?>) model);
        }
    }

    /**
     * Logs the user out by invalidating the user session. Based on
     * "Spring Boot Session Management Example (2022)" at
     * https://www.techgeeknext.com/spring-boot/spring-boot-session-management.
     *
     * @param request The request containing the user's session
     * @return A string which will redirect the user to the index page
     */
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        request.getSession().invalidate();
        return "redirect:/index";
    }

    //=== Helper Methods ===//

    /**
     * Returns a Person object containing user's information
     */
    public String getPersonName(Integer ID) {

        String sql = String.format("SELECT first_name FROM person WHERE p_id = %d",ID);
        String name = jdbcTemplate.queryForObject(sql, String.class);

        return name;
    }

    /**
     * Returns the user's role
     */
    public String getPersonRole(User user) {
        String role = "";
        if (user.hasRole(User.Role.RECEPTIONIST)){
            role = "Receptionist";
        }else if (user.hasRole(User.Role.DENTIST)){
            role = "Dentist";
        }else if (user.hasRole(User.Role.HYGIENIST)){
            role = "Hygienist";
        }else{
            role = "Patient";
        }


        return role;
    }

    /**
     * Returns a Person's ID containing user's information
     */
    public Integer getPersonID(Integer ID) {

        return ID;
    }

    /**
     * Sets the logged-in user-related attributes of the session which will determine what
     * actions the user can perform.
     */
    public void setSessionAttributes(HttpSession session, User user) {

        session.setAttribute("USER_SESSION", user);
        session.setAttribute("NAME", user.getName(jdbcTemplate));
        session.setAttribute("isReceptionist", user.hasRole(User.Role.RECEPTIONIST));
        session.setAttribute("isDentist", user.hasRole(User.Role.DENTIST));
        session.setAttribute("isHygienist", user.hasRole(User.Role.HYGIENIST));
        session.setAttribute("isPatient", user.hasRole(User.Role.PATIENT));
        session.setAttribute("PersonID", user.getPersonID());

    }
}
