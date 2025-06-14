package com.SpringSecurity.SpringSecurity.service;

import com.SpringSecurity.SpringSecurity.Controller.EntryController;
import com.SpringSecurity.SpringSecurity.Entity.JournalEntity;
import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import com.SpringSecurity.SpringSecurity.JournalEntryRepository.JournalRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalRepo journalRepo;


    @Autowired
    private UserEntryService userEntryService;

    private final static Logger logger = LogManager.getLogger(JournalEntryService.class);
    // Save a journal entry
    @Transactional
    public void save(JournalEntity journalEntity, String username) {
        try {
            UserEntity user = userEntryService.findByUserName(username);
            JournalEntity saved = journalRepo.save(journalEntity);
            user.getJournalEntities().add(saved);
            userEntryService.saveUser(user);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void saveEntry(JournalEntity journalEntity) {
        journalRepo.save(journalEntity);
    }


    // Retrieve all journal entries
    public List<JournalEntity> getAll() {
        return journalRepo.findAll();
    }

    // Find a journal entry by ID
    public Optional<JournalEntity> findByID(ObjectId id) {
        return journalRepo.findById(id);
    }

    // Delete a journal entry by ID
    @Transactional
    public boolean deleteByID(ObjectId id, String username) {
        boolean removed = false;
        try {
            UserEntity user = userEntryService.findByUserName(username);
            removed = user.getJournalEntities().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userEntryService.saveUser(user);
                journalRepo.deleteById(id);
            }
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException("An error during the deletion!", e);
        }
        return removed;

    }
}
