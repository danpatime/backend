package com.example.api.announcement;

import com.example.api.announcement.dto.AnnouncementCommand;
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

    @PostMapping
    public ResponseEntity<AnnouncementResponse> createAnnouncement(
            @RequestBody final AnnouncementRequest request
    ) {
        final AnnouncementCommand command = request.toCommand();
        final AnnouncementResponse response = announcementService.createAnnouncement(command);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementResponse>> getAnnouncements() {
        final List<AnnouncementResponse> responses = announcementService.getAllAnnouncements();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{announcementId}")
    public ResponseEntity<AnnouncementResponse> getAnnouncement(
            @PathVariable(required = true) final Long announcementId
    ) {
        final AnnouncementResponse response = announcementService.getAnnouncement(announcementId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{announcementId}")
    public ResponseEntity<AnnouncementResponse> updateAnnouncement(
            @PathVariable(required = true) final Long announcementId,
            @RequestBody final AnnouncementRequest request
    ) {
        final AnnouncementCommand command = request.toCommand();
        final AnnouncementResponse response = announcementService.updateAnnouncement(
                announcementId, command);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{announcementId}")
    public ResponseEntity<Void> deleteAnnouncement(
            @PathVariable(required = true) final Long announcementId
    ) {
        announcementService.deleteAnnouncement(announcementId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AnnouncementResponse>> searchAnnouncements(
            @RequestParam(required = true) final String keyword
    ) {
        final List<AnnouncementResponse> responses = announcementService.searchAnnouncements(keyword);
        return ResponseEntity.ok(responses);
    }
}
