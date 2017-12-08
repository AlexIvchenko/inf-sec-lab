package com.github.infseclab.controller;

import com.github.infseclab.model.File;
import com.github.infseclab.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Controller
@RequestMapping({"/", FileController.VIEW_NAME})
public class FileController {
    public static final String VIEW_NAME = "files";

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ModelAndView getFilesView() {
        Set<File> files = fileService.getUploadedFilesForAuthenticatedUser();
        ModelAndView ret = new ModelAndView(VIEW_NAME);
        ret.addObject("files", files);
        return ret;
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String name = file.getOriginalFilename();
        log.info("uploading file {}" + name);
        fileService.saveFileByAuthenticatedUser(name);
        return "redirect:" + VIEW_NAME;
    }
}
