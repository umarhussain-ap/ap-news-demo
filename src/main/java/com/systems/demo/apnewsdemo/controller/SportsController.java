package com.systems.demo.apnewsdemo.controller;

import com.systems.demo.apnewsdemo.dto.request.CreateSportsDto;
import com.systems.demo.apnewsdemo.dto.response.SportsDto;
import com.systems.demo.apnewsdemo.service.SportsService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
@RequiredArgsConstructor
public class SportsController {

    private final SportsService sportsService;

    @PostMapping("/create-sport")
    public ResponseEntity<SportsDto> createSport(@Valid @RequestBody CreateSportsDto createSportsDto) {
        return new ResponseEntity<>(sportsService.createSport(createSportsDto), HttpStatus.CREATED);
    }

    @GetMapping("/multiple-players/{playerCount}")
    public ResponseEntity<List<SportsDto>> getSportWithMultiplePlayers(@Valid @PathVariable(name = "playerCount") Integer playerCount) {
        return new ResponseEntity<>(sportsService.getSportsWithPlayersGreaterThanOrEqualTo(playerCount), HttpStatus.OK);
    }

    @GetMapping("/no-player-enlisted")
    public ResponseEntity<List<SportsDto>> noPlayersEnlisted() {
        return new ResponseEntity<>(sportsService.noPlayersEnlisted(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSportAndAssosiatedMapping(@PathVariable(name = "id") Integer id){
        sportsService.deleteSports(id);
        return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
    }
}
