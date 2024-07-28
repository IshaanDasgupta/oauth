package com.Bank.resourceServer.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Bank.resourceServer.entity.BankUser;

public interface BankUserRepository extends JpaRepository<BankUser, Integer> {}

