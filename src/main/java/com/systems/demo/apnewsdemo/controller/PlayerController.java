package com.systems.demo.apnewsdemo.controller;

import com.systems.demo.apnewsdemo.dto.request.CreatePlayerDto;
import com.systems.demo.apnewsdemo.dto.request.UpdatePlayerSportsDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;
import com.systems.demo.apnewsdemo.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Player controller.
 */
@RestController
@RequestMapping("/api/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    /**
     * Gets player with no sports.
     *
     * @return the player with no sports
     */
    @GetMapping("/no-enlistment")
    public ResponseEntity<List<PlayerDto>> getPlayerWithNoSports() {
        return new ResponseEntity<>(playerService.getPlayersWithNoSports(), HttpStatus.OK);
    }

    /**
     * Create player response entity.
     *
     * @param createPlayerDto the create player dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@Valid @RequestBody CreatePlayerDto createPlayerDto) {
        return new ResponseEntity<>(playerService.createPlayer(createPlayerDto),HttpStatus.CREATED);
    }

    /**
     * Update player with sports response entity.
     *
     * @param id                    the id
     * @param updatePlayerSportsDto the update player sports dto
     * @return the response entity
     */
    @PutMapping("/{id}/update-sports")
    public ResponseEntity<PlayerDto> updatePlayerWithSports(@PathVariable(name = "id") Integer id,
                                                            @Valid @RequestBody UpdatePlayerSportsDto updatePlayerSportsDto) {
        return new ResponseEntity<>(playerService.updatePlayerWithSport(id,updatePlayerSportsDto),HttpStatus.OK);
    }

}
