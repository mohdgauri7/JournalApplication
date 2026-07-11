package com.mamg.journalApp.controller;

import com.mamg.journalApp.entity.JournalEntry;
import com.mamg.journalApp.entity.User;
import com.mamg.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping("/{username}")
    public ResponseEntity<User> createEntry(@RequestBody JournalEntry journalEntry, @PathVariable String username) {
        return new ResponseEntity<>(journalEntryService.saveEntry(journalEntry, username), HttpStatus.OK);
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getEntityById(@PathVariable ObjectId myId) {
        return journalEntryService.findById(myId).orElse(null);
    }

    @DeleteMapping("/{username}/{journalEntryId}")
    public boolean deleteEntityById(@PathVariable String username, @PathVariable ObjectId journalEntryId) {
        journalEntryService.deleteById(username, journalEntryId);
        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateEntityById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntity) {
        return journalEntryService.updateJournalEntry(myId, newEntity);
    }


}

//Continue From Here: https://www.youtube.com/watch?v=HjDyv7gL4Wg&list=PLA3GkZPtsafacdBLdd3p1DyRd5FGfr3Ue&index=18