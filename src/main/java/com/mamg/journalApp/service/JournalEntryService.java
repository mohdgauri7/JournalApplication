package com.mamg.journalApp.service;

import com.mamg.journalApp.entity.JournalEntry;
import com.mamg.journalApp.entity.User;
import com.mamg.journalApp.repository.JournalEntryRepository;
import com.mamg.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public List<JournalEntry> getAllMyEntries(String username) {
        return userRepository.findByUsername(username).getJournalEntries();
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    @Transactional
    public ResponseEntity<?> deleteById(ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        try {
            User user = userRepository.findByUsername(username);
            if (user.getJournalEntries().contains(id)){
                user.getJournalEntries().removeIf(x -> x.getId().equals(id));
                journalEntryRepository.deleteById(id);
                userRepository.save(user);
                return new ResponseEntity<>("Journal entry has been deleted successfully!", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("You are not authorized to delete this entry!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new RuntimeException("an error occured while deleting entry: " + e);
        }
    }

    public ResponseEntity<?> updateJournalEntry(ObjectId id, JournalEntry journalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        if (user.getJournalEntries().contains(id)){
            Optional<JournalEntry> byId = journalEntryRepository.findById(id);
            if (byId.isPresent()) {
                JournalEntry existingEntry = byId.get();
                existingEntry.setTitle(journalEntry.getTitle());
                existingEntry.setContent(journalEntry.getContent());
                return new ResponseEntity<>(journalEntryRepository.save(existingEntry), HttpStatus.OK) ;
            }else{
                return new ResponseEntity<>("Error: This entry does not found", HttpStatus.NO_CONTENT);
            }
        }else{
            return new ResponseEntity<>("Error: This entry does not belongs to you!", HttpStatus.BAD_REQUEST);
        }
    }



}
