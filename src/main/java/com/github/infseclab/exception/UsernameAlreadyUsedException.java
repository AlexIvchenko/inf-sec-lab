package com.github.infseclab.exception;

/**
 * @author Alex Ivchenko
 */
public class UsernameAlreadyUsedException extends RuntimeException {
    private static final String FORMAT = "Username %s already exists";

    public UsernameAlreadyUsedException(String existedUsername) {
        super(String.format(FORMAT, existedUsername));
    }
}
