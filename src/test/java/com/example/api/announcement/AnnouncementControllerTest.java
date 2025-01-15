package com.example.api.announcement;

import com.example.api.announcement.controller.AnnouncementController;
import com.example.api.announcement.dto.AnnouncementRequest;
import com.example.api.announcement.dto.AnnouncementResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AnnouncementControllerTest {
    @InjectMocks
    private AnnouncementController announcementController;
    @Mock
    private AnnouncementService announcementService;
    private static final String DEFAULT_TITLE = "공지사항 제목";
    private static final String DEFAULT_TYPE = "공지사항";
    private static final String DEFAULT_CONTENT = "공지사항 내용";
    private static final int DEFAULT_VIEW_COUNT = 100;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAnnouncement_success() {
        final AnnouncementRequest request = createMockRequest();
        final AnnouncementResponse response = createMockResponse();
        when(announcementService.createAnnouncement(any()))
                .thenReturn(response);
        ResponseEntity<AnnouncementResponse> result = announcementController.createAnnouncement(request);
        assertCreateAnnouncementResponse(result);
        verify(announcementService, times(1)).createAnnouncement(any());
    }

    @Test
    void getAnnouncements_success() {
        final AnnouncementResponse response = createMockResponse();
        when(announcementService.getAllAnnouncements())
                .thenReturn(List.of(response));
        ResponseEntity<List<AnnouncementResponse>> result = announcementController.getAnnouncements();
        assertGetAnnouncementsResponse(result);
        verify(announcementService, times(1)).getAllAnnouncements();
    }

    private AnnouncementRequest createMockRequest() {
        return new AnnouncementRequest(DEFAULT_TITLE, DEFAULT_TYPE, DEFAULT_CONTENT);
    }

    private AnnouncementResponse createMockResponse() {
        return new AnnouncementResponse(1L, DEFAULT_TITLE, DEFAULT_TYPE, DEFAULT_CONTENT, DEFAULT_VIEW_COUNT);
    }

    private void assertCreateAnnouncementResponse(ResponseEntity<AnnouncementResponse> result) {
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody().announcementTitle()).isEqualTo(DEFAULT_TITLE);
    }

    private void assertGetAnnouncementsResponse(ResponseEntity<List<AnnouncementResponse>> result) {
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).hasSize(1);
    }
}
