package com.shepa.services;

import com.shepa.model.Role;
import com.shepa.model.Users;
import com.shepa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userRepository.findOneByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: "+userName);
        }

        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(mapRolesToAuthorities(user.getRoles()))
                .build();
    }

    private String[] mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(Role::getName).toArray(String[]::new);
    }

}
