package com.github.infseclab.service;

import com.github.infseclab.model.File;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface FileService {
    Set<File> getUploadedFilesForAuthenticatedUser();

    File saveFileByAuthenticatedUser(String name);
}
