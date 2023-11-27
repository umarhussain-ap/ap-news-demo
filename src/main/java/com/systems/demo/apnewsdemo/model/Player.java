package com.systems.demo.apnewsdemo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Player extends BaseEntity  {

    private String email;
    private Integer level;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToMany(mappedBy="player",orphanRemoval = true,cascade = CascadeType.ALL)
    private Collection<PlayerSports> sports;

}
