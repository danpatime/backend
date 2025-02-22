package com.example.api.aws.service;

import com.example.api.account.entity.Nationality;
import com.example.api.account.entity.UserRole;
import com.example.api.account.repository.AccountRepository;
import com.example.api.aws.dto.UploadProfileRequest;
import com.example.api.aws.dto.UploadProfileResponse;
import com.example.api.domain.Account;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class S3ServiceTest {
    @Autowired
    private S3Service s3Service;
    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    void setUp() {
        accountRepository.deleteAll();
        Account account = new Account(
                25,                // age
                false,                 // deleted
                "alice@example.com",   // email
                "user01",              // loginId
                "Alice",               // name
                Nationality.KOREAN,    // nationality
                "nickname1",           // nickname
                true,                  // openStatus
                "pass01",              // password
                "010-1234-5678",       // phoneNumber
                "user-uploads/1/profile.png",        // profileImage
                List.of(UserRole.ROLE_EMPLOYEE), // roles
                "F",                   // sex
                4.5f,                  // starPoint
                10                     // workCount
        );
        accountRepository.save(account);
    }

    @Test
    @Order(1)
    @DisplayName("업로드 파일이 null일 경우 기본 프로필로 초기화")
    void uploadProfileImage_NullFile_ShouldInitializeToDefaultImage() {
        MultipartFile file = null;

        UploadProfileRequest request = new UploadProfileRequest(1L, file);

        UploadProfileResponse response = s3Service.upload(request);
        String newFile = accountRepository.findProfileImageByAccountId(1L).orElse(null);
        assertNull(response.path());
        assertNull(newFile); // null로 업데이트 되었는지 확인
    }

    @Test
    @Order(2)
    @DisplayName("정상 업로드 성공")
    void upload_ShouldUploadFileSuccessfully() throws IOException {
        ClassPathResource resource = new ClassPathResource("test-files/test-image.png");
        MultipartFile file = new MockMultipartFile(
                "file",
                resource.getFilename(),
                "image/png",
                resource.getInputStream()
        );

        UploadProfileRequest request = new UploadProfileRequest(1L, file);

        UploadProfileResponse response = s3Service.upload(request);
        String newFile = accountRepository.findProfileImageByAccountId(1L).orElse(null);

        assertNotNull(response);
        assertEquals("https://danpat.s3.ap-northeast-2.amazonaws.com/user-uploads/1/profile.png", response.path());
        assertNotEquals("oldProfile", newFile); // 새로운 파일 이름으로 업데이트 되었는지 확인
    }
}