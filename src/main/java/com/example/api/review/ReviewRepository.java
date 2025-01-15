package com.example.api.review;

import com.example.api.domain.Account;
import com.example.api.domain.Contract;
import com.example.api.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReviewId(Long reviewId);

    @Query("SELECT c FROM Contract c WHERE c.offerEmployment.contract.id = :contractId")
    List<Contract> findByStoreId(@Param("storeId") Long storeId);
}
