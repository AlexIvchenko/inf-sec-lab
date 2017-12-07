package com.github.infseclab.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString
@Entity
public class File {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;
}
