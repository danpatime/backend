package com.example.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name = "flavored", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"account_id", "category_id"})
})
public class Flavored extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLAVORED_ID")
    private Long flavoredId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id")
    private Account employee;

}
