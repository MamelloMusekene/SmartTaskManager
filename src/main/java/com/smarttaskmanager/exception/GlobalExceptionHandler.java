package com.smarttaskmanager.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public String handleEmailExists(
            EmailAlreadyExistsException ex,
            Model model) {

        model.addAttribute("error", ex.getMessage());

        return "register";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(
            Exception ex,
            Model model) {

        model.addAttribute("error",
                "Something went wrong. Please try again.");

        return "error";
    }
}