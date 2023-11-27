package com.systems.demo.apnewsdemo.service.impl;

import com.systems.demo.apnewsdemo.dto.request.CreatePlayerDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;
import com.systems.demo.apnewsdemo.model.Player;
import com.systems.demo.apnewsdemo.repository.PlayerRepository;
import com.systems.demo.apnewsdemo.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Override
    public List<PlayerDto> getPlayersWithNoSports() {
        List<Player> players = playerRepository.getPlayerHavingNoSports();
        List<PlayerDto> playerDtos = new ArrayList<>();
        players.forEach(player -> {
            PlayerDto playerDto =
                    PlayerDto
                            .builder()
                            .id(player.getId())
                            .age(player.getAge())
                            .email(player.getEmail())
                            .level(player.getLevel())
                            .build();
            playerDtos.add(playerDto);
        });
        return playerDtos;
    }

    @Override
    public PlayerDto createPlayerDto(CreatePlayerDto createPlayerDto) {
        Player player = new Player();

        player.setAge(createPlayerDto.getAge());
        player.setGender(createPlayerDto.getGender());
        player.setLevel(createPlayerDto.getLevel());
        player = playerRepository.save(player);


        return PlayerDto
                .builder()
                .id(player.getId())
                .age(player.getAge())
                .email(player.getEmail())
                .level(player.getLevel())
                .gender(player.getGender())
                .build();

    }


}
