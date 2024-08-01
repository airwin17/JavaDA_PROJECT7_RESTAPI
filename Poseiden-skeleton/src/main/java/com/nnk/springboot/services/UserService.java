package com.nnk.springboot.services;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.RequestedObjectNotFoundException;
import com.nnk.springboot.repositories.UserRepository;

import jakarta.transaction.Transactional;



public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository,User user) {
        this.userRepository = userRepository;
        save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findById(Integer id) throws RequestedObjectNotFoundException{
        return userRepository.findById(id).orElseThrow(()-> new RequestedObjectNotFoundException("User not found with id: " + id));
    }
    @Transactional
    public void delete(int user) {
        userRepository.deleteByuserid(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
