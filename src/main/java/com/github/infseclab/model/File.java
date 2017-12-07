package com.github.infseclab.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString
public class File {
    private Long id;
    private String name;
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;
}
