package com.github.infseclab.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
public class PasswordUpdatingDto {
    @Size(min = 8, max = 32)
    private String oldPassword;

    @Size(min = 8, max = 32)
    private String newPassword;
}
