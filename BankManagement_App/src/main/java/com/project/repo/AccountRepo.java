package com.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{

}
