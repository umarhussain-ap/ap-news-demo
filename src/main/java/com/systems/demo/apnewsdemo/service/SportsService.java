package com.systems.demo.apnewsdemo.service;

import com.systems.demo.apnewsdemo.dto.request.CreateSportsDto;
import com.systems.demo.apnewsdemo.dto.response.SportsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SportsService {
    SportsDto createSport(CreateSportsDto createSportsDto);

    List<SportsDto> getSportsWithPlayersGreaterThanOrEqualTo(Integer playerCount);

    List<SportsDto> noPlayersEnlisted();

    void deleteSports(Integer id);
}
