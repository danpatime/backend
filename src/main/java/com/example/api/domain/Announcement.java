package com.example.api.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Table(name = "ANNOUNCEMENT")
public class Announcement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNOUNCEMENT_ID")
    private Long announcementId;
    @Column(name = "ANNOUNCEMENT_TITLE")
    private String announcementTitle;
    @Column(name = "ANNOUNCEMENT_TYPE")
    private String announcementType;
    @Column(name = "ANNOUNCEMENT_CONTENT", columnDefinition = "TEXT")
    private String announcementContent;
    @Column(name = "VIEW_COUNT", columnDefinition = "int DEFAULT 0")
    private int viewCount;
}