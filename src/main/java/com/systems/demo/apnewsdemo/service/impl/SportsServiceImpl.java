package com.systems.demo.apnewsdemo.service.impl;

import com.systems.demo.apnewsdemo.dto.request.CreateSportsDto;
import com.systems.demo.apnewsdemo.dto.response.ErrorDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;
import com.systems.demo.apnewsdemo.dto.response.SportsDto;
import com.systems.demo.apnewsdemo.exception.ServiceException;
import com.systems.demo.apnewsdemo.model.Sport;
import com.systems.demo.apnewsdemo.repository.SportRepository;
import com.systems.demo.apnewsdemo.service.SportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SportsServiceImpl implements SportsService {

    private final SportRepository sportRepository;

    @Override
    public List<SportsDto> getSportsWithPlayersGreaterThanOrEqualTo(Integer playerCount) {
         List<Sport> sport = sportRepository.getSportsHavingPlayerGreaterThan(playerCount);
         List<SportsDto> sportsDtos = new ArrayList<>();

         sport.forEach(s -> {

             List<PlayerDto> playerDtos = mapPlayerToPlayDto(s);

             SportsDto sportsDto = SportsDto
                     .builder()
                     .name(s.getName())
                     .id(s.getId())
                     .players(playerDtos)
                     .build();
             sportsDtos.add(sportsDto);
         });

         return sportsDtos;
    }

    @Override
    public SportsDto createSport(CreateSportsDto createSportsDto) {
        Sport sport = new Sport();
        sport.setName(createSportsDto.getName());
        sport = sportRepository.save(sport);
        return SportsDto
                .builder()
                .players(new ArrayList<>())
                .name(sport.getName())
                .build();
    }

    @Override
    public SportsDto getSport(Integer sportId) {

        List<ErrorDto> errorList = new ArrayList<>();
        Optional<Sport> sportOptional = sportRepository.findById(sportId);
        if(sportOptional.isEmpty()) {
            errorList.add(ErrorDto
                    .builder()
                    .errorCode("101")
                    .errorMessage("No SportFound for the given Id").build());
            throw ServiceException.of(null, errorList, HttpStatus.BAD_REQUEST);
        }

        return SportsDto
                .builder()
                .players(mapPlayerToPlayDto(sportOptional.get()))
                .name(sportOptional.get().getName())
                .build();

    }

    @Override
    public List<SportsDto> noPlayersEnlisted() {
        List<Sport> sports = sportRepository.getSportsHavingNoPlayers();
        List<SportsDto> response = new ArrayList<>();
        sports.forEach(sport -> {
            SportsDto sportsDto = SportsDto
                    .builder()
                    .name(sport.getName())
                    .id(sport.getId())
                    .build();
            response.add(sportsDto);
        });
        return response;
    }

    @Override
    public void deleteSports(Integer id) {
        sportRepository.deleteById(id);
    }

    private  List<PlayerDto> mapPlayerToPlayDto(Sport s) {
        List<PlayerDto> playerDtos = new ArrayList<>();
        s.getPlayers().forEach(playerSports -> {
            PlayerDto playerDto  =
                    PlayerDto.builder()
                            .age(playerSports.getPlayer().getAge())
                            .email(playerSports.getPlayer().getEmail())
                            .level(playerSports.getPlayer().getLevel())
                            .id(playerSports.getPlayer().getId())
                            .build();
            playerDtos.add(playerDto);
        });
        return playerDtos;
    }
}
