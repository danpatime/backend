package com.example.api.announcement;

import com.example.api.announcement.dto.AnnouncementCommand;
import com.example.api.announcement.dto.AnnouncementResponse;
import com.example.api.announcement.dto.PageNumberRequest;
import com.example.api.domain.Announcement;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Transactional
    public AnnouncementResponse createAnnouncement(
            @Validated final AnnouncementCommand command
    ) {
        Announcement announcement = new Announcement();
        announcement.setAnnouncementTitle(command.announcementTitle());
        announcement.setAnnouncementType(command.announcementType());
        announcement.setAnnouncementContent(command.announcementContent());
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return new AnnouncementResponse(savedAnnouncement);
    }

    @Transactional
    public List<AnnouncementResponse> getAllAnnouncements(final PageNumberRequest pageRequest) {

        Pageable pageable = PageRequest.of(pageRequest.page()-1, 20, Sort.by("createdDate").descending());
        Page<Announcement> announcements = announcementRepository.findAllByOrderByCreatedDateDesc(pageable);
        return announcements.getContent().stream()
                .map(AnnouncementResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public AnnouncementResponse getAnnouncement(
            @Validated final Long announcementId
    ) {
        final Announcement announcement = findAnnouncementById(announcementId);
        return new AnnouncementResponse(announcement);
    }

    @Transactional
    public AnnouncementResponse updateAnnouncement(
            @Validated final Long announcementId,
            @Validated final AnnouncementCommand command
    ) {
        Announcement announcement = findAnnouncementById(announcementId);
        announcement.setAnnouncementTitle(command.announcementTitle());
        announcement.setAnnouncementType(command.announcementType());
        announcement.setAnnouncementContent(command.announcementContent());
        Announcement updatedAnnouncement = announcementRepository.save(announcement);
        return new AnnouncementResponse(updatedAnnouncement);
    }

    @Transactional
    public void deleteAnnouncement(
            @Validated final Long announcementId
    ) {
        final Announcement announcement = findAnnouncementById(announcementId);
        announcementRepository.delete(announcement);
    }

    @Transactional
    public List<AnnouncementResponse> searchAnnouncements(
            @Validated final String keyword,
            final PageNumberRequest pageRequest
    ) {
        Pageable pageable = PageRequest.of(pageRequest.page()-1, 20, Sort.by("createdDate").descending());
        final Page<Announcement> announcements = announcementRepository.findByAnnouncementTitleContaining(keyword, pageable);
        return announcements.getContent().stream()
                .map(AnnouncementResponse::new)
                .collect(Collectors.toList());
    }

    private Announcement findAnnouncementById(
            @Validated final Long announcementId
    ) {
        return announcementRepository.findById(announcementId)
                .orElseThrow(() -> new RuntimeException(getErrorMessage("announcement.not.found")));
    }

    private String getErrorMessage(final String key) {
        return key;
    }
}


