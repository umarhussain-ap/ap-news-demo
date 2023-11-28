package com.systems.demo.apnewsdemo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * The type Sport.
 */
@Entity
@Table(name = "sport")
@Getter
@Setter
public class Sport extends BaseEntity {
    private String name;
    @OneToMany(mappedBy="sport",orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<PlayerSports> players;
}
