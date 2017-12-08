package com.github.infseclab.controller;

import com.github.infseclab.dto.UserCreationDto;
import com.github.infseclab.exception.UsernameAlreadyUsedException;
import com.github.infseclab.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Controller
@RequestMapping(SignUpController.VIEW_NAME)
public class SignUpController {
    public final static String VIEW_NAME = "signUp";

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView signUp() {
        return new ModelAndView(VIEW_NAME, "user", new UserCreationDto());
    }

    @PostMapping
    public String signUp(@ModelAttribute("user") @Valid UserCreationDto dto, BindingResult result) {
        log.info("model: {}", dto);

        if (result.hasErrors()) {
            return VIEW_NAME;
        }

        try {
            userService.create(dto);
        } catch (UsernameAlreadyUsedException e) {
            result.addError(new FieldError("user", "username", e.getMessage()));
            return VIEW_NAME;
        }

        return redirect(SignInController.VIEW_NAME);
    }

    private String redirect(String viewName) {
        return String.format("redirect:%s", viewName);
    }
}
