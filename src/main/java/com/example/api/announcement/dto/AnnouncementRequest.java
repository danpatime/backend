package com.example.api.announcement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnnouncementRequest {
    private String announcementTitle;
    private String announcementType;
    private String announcementContent;
}
