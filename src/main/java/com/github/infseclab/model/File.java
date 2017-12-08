package com.github.infseclab.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class File {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;

    public File(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }
}
