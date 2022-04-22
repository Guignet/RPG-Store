package com.project.rpgstoreback.repository;

import com.project.rpgstoreback.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Boolean existsByUsername(String username);
}
