package com.mamg.journalApp.service;

import com.mamg.journalApp.entity.JournalEntry;
import com.mamg.journalApp.entity.User;
import com.mamg.journalApp.repository.JournalEntryRepository;
import com.mamg.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User saveNewUserAdmin(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        return userRepository.save(user);
    }
    public User saveNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public ResponseEntity<?> updateUserById(User newUser, String username) {
        User user = userRepository.findByUsername(username);
        user.setUsername(newUser.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
