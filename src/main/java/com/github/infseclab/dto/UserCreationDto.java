package com.github.infseclab.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString(exclude = "password")
public class UserCreationDto {
    @Size(min = 8, max = 32)
    private String username;

    @Size(min = 8, max = 32)
    private String password;
}
