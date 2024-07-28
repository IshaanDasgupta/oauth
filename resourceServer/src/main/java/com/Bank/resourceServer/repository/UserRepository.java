package com.Bank.resourceServer.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Bank.resourceServer.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
