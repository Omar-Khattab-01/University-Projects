package com.dcms.dcms.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //=== Request Handlers ===//

    /**
     * Serves the index page to the user.
     */
    @GetMapping("/index")
    public String index(HttpSession session) {
        return "index";
    }

}
