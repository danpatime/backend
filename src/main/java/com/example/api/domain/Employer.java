package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "EMPLOYER")
public class Employer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYER_ID")
    private Long employerId;

    @Column(name = "EMPLOYER_NICKNAME")
    private String nickname;
}

