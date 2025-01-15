package com.example.api.announcement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AnnouncementResponse {
    private Long announcementId;
    private String announcementTitle;
    private String announcementType;
    private String announcementContent;
    private int viewCount; // 조회수

}
