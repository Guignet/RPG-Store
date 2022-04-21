package com.project.rpgstoreback.service;

import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.repository.RoleRepository;
import com.project.rpgstoreback.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = userRepository
                .findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("username " + username + " not found"));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        account.getRoleList()
                .forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r.getName().name())));
        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                grantedAuthorities
        );
    }
}
