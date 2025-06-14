package com.SpringSecurity.SpringSecurity.Controller;

import com.SpringSecurity.SpringSecurity.Entity.JournalEntity;
import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.SpringSecurity.service.JournalEntryService;
import com.SpringSecurity.SpringSecurity.service.UserEntryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class EntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    private final static Logger logger = LogManager.getLogger(EntryController.class);

    // Get all journal entries
    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userEntryService.findByUserName(username);
        List<JournalEntity> all = user.getJournalEntities();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Create a new journal entry
    @PostMapping
    public ResponseEntity<?> createEntry(@RequestBody JournalEntity myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.save(myEntry, username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the error (use a logger in real-world apps)
            logger.error("Error creating journal entry:", e);
            //System.err.println("Error creating journal entry: " + e.getMessage());
            return new ResponseEntity<>("Failed to create journal entry", HttpStatus.BAD_REQUEST);
        }
    }


    // Get a journal entry by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntity> getEntryByID(@PathVariable("id") ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userEntryService.findByUserName(username);
        List<JournalEntity> collect = user.getJournalEntities().stream().filter(x -> x.getId().equals(id)).toList();
        if (!collect.isEmpty()) {
            Optional<JournalEntity> journalEntity = journalEntryService.findByID(id);
            if (journalEntity.isPresent()) {
                return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // Delete a journal entry by ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntryByID(@PathVariable("id") ObjectId id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalEntryService.deleteByID(id, username);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Update a journal entry by ID
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateEntryByID(@PathVariable("id") ObjectId id, @RequestBody JournalEntity newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity user = userEntryService.findByUserName(username);
        List<JournalEntity> collect = user.getJournalEntities().stream().filter(x -> x.getId().equals(id)).toList();
        if (!collect.isEmpty()) {
            Optional<JournalEntity> journalEntity = journalEntryService.findByID(id);
            if (journalEntity.isPresent()) {
                JournalEntity oldEntry = journalEntity.get();
                if (!newEntry.getTitle().isEmpty()) {
                    oldEntry.setTitle(newEntry.getTitle());
                }
                if (newEntry.getContents() != null && !newEntry.getContents().isEmpty()) {
                    oldEntry.setContents(newEntry.getContents());
                }
                journalEntryService.saveEntry(oldEntry);
                return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(collect, HttpStatus.NOT_FOUND);
    }

}
