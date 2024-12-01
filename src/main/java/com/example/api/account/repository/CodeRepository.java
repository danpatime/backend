package com.example.api.account.repository;

import com.example.api.account.domain.Code;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends MongoRepository<Code, String> {
    Optional<Code> findCodeByEmail(String email);
}
