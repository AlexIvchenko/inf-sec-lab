package com.github.infseclab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Alex Ivchenko
 */
@Controller()
@RequestMapping(ErrorController.VIEW_NAME)
public class ErrorController {
    public static final String VIEW_NAME = "error";

    @GetMapping
    public String error() {
        return VIEW_NAME;
    }
}
