package com.example.api.announcement;

import com.example.api.announcement.dto.AnnouncementCommand;
import com.example.api.announcement.dto.AnnouncementResponse;
import com.example.api.domain.Announcement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AnnouncementServiceTest {
    @InjectMocks
    private AnnouncementService announcementService;
    @Mock
    private AnnouncementRepository announcementRepository;
    private static final String DEFAULT_TITLE = "공지사항 제목";
    private static final String DEFAULT_TYPE = "공지사항";
    private static final String DEFAULT_CONTENT = "공지사항 내용";
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAnnouncement_success() {
        final AnnouncementCommand command = createMockCommand();
        final Announcement announcement = createMockAnnouncement(command);
        when(announcementRepository.save(any(Announcement.class)))
                .thenReturn(announcement);
        AnnouncementResponse response = announcementService.createAnnouncement(command);
        assertCreateAnnouncementResponse(response);
        verify(announcementRepository, times(1)).save(any(Announcement.class));
    }

    @Test
    void getAnnouncement_notFound() {
        final Long announcementId = 1L;
        when(announcementRepository.findById(announcementId))
                .thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            announcementService.getAnnouncement(announcementId);
        });
        assertThat(exception.getMessage()).isEqualTo("announcement.not.found");
        verify(announcementRepository, times(1)).findById(announcementId);
    }

    @Test
    void searchAnnouncements_success() {
        final String keyword = "공지";
        final Announcement announcement = createMockAnnouncementWithTitle(keyword);
        when(announcementRepository.findByAnnouncementTitleContaining(keyword))
                .thenReturn(List.of(announcement));
        List<AnnouncementResponse> responses = announcementService.searchAnnouncements(keyword);
        assertSearchAnnouncementResponses(responses);
        verify(announcementRepository, times(1)).findByAnnouncementTitleContaining(keyword);
    }

    private AnnouncementCommand createMockCommand() {
        return new AnnouncementCommand(DEFAULT_TITLE, DEFAULT_TYPE, DEFAULT_CONTENT);
    }

    private Announcement createMockAnnouncement(AnnouncementCommand command) {
        Announcement announcement = new Announcement();
        announcement.setAnnouncementTitle(command.announcementTitle());
        announcement.setAnnouncementType(command.announcementType());
        announcement.setAnnouncementContent(command.announcementContent());
        return announcement;
    }

    private Announcement createMockAnnouncementWithTitle(String title) {
        Announcement announcement = new Announcement();
        announcement.setAnnouncementTitle(DEFAULT_TITLE);
        announcement.setAnnouncementType(DEFAULT_TYPE);
        announcement.setAnnouncementContent(DEFAULT_CONTENT);
        return announcement;
    }

    private void assertCreateAnnouncementResponse(AnnouncementResponse response) {
        assertThat(response.announcementTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(response.announcementType()).isEqualTo(DEFAULT_TYPE);
        assertThat(response.announcementContent()).isEqualTo(DEFAULT_CONTENT);
    }

    private void assertSearchAnnouncementResponses(List<AnnouncementResponse> responses) {
        assertThat(responses).hasSize(1);
        assertThat(responses.get(0).announcementTitle()).isEqualTo(DEFAULT_TITLE);
    }
}


