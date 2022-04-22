package com.project.rpgstoreback.service;

import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.models.Role;
import com.project.rpgstoreback.models.RoleEnum;
import com.project.rpgstoreback.repository.RoleRepository;
import com.project.rpgstoreback.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountService  {

    @Autowired
    private AccountRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean checkUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public Account signup(String username, String password,String firstname,String lastname,String email) {
        Role roleUser = roleRepository.findByName(RoleEnum.USER);
        List<Role> roleList = Arrays.asList(roleUser);

        Account u = new Account(firstname,lastname,username, passwordEncoder.encode(password),email, LocalDate.now(),false, roleList);
        return userRepository.save(u);
    }
}
