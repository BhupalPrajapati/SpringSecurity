package com.SpringSecurity.SpringSecurity.JournalEntryRepository;


// the follow of our project is

// controller --> Service --> Repository

import com.SpringSecurity.SpringSecurity.Entity.JournalEntity;
import com.SpringSecurity.SpringSecurity.Entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<UserEntity, ObjectId> {

    Optional<UserEntity> findByUsername(String username);





}
