package com.systems.demo.apnewsdemo.controller;

import com.systems.demo.apnewsdemo.dto.request.CreateSportsDto;
import com.systems.demo.apnewsdemo.dto.response.SportsDto;
import com.systems.demo.apnewsdemo.service.SportsService;
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



    @GetMapping("/multiple-players/{playerCount}")
    public ResponseEntity<List<SportsDto>> getSportWithMultiplePlayers(@Valid @PathVariable(name = "playerCount") Integer playerCount) {
        return new ResponseEntity<>(sportsService.getSportsWithPlayersGreaterThanOrEqualTo(playerCount), HttpStatus.OK);
    }

    @GetMapping("/no-player-enlisted")
    public ResponseEntity<List<SportsDto>> noPlayersEnlisted() {
        return new ResponseEntity<>(sportsService.noPlayersEnlisted(), HttpStatus.OK);
    }

    //crud
    @PostMapping
    public ResponseEntity<SportsDto> createSport(@Valid @RequestBody CreateSportsDto createSportsDto) {
        return new ResponseEntity<>(sportsService.createSport(createSportsDto), HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<SportsDto> getSport(@NotNull(message = "Id cannot be null") @PathVariable(name = "Id") Integer id) {
        return new ResponseEntity<>(sportsService.getSport(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSport(@PathVariable(name = "id") Integer id) {
        sportsService.deleteSports(id);
        return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
    }
}
