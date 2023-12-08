package com.systems.demo.apnewsdemo.repository;

import com.systems.demo.apnewsdemo.model.Gender;
import com.systems.demo.apnewsdemo.model.Player;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** The interface Player repository. */
public interface PlayerRepository extends JpaRepository<Player, Integer> {

  /**
   * Get players by age and level and gender collection.
   *
   * @param age the age
   * @param level the level
   * @param gender the gender
   * @return the collection example of generated query by ORM select * from player p where p.age =
   *     10 and p.email = "example@test.com" and gender = "MALE"
   */
  @Query("select p from Player p where p.age= :age and p.email = :level and p.gender = :gender")
  List<Player> getPlayersByAgeAndLevelAndGender(
      @Param("age") Integer age, @Param("level") Integer level, @Param("gender") Gender gender);

  @Query(
      "select p from Player p left join SportPlayer sp on p.id = sp.player.id where sp.player is null ")
  List<Player> getPlayerHavingNoSports();

  @Query(
      "select p from Player p "
          + "inner join SportPlayer sp on p.id  = sp.player.id "
          + "inner join Sport s on s.id = sp.sport.id where s.name = :sportName ")
  Page<Player> findAllBySportsName(@Param("sportName") String name, Pageable pageable);
}
