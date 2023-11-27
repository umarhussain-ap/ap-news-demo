package com.systems.demo.apnewsdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "player_sport")
@Getter
@Setter
public class PlayerSports extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="player_id",nullable = false)
    private Player player;

    @ManyToOne()
    @JoinColumn(name="sport_id",nullable = false)
    private Sport sport;

}
