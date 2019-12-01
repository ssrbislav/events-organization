package com.ftn.eventsorganization.service.impl;

import com.ftn.eventsorganization.model.Admin;
import com.ftn.eventsorganization.model.User;
import com.ftn.eventsorganization.model.UserPrinciple;
import com.ftn.eventsorganization.model.Visitor;
import com.ftn.eventsorganization.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found -> username : " + username));

        return UserPrinciple.build(user);
    }

    public String getUsername() {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass() == String.class) {
            return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        UserPrinciple user = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
