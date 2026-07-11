package com.mamg.journalApp.service;

import com.mamg.journalApp.entity.JournalEntry;
import com.mamg.journalApp.entity.User;
import com.mamg.journalApp.repository.JournalEntryRepository;
import com.mamg.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userRepository.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry save = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(save);
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(String username, ObjectId journalEntryId) {
        User user = userRepository.findByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(journalEntryId));
        journalEntryRepository.deleteById(journalEntryId);
        userRepository.save(user);
    }

    public JournalEntry updateJournalEntry(ObjectId id, JournalEntry journalEntry) {
        Optional<JournalEntry> byId = journalEntryRepository.findById(id);
        if (byId.isPresent()) {
            JournalEntry existingEntry = byId.get();
            existingEntry.setTitle(journalEntry.getTitle());
            existingEntry.setContent(journalEntry.getContent());
            return journalEntryRepository.save(existingEntry);
        }
        return journalEntry;
    }



}
