package com.example.api.announcement.dto;

import lombok.NonNull;

public record AnnouncementRequest(
        @NonNull
        String announcementTitle,
        String announcementType,
        String announcementContent
) {
        public AnnouncementCommand toCommand() {
                return new AnnouncementCommand(
                        this.announcementTitle,
                        this.announcementType,
                        this.announcementContent
                );
        }
}

