package com.Bank.authorisationServer.repository;

import com.Bank.authorisationServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
