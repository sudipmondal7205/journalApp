package net.digest.journalApp.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.digest.journalApp.entity.JournalEntry;
import net.digest.journalApp.entity.User;
import net.digest.journalApp.repository.JournalEntryRepository;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Component
public class JournalEntryService {
    
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userServices;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        User user = userServices.findByUserName(username);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userServices.saveUser(user);
    }
    
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    // @Transactional
    public boolean deleteJournalEntry(String username, ObjectId id){
        boolean removed = false;
        try{
            User user = userServices.findByUserName(username);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userServices.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("An error occured while deleting the entry", e);
        }
        return removed;
    }  
}
