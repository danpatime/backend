package com.example.api.contracts;

import com.example.api.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ReviewRepository extends JpaRepository<Review, Long> {
}
