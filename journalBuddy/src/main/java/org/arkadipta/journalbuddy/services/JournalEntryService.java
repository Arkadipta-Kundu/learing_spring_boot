package org.arkadipta.journalbuddy.services;

import org.arkadipta.journalbuddy.Repository.JournalEntryRepo;
import org.arkadipta.journalbuddy.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    public JournalEntryRepo journalEntryRepo;

    public JournalEntry saveEntry(JournalEntry journalEntry){
        return journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> showAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getEntryById(ObjectId id) {
         return journalEntryRepo.findById(id);
    }

    public void deleteEntryById(ObjectId id) {
        journalEntryRepo.deleteById(id);
    }

    public JournalEntry updateEntryById(ObjectId id, JournalEntry newEntry) {
        JournalEntry oldEntry = journalEntryRepo.findById(id).orElse(null);
        if (oldEntry != null) {
            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                oldEntry.setTitle(newEntry.getTitle());
            }
            if (newEntry.getBody() != null && !newEntry.getBody().isEmpty()) {
                oldEntry.setBody(newEntry.getBody());
            }
            if (newEntry.getDate() != null) {
                oldEntry.setDate(newEntry.getDate());
            }
            return journalEntryRepo.save(oldEntry);
        }
        return null;
    }


}
