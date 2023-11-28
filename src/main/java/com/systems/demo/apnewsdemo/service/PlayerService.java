package com.systems.demo.apnewsdemo.service;

import com.systems.demo.apnewsdemo.dto.request.CreatePlayerDto;
import com.systems.demo.apnewsdemo.dto.request.UpdatePlayerSportsDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;

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
