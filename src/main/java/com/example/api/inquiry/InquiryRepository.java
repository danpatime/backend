package com.example.api.inquiry;

import com.example.api.domain.Account;
import com.example.api.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    List<Inquiry> findByCreatedByAccountId(Long accountId);
}
