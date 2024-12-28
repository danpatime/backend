package com.example.api.announcement;

import com.example.api.announcement.dto.AnnouncementCommand;
import com.example.api.announcement.dto.AnnouncementRequest;
import com.example.api.announcement.dto.AnnouncementResponse;
import com.example.api.domain.Announcement;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;

    @Transactional
    public AnnouncementResponse createAnnouncement(
            final AnnouncementCommand command
    ) {
        Announcement announcement = new Announcement();
        announcement.setAnnouncementTitle(command.announcementTitle());
        announcement.setAnnouncementType(command.announcementType());
        announcement.setAnnouncementContent(command.announcementContent());
        Announcement savedAnnouncement = announcementRepository.save(announcement);
        return new AnnouncementResponse(savedAnnouncement);
    }

    @Transactional
    public List<AnnouncementResponse> getAllAnnouncements() {
        final List<Announcement> announcements = announcementRepository.findAll();
        return announcements.stream()
                .map(AnnouncementResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public AnnouncementResponse getAnnouncement(
            final Long announcementId
    ) {
        final Announcement announcement = findAnnouncementById(announcementId);
        return new AnnouncementResponse(announcement);
    }

    @Transactional
    public AnnouncementResponse updateAnnouncement(
            final Long announcementId,
            final AnnouncementCommand command
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
            final Long announcementId
    ) {
        final Announcement announcement = findAnnouncementById(announcementId);
        announcementRepository.delete(announcement);
    }

    @Transactional
    public List<AnnouncementResponse> searchAnnouncements(
            final String keyword
    ) {
        final List<Announcement> announcements = announcementRepository.findByAnnouncementTitleContaining(keyword);
        return announcements.stream()
                .map(AnnouncementResponse::new)
                .collect(Collectors.toList());
    }

    private Announcement findAnnouncementById(
            final Long announcementId
    ) {
        return announcementRepository.findById(announcementId)
                .orElseThrow(() -> new RuntimeException(getErrorMessage("announcement.not.found")));
    }

    private String getErrorMessage(final String key) {
        // 실제 메시지 로직 (ex: MessageSource 사용)
        return key;
    }
}


