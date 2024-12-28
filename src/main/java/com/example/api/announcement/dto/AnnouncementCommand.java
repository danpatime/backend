package com.example.api.announcement.dto;

import lombok.NonNull;

public record AnnouncementCommand(
        @NonNull
        String announcementTitle,
        String announcementType,
        String announcementContent
) {}
