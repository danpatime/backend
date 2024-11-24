package com.example.api.announcement;

import com.example.api.announcement.dto.AnnouncementRequest;
import com.example.api.announcement.dto.AnnouncementResponse;
import com.example.api.domain.Announcement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final ModelMapper modelMapper;


    public AnnouncementResponse createAnnouncement(AnnouncementRequest request) { // 공지사항 작성
        Announcement announcement = modelMapper.map(request, Announcement.class);
        announcement.setViewCount(0);
        announcement = announcementRepository.save(announcement);
        return modelMapper.map(announcement, AnnouncementResponse.class);
    }


    public List<AnnouncementResponse> getAllAnnouncements() {
        List<Announcement> announcements = announcementRepository.findAll();
        return announcements.stream()
                .map(announcement -> modelMapper.map(announcement, AnnouncementResponse.class))
                .collect(Collectors.toList());
    }


    public AnnouncementResponse getAnnouncement(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 공지사항을 찾을 수 없습니다."));
        return modelMapper.map(announcement, AnnouncementResponse.class);
    }


    public AnnouncementResponse updateAnnouncement(Long id, AnnouncementRequest request) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));

        modelMapper.map(request, announcement);

        announcement = announcementRepository.save(announcement);
        return modelMapper.map(announcement, AnnouncementResponse.class);
    }


    public void deleteAnnouncement(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));
        announcementRepository.delete(announcement);
    }


    public List<AnnouncementResponse> searchAnnouncements(String keyword) {
        List<Announcement> announcements = announcementRepository.findByAnnouncementTitleContaining(keyword);
        return announcements.stream()
                .map(announcement -> modelMapper.map(announcement, AnnouncementResponse.class))
                .collect(Collectors.toList());
    }

}


