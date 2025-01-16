package com.example.api.aws.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.api.account.repository.AccountRepository;
import com.example.api.aws.dto.S3UploadRequest;
import com.example.api.aws.dto.OldKeyRequest;
import com.example.api.aws.dto.UploadProfileRequest;
import com.example.api.aws.dto.UploadProfileResponse;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import com.example.api.global.config.AmazonConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Service {
    private final AmazonS3 amazonS3;
    private final AmazonConfig amazonConfig;
    private final AccountRepository accountRepository;

    @Transactional
    public UploadProfileResponse upload(@Validated final UploadProfileRequest request) {
        // 업로드 파일이 null 이라면 기본 프로필로 초기화
        if (initDefaultIfFileIsNull(request)) return new UploadProfileResponse(null);

        Optional<String> userProfile = accountRepository.findProfileImageByAccountId(request.userId());
        userProfile.ifPresent(oldKey -> remove(new OldKeyRequest(oldKey)));

        String key = generateFileName(request);
        String path = uploadToS3(new S3UploadRequest(request.multipartFile(), key));
        accountRepository.updateProfileImageByAccountId(key, request.userId());     // S3 업로드 이후 사용자 테이블 프로필 값 업데이트

        return new UploadProfileResponse(path);
    }

    private boolean initDefaultIfFileIsNull(final UploadProfileRequest request) {
        if (request.multipartFile() == null || request.multipartFile().isEmpty()) {
            accountRepository.updateProfileImageByAccountId(null, request.userId());
            String oldKey = "user-uploads/" + request.userId() + "/profile.png";
            remove(new OldKeyRequest(oldKey));
            return true;
        }
        return false;
    }

    private String generateFileName(final UploadProfileRequest request) {
        String contentType = request.multipartFile().getContentType();
        String fileExtension = contentType != null && contentType.contains("/")
                ? "." + contentType.split("/")[1]
                : ".png";
        return String.format("user-uploads/%d/profile%s", request.userId(), fileExtension);
    }

    private String uploadToS3(final S3UploadRequest s3UploadRequest) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(s3UploadRequest.multipartFile().getSize());
            metadata.setContentType(s3UploadRequest.multipartFile().getContentType());

            amazonS3.putObject(
                    new PutObjectRequest(
                            amazonConfig.getBucket(),
                            s3UploadRequest.key(),
                            s3UploadRequest.multipartFile().getInputStream(),
                            metadata
                    )
            );

            return amazonS3.getUrl(amazonConfig.getBucket(), s3UploadRequest.key()).toString();

        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILED);
        }
    }

    public void remove(final OldKeyRequest request) {
        if (!amazonS3.doesObjectExist(amazonConfig.getBucket(), request.oldKey())) {
            throw new AmazonS3Exception("Object " + request.oldKey() + " does not exist!");
        }
        amazonS3.deleteObject(amazonConfig.getBucket(), request.oldKey());
    }
}