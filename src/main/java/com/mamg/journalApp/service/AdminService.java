package com.mamg.journalApp.service;

import com.mamg.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {


    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryService journalEntryService;

    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllJournalEntries() {
        return new ResponseEntity<>(journalEntryService.getAllEntries(), HttpStatus.OK);
    }

    public ResponseEntity<?> createUserAdmin(User user) {
        try {
            return new ResponseEntity<>(userService.saveNewUserAdmin(user), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Exception occured while creating admin: " + e);
        }

    }
}
