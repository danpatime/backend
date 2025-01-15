package com.example.api.announcement;

import com.example.api.announcement.dto.AnnouncementRequest;
import com.example.api.announcement.dto.AnnouncementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/support/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping // 공지사항 작성
    public ResponseEntity<AnnouncementResponse> createAnnouncement(@RequestBody AnnouncementRequest request) {
        AnnouncementResponse response = announcementService.createAnnouncement(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping // 공지사항 전체 조회
    public ResponseEntity<List<AnnouncementResponse>> getAnnouncements() {
        List<AnnouncementResponse> responses = announcementService.getAllAnnouncements();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}") // 공지사항 상세 조회
    public ResponseEntity<AnnouncementResponse> getAnnouncement(@PathVariable Long id) {
        AnnouncementResponse response = announcementService.getAnnouncement(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}") // 공지사항 수정
    public ResponseEntity<AnnouncementResponse> updateAnnouncement(@PathVariable Long id, @RequestBody AnnouncementRequest request) {
        AnnouncementResponse response = announcementService.updateAnnouncement(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}") // 공지사항 삭제
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search") // 키워드 검색 기능
    public ResponseEntity<List<AnnouncementResponse>> searchAnnouncements(@RequestParam String keyword) {
        List<AnnouncementResponse> responses = announcementService.searchAnnouncements(keyword);
        return ResponseEntity.ok(responses);
    }
}