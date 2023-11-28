package com.systems.demo.apnewsdemo.repository;

import com.systems.demo.apnewsdemo.model.PlayerSports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerSportRepository extends JpaRepository<PlayerSports,Integer> {
}
