package com.github.infseclab.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString(exclude = "password")
public class UserCreationDto {
    private String username;
    private String password;
}
