package com.example.api.inquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByCreatedByAccountId(Long accountId);
}
