package com.github.infseclab.service;

import com.github.infseclab.model.File;
import com.github.infseclab.model.User;
import com.github.infseclab.repository.FileRepository;
import com.github.infseclab.security.AuthService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Service
public class FileServiceImpl implements FileService {
    private final AuthService authService;
    private final FileRepository fileRepository;

    public FileServiceImpl(AuthService authService, FileRepository fileRepository) {
        this.authService = authService;
        this.fileRepository = fileRepository;
    }

    @Override
    public Set<File> getUploadedFilesForAuthenticatedUser() {
        User authenticated = authService.currentUser();
        return fileRepository.findByOwner(authenticated);
    }

    @Override
    public File saveFileByAuthenticatedUser(String name) {
        User authenticated = authService.currentUser();
        File file = new File(name, authenticated);
        return fileRepository.save(file);
    }
}
