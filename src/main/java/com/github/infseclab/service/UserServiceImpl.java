package com.github.infseclab.service;

import com.github.infseclab.dto.UserCreationDto;
import com.github.infseclab.exception.UsernameAlreadyUsedException;
import com.github.infseclab.model.User;
import com.github.infseclab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User create(UserCreationDto dto) throws UsernameAlreadyUsedException {
        checkIdentity(dto);
        User user = new User(dto.getUsername(), passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(user);
    }

    private void checkIdentity(UserCreationDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameAlreadyUsedException(dto.getUsername());
        }
    }
}
