package com.project.rpgstoreback.service;

import com.project.rpgstoreback.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username " + username + " not found"));

        return user;
    }
}
