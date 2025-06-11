package com.SpringSecurity.SpringSecurity.Entity;


// mapping with ORm

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "journal_entries")
//@Setter
//@Getter
@Data
@RequiredArgsConstructor
public class JournalEntity {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String contents;
    private LocalDateTime date;

}
