package com.mamg.journalApp.controller;

import com.mamg.journalApp.entity.JournalEntry;
import com.mamg.journalApp.entity.User;
import com.mamg.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create-entry")
    public ResponseEntity<User> createEntry(@RequestBody JournalEntry journalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new ResponseEntity<>(journalEntryService.saveEntry(journalEntry, username), HttpStatus.OK);
    }

    @GetMapping("/entries")
    public List<JournalEntry> getEntityById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return journalEntryService.getAllMyEntries(username);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntityById(@PathVariable ObjectId id) {
        return journalEntryService.deleteById(id);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateEntityById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntity) {
        return journalEntryService.updateJournalEntry(myId, newEntity);
    }


}

//Update tracker - updated2
//Continue From Here: https://www.youtube.com/watch?v=bphMYrTv8pA&list=PLA3GkZPtsafacdBLdd3p1DyRd5FGfr3Ue&index=27