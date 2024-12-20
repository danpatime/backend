package com.example.api.account.repository;

import com.example.api.account.entity.Code;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends MongoRepository<Code, String> {
    Optional<Code> findFirstByEmailOrderByCreatedAtDesc(String email);
}