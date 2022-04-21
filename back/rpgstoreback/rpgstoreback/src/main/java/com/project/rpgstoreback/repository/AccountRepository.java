package com.project.rpgstoreback.repository;

import com.project.rpgstoreback.models.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);
}
