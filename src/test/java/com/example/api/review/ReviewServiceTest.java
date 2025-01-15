package com.example.api.review;

import com.example.api.account.entity.Nationality;
import com.example.api.account.entity.UserRole;
import com.example.api.domain.Account;
import com.example.api.domain.Business;
import com.example.api.domain.Review;
import com.example.api.domain.repository.ReviewRepository;
import com.example.api.review.dto.ReviewResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ReviewServiceTest {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @BeforeEach
    void setUp() {
        reviewRepository.deleteAll();

        Business business1 = new Business();
        business1.setBusinessId(1L);
        business1.setBusinessName("Business Owner 1");
        business1.setLocation("Seoul");
        business1.setRepresentationName("Owner1");
        business1.setOpenDate(LocalDate.of(2020, 1, 1));
        business1.setRegistrationNumber("123-45-67890");

        Account employee1 = new Account();
        employee1.setAccountId(1001L);
        employee1.setLoginId("johnDoe");
        employee1.setPassword("securePassword");
        employee1.setName("John Doe");
        employee1.setNickname("johnny");
        employee1.setPhoneNumber("010-1234-5678");
        employee1.setEmail("john.doe@example.com");
        employee1.setNationality(Nationality.KOREAN);
        employee1.setRoles(List.of(UserRole.EMPLOYEE));
        employee1.setSex("M");
        employee1.setAge(30);
        employee1.setProfileImage("user-uploads/1/profile.png");
        employee1.setStarPoint(4.8f);
        employee1.setWorkCount(20);
        employee1.setOpenStatus(true);
        employee1.setDeleted(false);

        Review review1 = new Review();
        review1.setWriter(business1);
        review1.setEmployee(employee1);
        review1.setReviewStarPoint(5);
        review1.setReviewContent("Excellent work!");

        Business business2 = new Business();
        business2.setBusinessId(2L);
        business2.setBusinessName("Business Owner 2");
        business2.setLocation("Busan");
        business2.setRepresentationName("Owner2");
        business2.setOpenDate(LocalDate.of(2021, 5, 1));
        business2.setRegistrationNumber("987-65-43210");

        Account employee2 = new Account();
        employee2.setAccountId(1002L);
        employee2.setLoginId("janeSmith");
        employee2.setPassword("securePassword");
        employee2.setName("Jane Smith");
        employee2.setNickname("jane");
        employee2.setPhoneNumber("010-5678-1234");
        employee2.setEmail("jane.smith@example.com");
        employee2.setNationality(Nationality.KOREAN);
        employee2.setRoles(List.of(UserRole.EMPLOYEE));
        employee2.setSex("F");
        employee2.setAge(28);
        employee2.setProfileImage("user-uploads/2/profile.png");
        employee2.setStarPoint(4.5f);
        employee2.setWorkCount(15);
        employee2.setOpenStatus(true);
        employee2.setDeleted(false);

        Review review2 = new Review();
        review2.setWriter(business2);
        review2.setEmployee(employee2);
        review2.setReviewStarPoint(4);
        review2.setReviewContent("Good work!");

        reviewRepository.saveAll(List.of(review1, review2));
    }

    @Test
    @Order(1)
    @DisplayName("전체 리뷰 조회")
    void getAllReviews_ShouldReturnAllReviews() {
        List<ReviewResponse> reviews = reviewService.getAllReviews();
        assertNotNull(reviews);
        assertEquals(2, reviews.size());
        assertEquals("Excellent work!", reviews.get(0).reviewContent());
        assertEquals("Good work!", reviews.get(1).reviewContent());
    }

    @Test
    @Order(2)
    @DisplayName("리뷰 상세 조회")
    void getReviewById_ShouldReturnReviewDetails() {
        ReviewResponse response = reviewService.getReviewsByEmployee(1L).get(0);
        assertNotNull(response);
        assertEquals(1L, response.reviewId());
        assertEquals("Excellent work!", response.reviewContent());
    }
}
