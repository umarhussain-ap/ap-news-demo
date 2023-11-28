package com.systems.demo.apnewsdemo.service;

import com.systems.demo.apnewsdemo.dto.request.CreatePlayerDto;
import com.systems.demo.apnewsdemo.dto.request.UpdatePlayerSportsDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;

import java.util.List;

public interface PlayerService {
    List<PlayerDto> getPlayersWithNoSports();

    PlayerDto createPlayerDto(CreatePlayerDto createPlayerDto);

    PlayerDto updatePlayerWithSport(Integer playerId, UpdatePlayerSportsDto updatePlayerSportsDto);
}
