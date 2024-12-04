package com.example.api.announcement;

import com.example.api.announcement.dto.AnnouncementRequest;
import com.example.api.announcement.dto.AnnouncementResponse;
import com.example.api.domain.Announcement;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Transactional
    public AnnouncementResponse createAnnouncement(@Validated AnnouncementRequest request) {
        Announcement announcement = new Announcement();
        announcement.setAnnouncementTitle(request.announcementTitle());
        announcement.setAnnouncementType(request.announcementType());
        announcement.setAnnouncementContent(request.announcementContent());

        Announcement savedAnnouncement = announcementRepository.save(announcement);

        return new AnnouncementResponse(savedAnnouncement);
    }

    @Transactional
    public List<AnnouncementResponse> getAllAnnouncements() {
        final List<Announcement> announcements = announcementRepository.findAll();

        return announcements.stream()
                .map(announcement -> new AnnouncementResponse(
                        announcement.getAnnouncementId(),
                        announcement.getAnnouncementTitle(),
                        announcement.getAnnouncementType(),
                        announcement.getAnnouncementContent()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public AnnouncementResponse getAnnouncement(final Long annoucementId) {
        final Announcement announcement = announcementRepository.findById(annoucementId)
                .orElseThrow(() -> new RuntimeException("해당 공지사항을 찾을 수 없습니다."));

        return new AnnouncementResponse(announcement);
    }

    @Transactional
    public AnnouncementResponse updateAnnouncement(final Long annoucementId, @Validated AnnouncementRequest request) {
        Announcement announcement = announcementRepository.findById(annoucementId)
                .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));

        announcement.setAnnouncementTitle(request.announcementTitle());
        announcement.setAnnouncementType(request.announcementType());
        announcement.setAnnouncementContent(request.announcementContent());

        Announcement updatedAnnouncement = announcementRepository.save(announcement);

        return new AnnouncementResponse(updatedAnnouncement);
    }

    @Transactional
    public void deleteAnnouncement(final Long annoucementId) {
        final Announcement announcement = announcementRepository.findById(annoucementId)
                .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));

        announcementRepository.delete(announcement);
    }

    @Transactional
    public List<AnnouncementResponse> searchAnnouncements(final String keyword) {
        final List<Announcement> announcements = announcementRepository.findByAnnouncementTitleContaining(keyword);

        return announcements.stream()
                .map(announcement -> new AnnouncementResponse(
                        announcement.getAnnouncementId(),
                        announcement.getAnnouncementTitle(),
                        announcement.getAnnouncementType(),
                        announcement.getAnnouncementContent()
                ))
                .collect(Collectors.toList());
    }
}


