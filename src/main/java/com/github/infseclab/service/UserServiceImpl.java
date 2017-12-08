package com.github.infseclab.service;

import com.github.infseclab.dto.UserCreationDto;
import com.github.infseclab.dto.PasswordUpdatingDto;
import com.github.infseclab.exception.PasswordsNotMatchException;
import com.github.infseclab.exception.UsernameAlreadyUsedException;
import com.github.infseclab.model.User;
import com.github.infseclab.repository.UserRepository;
import com.github.infseclab.security.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service
public class UserServiceImpl implements UserService {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(AuthService authService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(UserCreationDto dto) throws UsernameAlreadyUsedException {
        checkIdentity(dto);
        User user = new User(dto.getUsername(), passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateAuthorizedUserProfile(PasswordUpdatingDto dto) throws PasswordsNotMatchException {
        User user = authService.currentUser();
        if (passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            return userRepository.save(user);
        } else {
            throw new PasswordsNotMatchException();
        }
    }

    private void checkIdentity(UserCreationDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameAlreadyUsedException(dto.getUsername());
        }
    }
}
