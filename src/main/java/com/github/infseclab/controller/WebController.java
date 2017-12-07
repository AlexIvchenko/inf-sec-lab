package com.github.infseclab.controller;

import com.github.infseclab.dto.UserCreationDto;
import com.github.infseclab.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Controller
public class WebController {
    private final UserService userService;

    public WebController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signUp")
    public ModelAndView signUp() {
        return new ModelAndView("signUp", "user", new UserCreationDto());
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "signIn";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute("user") UserCreationDto dto) {
        log.info("model: {}", dto);
        userService.create(dto);
        return "redirect:signIn";
    }
}
