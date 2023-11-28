package com.systems.demo.apnewsdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * The type Base entity.
 */
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
