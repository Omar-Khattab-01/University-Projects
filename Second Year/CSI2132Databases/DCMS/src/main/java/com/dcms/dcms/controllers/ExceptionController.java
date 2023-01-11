package com.dcms.dcms.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class ExceptionController implements ErrorController {

    /**
     * Handles any exception that occurred.
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView formError(HttpServletRequest request, Exception exception) {

        ModelAndView errorPage = new ModelAndView("redirect:/error");
        return errorPage;
    }

    @GetMapping("/error")
    public ModelAndView error(HttpServletRequest request, Model model) {

        ModelAndView errorPage = new ModelAndView("error");
        return errorPage;
    }
}
