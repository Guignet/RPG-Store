package com.project.rpgstoreback.service;

import com.project.rpgstoreback.repository.RoleRepository;
import com.project.rpgstoreback.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

}
