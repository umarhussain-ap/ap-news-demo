package com.systems.demo.apnewsdemo.service.impl;

import com.systems.demo.apnewsdemo.dto.request.CreateSportsDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;
import com.systems.demo.apnewsdemo.dto.response.SportsDto;
import com.systems.demo.apnewsdemo.model.Sport;
import com.systems.demo.apnewsdemo.repository.SportRepository;
import com.systems.demo.apnewsdemo.service.SportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SportsServiceImpl implements SportsService {

    private final SportRepository sportRepository;

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
    public List<SportsDto> getSportsWithPlayersGreaterThanOrEqualTo(Integer playerCount) {
         List<Sport> sport = sportRepository.getSportsHavingPlayerGreaterThan(playerCount);
         List<SportsDto> sportsDtos = new ArrayList<>();

         sport.forEach(s -> {

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
    public List<SportsDto> noPlayersEnlisted() {
        List<Sport> sports = sportRepository.getSportsHavingNoPlayers();
        List<SportsDto> response = new ArrayList<>();
        sports.forEach(sport -> {
            SportsDto sportsDto = SportsDto
                    .builder()
                    .name(sport.getName())
                    .id(sport.getId())
                    .players(new ArrayList<>())
                    .build();
            response.add(sportsDto);
        });
        return response;
    }

    @Override
    public void deleteSports(Integer id) {
        sportRepository.deleteById(id);
    }
}
