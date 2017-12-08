package com.github.infseclab.controller;

import com.github.infseclab.dto.PasswordUpdatingDto;
import com.github.infseclab.exception.PasswordsNotMatchException;
import com.github.infseclab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author Alex Ivchenko
 */
@Controller
@RequestMapping(ProfileController.VIEW_NAME)
public class ProfileController {
    public static final String VIEW_NAME = "profile";

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView profile() {
        return new ModelAndView(VIEW_NAME, "passwords", new PasswordUpdatingDto());
    }

    @PostMapping
    public String updatePassword(@ModelAttribute("passwords") @Valid PasswordUpdatingDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return VIEW_NAME;
        }

        try {
            userService.updateAuthorizedUserProfile(dto);
        } catch (PasswordsNotMatchException e) {
            result.addError(new FieldError("passwords", "oldPassword", "password doesn't match"));
            return VIEW_NAME;
        }

        return "redirect:" + VIEW_NAME;
    }
}
