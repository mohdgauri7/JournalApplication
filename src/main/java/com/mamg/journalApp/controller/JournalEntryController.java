package com.mamg.journalApp.controller;

import com.mamg.journalApp.entity.JournalEntry;
import com.mamg.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getEntityById(@PathVariable ObjectId myId) {
        journalEntryService.findById(myId).orElse(null);
        return null;
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteEntityById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateEntityById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntity) {
        JournalEntry oldEntity = journalEntryService.findById(myId).orElse(null);
        if (oldEntity != null) {
            oldEntity.setTitle(newEntity.getTitle() != null && newEntity.getTitle().equals("") ? oldEntity.getTitle() : newEntity.getTitle());
            oldEntity.setContent(newEntity.getContent() != null && newEntity.getContent().equals("") ? oldEntity.getContent() : newEntity.getContent());
            journalEntryService.saveEntry(oldEntity);
        }
        return oldEntity;
    }


}

//Continue From Here: https://www.youtube.com/watch?v=tWBhE1Cn8D0&list=PLA3GkZPtsafacdBLdd3p1DyRd5FGfr3Ue&index=14