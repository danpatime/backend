package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ANNOUNCEMENT")
public class Announcement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long announcementId;

    private String announcementTitle;
    private String announcementType;
    private String announcementContent;

    @Column(columnDefinition = "int DEFAULT 0")
    private int viewCount;

}

