package com.github.infseclab.controller;

import com.github.infseclab.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Controller
@RequestMapping(SignInController.VIEW_NAME)
public class SignInController {
    public final static String VIEW_NAME = "signIn";

    private final UserService userService;

    public SignInController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signIn() {
        return VIEW_NAME;
    }
}
