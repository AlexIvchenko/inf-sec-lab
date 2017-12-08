package com.github.infseclab.repository;

import com.github.infseclab.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alex Ivchenko
 */
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);

    User findByUsername(String username);
}
