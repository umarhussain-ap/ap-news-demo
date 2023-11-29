package com.systems.demo.apnewsdemo.service;

import com.systems.demo.apnewsdemo.dto.request.CreatePlayerDto;
import com.systems.demo.apnewsdemo.dto.request.UpdatePlayerSportsDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * The interface Player service.
 */
public interface PlayerService {
    /**
     * Gets players with no sports.
     *
     * @return the players with no sports
     */
    List<PlayerDto> getPlayersWithNoSports();

    /**
     * add player in database and returns player dto as response.
     *
     * @param createPlayerDto the create player dto
     * @return the player dto
     */
    PlayerDto createPlayer(CreatePlayerDto createPlayerDto);

    /**
     * Gets players by sports category.
     *
     * @param category the category
     * @param pageable the pageable
     * @return the players by sports category
     */
    Page<PlayerDto> getPlayersBySportsCategory(String category, Pageable pageable);

    /**
     * Update player for the given Id return player dto.
     *
     * @param id              the id
     * @param createPlayerDto the create player dto
     * @return the player dto
     */
    PlayerDto updatePlayer(Integer id, CreatePlayerDto createPlayerDto);

    /**
     * Update player with sport player dto.
     *
     * @param playerId              the player id
     * @param updatePlayerSportsDto the update player sports dto
     * @return the player dto
     */
    PlayerDto updatePlayerWithSport(Integer playerId, UpdatePlayerSportsDto updatePlayerSportsDto);
}
