package com.example.api.announcement.dto;

import com.example.api.domain.Announcement;

public record AnnouncementResponse(
        Long announcementId,
        String announcementTitle,
        String announcementType,
        String announcementContent,
        int viewCount
) {
    public AnnouncementResponse(Announcement announcement) {
        this(
                announcement.getAnnouncementId(),
                announcement.getAnnouncementTitle(),
                announcement.getAnnouncementType(),
                announcement.getAnnouncementContent(),
                announcement.getViewCount()
        );
    }
}

