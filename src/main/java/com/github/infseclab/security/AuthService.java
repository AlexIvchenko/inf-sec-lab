package com.github.infseclab.security;

import com.github.infseclab.model.User;

/**
 * @author Alex Ivchenko
 */
public interface AuthService {
    User currentUser();
}
