package com.systems.demo.apnewsdemo.model;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/** The type Player. */
@Entity
@Getter
@Setter
public class Player extends BaseEntity {

  private String email;
  private Integer level;
  private Integer age;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @OneToMany(mappedBy = "player", orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<SportPlayer> sports;
}
