package com.xenby.demo.repository;

import com.xenby.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    public User findByUsername(String username);
}
