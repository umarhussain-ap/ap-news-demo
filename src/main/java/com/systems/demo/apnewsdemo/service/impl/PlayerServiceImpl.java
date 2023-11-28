package com.systems.demo.apnewsdemo.service.impl;

import com.systems.demo.apnewsdemo.dto.request.CreatePlayerDto;
import com.systems.demo.apnewsdemo.dto.request.UpdatePlayerSportsDto;
import com.systems.demo.apnewsdemo.dto.response.ErrorDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;
import com.systems.demo.apnewsdemo.dto.response.SportsDto;
import com.systems.demo.apnewsdemo.exception.ServiceException;
import com.systems.demo.apnewsdemo.model.Player;
import com.systems.demo.apnewsdemo.model.PlayerSports;
import com.systems.demo.apnewsdemo.model.Sport;
import com.systems.demo.apnewsdemo.repository.PlayerRepository;
import com.systems.demo.apnewsdemo.repository.SportRepository;
import com.systems.demo.apnewsdemo.service.PlayerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The type Player service.
 */
@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final SportRepository sportRepository;


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
    public PlayerDto createPlayer(CreatePlayerDto createPlayerDto) {
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

    @Override
    public PlayerDto updatePlayer(Integer id, CreatePlayerDto createPlayerDto) {

        Optional<Player> playerOptional = playerRepository.findById(id);
        List<ErrorDto> errorList = new ArrayList<>();
        Player updatedPlayer;
        if(playerOptional.isEmpty()){
            errorList.add(ErrorDto.builder().errorCode("101").errorMessage("No Player Found for the given Id").build());
            throw ServiceException.of(null, errorList, HttpStatus.BAD_REQUEST);
        }

        playerOptional.get().setAge(createPlayerDto.getAge());
        playerOptional.get().setGender(createPlayerDto.getGender());
        playerOptional.get().setLevel(createPlayerDto.getLevel());
        playerOptional.get().setEmail(createPlayerDto.getEmail());
        updatedPlayer = playerRepository.save(playerOptional.get());



        return PlayerDto
                .builder()
                .id(updatedPlayer.getId())
                .age(updatedPlayer.getAge())
                .email(updatedPlayer.getEmail())
                .level(updatedPlayer.getLevel())
                .gender(updatedPlayer.getGender())
                .build();


    }


    @Override
    @Transactional
    public PlayerDto updatePlayerWithSport(Integer playerId, UpdatePlayerSportsDto updatePlayerSportsDto) {

        List<ErrorDto> errorList = new ArrayList<>();
        List<Sport> sportList;
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        List<SportsDto> sportsDtos = new ArrayList<>();

        if (optionalPlayer.isEmpty()) {
            errorList.add(ErrorDto.builder().errorCode("101").errorMessage("No Player Found for the given Id").build());
        }

        if(optionalPlayer.isPresent()) {
            Set<PlayerSports> playerSports = optionalPlayer.get().getSports();
            if(!CollectionUtils.isEmpty(playerSports)) {
                playerSports.forEach(playerSport ->
                        updatePlayerSportsDto.getSportIds().remove(playerSport.getSport().getId())
                );
            }

        }

        if (!CollectionUtils.isEmpty(updatePlayerSportsDto.getSportIds())) {
            sportList = sportRepository.findAllById(updatePlayerSportsDto.getSportIds());
            if (CollectionUtils.isEmpty(sportList)) {
                errorList.add(
                        ErrorDto.builder()
                        .errorCode("102")
                        .errorMessage("No Sports Found for the given Sport Id List")
                        .build());
            } else {
                sportList.forEach(sport -> {
                    PlayerSports playerSports = new PlayerSports();
                    playerSports.setPlayer(optionalPlayer.get());
                    playerSports.setSport(sport);
                    optionalPlayer.get().getSports().add(playerSports);
                });
            }

        }

        if (!errorList.isEmpty()) {
            throw ServiceException.of(null, errorList, HttpStatus.BAD_REQUEST);
        }

        optionalPlayer.get().getSports().forEach(playerSports ->
            sportsDtos.add(SportsDto
                      .builder()
                      .name(playerSports.getSport().getName())
                      .id(playerSports.getSport().getId())
                      .build())
        );

        return PlayerDto.builder()
                .sports(sportsDtos)
                .id(optionalPlayer.get().getId())
                .gender(optionalPlayer.get().getGender())
                .age(optionalPlayer.get().getAge())
                .level(optionalPlayer.get().getLevel())
                .email(optionalPlayer.get().getEmail())
                .build();

    }


}
