package com.example.api.announcement;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.api.announcement.dto.AnnouncementCommand;
import com.example.api.announcement.dto.AnnouncementResponse;
import com.example.api.domain.Announcement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

@SpringBootTest
class AnnouncementServiceTest {
    @MockBean
    private AnnouncementRepository announcementRepository;

    @Autowired
    private AnnouncementService announcementService;

    private Announcement announcement;
    private AnnouncementCommand announcementCommand;

    public AnnouncementServiceTest() {
        announcement = new Announcement(1L, "Test Title", "General", "Test Content", 0);
        announcementCommand = new AnnouncementCommand(1L, "Test Title", "General", "Test Content");
    }

    @Test
    @DisplayName("새로운 공지사항을 생성할 수 있어야 한다")
    void shouldCreateAnnouncement() {
        when(announcementRepository.save(any(Announcement.class))).thenReturn(announcement);
        AnnouncementResponse response = announcementService.createAnnouncement(announcementCommand);
        assertNotNull(response);
        assertEquals("Test Title", response.announcementTitle());
        assertEquals("General", response.announcementType());
        assertEquals("Test Content", response.announcementContent());
        verify(announcementRepository, times(1)).save(any(Announcement.class));
    }

    @Test
    @DisplayName("모든 공지사항을 조회할 수 있어야 한다")
    void shouldGetAllAnnouncements() {
        when(announcementRepository.findAll()).thenReturn(Arrays.asList(announcement));
        List<AnnouncementResponse> responses = announcementService.getAllAnnouncements();
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Test Title", responses.get(0).announcementTitle());
        verify(announcementRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("특정 공지사항을 조회할 수 있어야 한다")
    void shouldGetAnnouncement() {
        when(announcementRepository.findById(1L)).thenReturn(Optional.of(announcement));
        AnnouncementResponse response = announcementService.getAnnouncement(1L);
        assertNotNull(response);
        assertEquals("Test Title", response.announcementTitle());
        verify(announcementRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("공지사항이 존재하지 않으면 예외가 발생해야 한다")
    void shouldThrowExceptionWhenAnnouncementNotFound() {
        when(announcementRepository.findById(1L)).thenReturn(Optional.empty());
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            announcementService.getAnnouncement(1L);
        });
        assertEquals("announcement.not.found", thrown.getMessage());
        verify(announcementRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("공지사항을 업데이트할 수 있어야 한다")
    void shouldUpdateAnnouncement() {
        when(announcementRepository.findById(1L)).thenReturn(Optional.of(announcement));
        when(announcementRepository.save(any(Announcement.class))).thenReturn(announcement);
        AnnouncementResponse response = announcementService.updateAnnouncement(1L, announcementCommand);
        assertNotNull(response);
        assertEquals("Test Title", response.announcementTitle());
        assertEquals("General", response.announcementType());
        assertEquals("Test Content", response.announcementContent());

        verify(announcementRepository, times(1)).findById(1L);
        verify(announcementRepository, times(1)).save(any(Announcement.class));
    }

    @Test
    @DisplayName("공지사항을 삭제할 수 있어야 한다")
    void shouldDeleteAnnouncement() {
        when(announcementRepository.findById(1L)).thenReturn(Optional.of(announcement));
        announcementService.deleteAnnouncement(1L, 1L);
        verify(announcementRepository, times(1)).findById(1L);
        verify(announcementRepository, times(1)).delete(any(Announcement.class));
    }

    @Test
    @DisplayName("키워드를 사용해 공지사항을 검색할 수 있어야 한다")
    void shouldSearchAnnouncements() {
        when(announcementRepository.findByAnnouncementTitleContaining("Test")).thenReturn(Arrays.asList(announcement));
        List<AnnouncementResponse> responses = announcementService.searchAnnouncements("Test");
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Test Title", responses.get(0).announcementTitle());
        verify(announcementRepository, times(1)).findByAnnouncementTitleContaining("Test");
    }
}



