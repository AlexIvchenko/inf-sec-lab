package com.github.infseclab.service;

import com.github.infseclab.dto.UserCreationDto;
import com.github.infseclab.dto.PasswordUpdatingDto;
import com.github.infseclab.exception.PasswordsNotMatchException;
import com.github.infseclab.exception.UsernameAlreadyUsedException;
import com.github.infseclab.model.User;

/**
 * @author Alex Ivchenko
 */
public interface UserService {
    User create(UserCreationDto dto) throws UsernameAlreadyUsedException;

    User updateAuthorizedUserProfile(PasswordUpdatingDto dto) throws PasswordsNotMatchException;
}