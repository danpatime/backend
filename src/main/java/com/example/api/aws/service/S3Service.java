package com.example.api.aws.entity;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.api.account.repository.AccountRepository;
import com.example.api.aws.dto.AwsS3;
import com.example.api.aws.dto.UploadProfileRequest;
import com.example.api.exception.BusinessException;
import com.example.api.exception.ErrorCode;
import com.example.api.global.config.AmazonConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AwsUtils {
    private final AmazonS3 amazonS3;
    private final AmazonConfig amazonConfig;
    private final AccountRepository accountRepository;

    @Transactional
    public AwsS3 upload(@Validated UploadProfileRequest request) {
        if (request.multipartFile() == null || request.multipartFile().isEmpty()) {
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILED);
        }

        Optional<String> userProfile = accountRepository.findProfileImageByAccountId(request.userId());
        userProfile.ifPresent(oldKey -> remove(new AwsS3(oldKey, null)));

        String key = generateFileName(request.userId(), request.multipartFile());
        String path = uploadToS3(request.multipartFile(), key);
        accountRepository.updateProfileImageByAccountId(key, request.userId());

        return new AwsS3(key, path);
    }

    private String generateFileName(Long userId, MultipartFile file) {
        String contentType = file.getContentType();
        String fileExtension = contentType != null && contentType.contains("/")
                ? "." + contentType.split("/")[1]
                : ".png";
        return String.format("user-profile/%d/profile%s", userId, fileExtension);
    }

    private String uploadToS3(MultipartFile uploadFile, String fileName) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(uploadFile.getSize());
            metadata.setContentType(uploadFile.getContentType());

            amazonS3.putObject(
                    new PutObjectRequest(
                            amazonConfig.getBucket(),
                            fileName,
                            uploadFile.getInputStream(),
                            metadata
                    )
            );
            return getS3Url(amazonConfig.getBucket(), fileName);

        } catch (IOException e) {
            throw new BusinessException(ErrorCode.FILE_UPLOAD_FAILED);
        }
    }

    private String getS3Url(String bucket, String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }

    public void remove(AwsS3 awsS3) {
        if (!amazonS3.doesObjectExist(amazonConfig.getBucket(), awsS3.key())) {
            throw new AmazonS3Exception("Object " + awsS3.key() + " does not exist!");
        }
        amazonS3.deleteObject(amazonConfig.getBucket(), awsS3.key());
    }
}