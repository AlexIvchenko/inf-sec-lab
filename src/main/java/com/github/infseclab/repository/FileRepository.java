package com.github.infseclab.repository;

import com.github.infseclab.model.File;
import com.github.infseclab.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface FileRepository extends CrudRepository<File, Long> {
    Set<File> findByOwner(User owner);
}
