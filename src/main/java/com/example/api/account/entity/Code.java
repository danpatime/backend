package com.example.api.account.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Document(collection = "code")
public class Code {
    @Id
    private String id;
    private String email;
    private String code;

    @Indexed(expireAfterSeconds = 600)
    private final Date createdAt;

    public Code(String email, String code) {
        this.email = email;
        this.code = code;
        this.createdAt = new Date();
    }
}
