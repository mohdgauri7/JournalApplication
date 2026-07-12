package com.mamg.journalApp.controller;


import com.mamg.journalApp.entity.User;
import com.mamg.journalApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;



    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        return adminService.getAllUsers();
    }

    @GetMapping("/all-journal-entries")
    public ResponseEntity<?> getAllJournalEntries() {
        return adminService.getAllJournalEntries();
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        return adminService.createUserAdmin(user);
    }





}

