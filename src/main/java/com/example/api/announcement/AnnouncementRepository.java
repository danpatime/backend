package com.example.api.announcement;

import com.example.api.domain.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    Page<Announcement> findByAnnouncementTitleContaining(final String keyword, final Pageable pageable);

    Page<Announcement> findAllByOrderByCreatedDateDesc(final Pageable pageable);
}
