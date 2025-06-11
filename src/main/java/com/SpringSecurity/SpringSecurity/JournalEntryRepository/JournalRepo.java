package com.SpringSecurity.SpringSecurity.JournalEntryRepository;


// the follow of our project is

// controller --> Service --> Repository

import com.SpringSecurity.SpringSecurity.Entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepo extends MongoRepository<JournalEntity, ObjectId> {




}
