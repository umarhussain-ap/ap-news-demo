package com.systems.demo.apnewsdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * The type Player sports.
 */
@Entity
@Table(name = "player_sport")
@Getter
@Setter
public class SportPlayer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="player_id",nullable = false)
    private Player player;

    @ManyToOne()
    @JoinColumn(name="sport_id",nullable = false)
    private Sport sport;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportPlayer that)) return false;
        return Objects.equals(player.getId(), that.player.getId()) && Objects.equals(sport.getId(), that.sport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(player.getId(), sport.getId());
    }
}
