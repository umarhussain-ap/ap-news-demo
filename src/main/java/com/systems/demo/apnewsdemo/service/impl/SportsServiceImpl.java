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
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SportsServiceImpl implements SportsService {

    private final SportRepository sportRepository;

    @Override
    public List<SportsDto> getSportsWithPlayersGreaterThanOrEqualTo(Integer playerCount) {
        List<Sport> sport = sportRepository.getSportsHavingPlayerGreaterThan(playerCount);

        return getSportsDtos(sport);
    }

    @Override
    public SportsDto createSport(CreateSportsDto createSportsDto) {
        Sport sport = new Sport();
        sport.setName(createSportsDto.getName());
        sport = sportRepository.save(sport);
        return SportsDto
                .builder()
                .name(sport.getName())
                .build();
    }

    @Override
    public SportsDto getSport(Integer sportId) {

        List<ErrorDto> errorList = new ArrayList<>();
        Optional<Sport> sportOptional = sportRepository.findById(sportId);
        if (sportOptional.isEmpty()) {
            errorList.add(ErrorDto
                    .builder()
                    .errorCode("101")
                    .errorMessage("No SportFound for the given Id").build());
            throw ServiceException.of(null, errorList, HttpStatus.BAD_REQUEST);
        }

        return SportsDto
                .builder()
                .players(mapPlayerToPlayDto(sportOptional.get()))
                .id(sportOptional.get().getId())
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

    @Override
    public List<SportsDto> getSportsByName(Set<String> sportName) {

        Set<Sport> sports = sportRepository.findSportsByNameIn(sportName);
        List<Sport> orderedList = new ArrayList<>();
        if (CollectionUtils.isEmpty(sports)) {
            List<ErrorDto> errorList =
                    List.of(ErrorDto
                            .builder()
                            .errorCode("101")
                            .errorMessage("No SportFound for the given sport names").build());

            throw ServiceException.of(null, errorList, HttpStatus.BAD_REQUEST);
        }
        Map<String, Sport> sportMap = sports.stream()
                .collect(Collectors.toMap(Sport::getName, Function.identity()));
        sports.forEach(sport -> {
            if (sportMap.containsKey(sport.getName())) {
                orderedList.add(sportMap.get(sport.getName()));
            }

        });
        return getSportsDtos(orderedList);
    }

    private List<SportsDto> getSportsDtos(List<Sport> sport) {
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

    private List<PlayerDto> mapPlayerToPlayDto(Sport s) {
        List<PlayerDto> playerDtos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(s.getPlayers())){
            s.getPlayers().forEach(sportPlayer -> {
                PlayerDto playerDto =
                        PlayerDto.builder()
                                .age(sportPlayer.getPlayer().getAge())
                                .email(sportPlayer.getPlayer().getEmail())
                                .gender(sportPlayer.getPlayer().getGender())
                                .level(sportPlayer.getPlayer().getLevel())
                                .id(sportPlayer.getPlayer().getId())
                                .build();
                playerDtos.add(playerDto);
            });
        }

        return playerDtos;
    }
}
