package com.systems.demo.apnewsdemo;

import com.systems.demo.apnewsdemo.dto.request.CreatePlayerDto;
import com.systems.demo.apnewsdemo.dto.request.UpdatePlayerSportsDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;
import com.systems.demo.apnewsdemo.model.Gender;
import com.systems.demo.apnewsdemo.model.Player;
import com.systems.demo.apnewsdemo.model.Sport;
import com.systems.demo.apnewsdemo.repository.PlayerRepository;
import com.systems.demo.apnewsdemo.repository.SportRepository;
import com.systems.demo.apnewsdemo.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private SportRepository sportRepository;

    @InjectMocks
    private PlayerServiceImpl playerService;


    @Test
    void testUpdatePlayer() {
        CreatePlayerDto createPlayerDto = new CreatePlayerDto();
        createPlayerDto.setAge(26);
        createPlayerDto.setLevel(6);
        createPlayerDto.setEmail("updated@example.com");

        Player player = new Player();
        player.setId(1);
        player.setAge(25);
        player.setLevel(5);
        player.setEmail("umar@test.com");

        Mockito.when(playerRepository.findById(1)).thenReturn(Optional.of(player));
        Mockito.when(playerRepository.save(player)).thenReturn(player);

        PlayerDto updatedPlayer = playerService.updatePlayer(1,createPlayerDto);

        assertNotNull(updatedPlayer);
        assertEquals(26, updatedPlayer.getAge());
        assertEquals(6, updatedPlayer.getLevel());
        assertEquals("updated@example.com", updatedPlayer.getEmail());
    }

    @Test
     void testPlayerWithNoSports() {
        Long playerId = 1L;
        Player playerEntity = new Player();
        playerEntity.setEmail("example@example.com");

        List<Player> players = List.of(playerEntity);

        Mockito.when(playerRepository.getPlayerHavingNoSports()).thenReturn(players);

        List<PlayerDto> playerDtos = playerService.getPlayersWithNoSports();

        assertNotNull(playerDtos);
        assertEquals(players.get(0).getEmail(), playerDtos.get(0).getEmail());
    }
    @Test
    void testUpdatePlayerWithSport() {
        Integer playerId = 1;
        UpdatePlayerSportsDto sportsRequest = new UpdatePlayerSportsDto();
        Set<Integer> sportIds = new HashSet<>();
        sportIds.add(1);
        sportsRequest.setSportIds(sportIds);

        Player player = new Player();
        player.setId(1);
        player.setAge(25);
        player.setLevel(5);
        player.setEmail("umar@test.com");
        player.setSports(new HashSet<>());

        Sport sport = new Sport();
        sport.setName("Badminton");
        sport.setId(1);

        Mockito.when(playerRepository.findById(any())).thenReturn(Optional.of(player));
        Mockito.when(sportRepository.findAllById(sportIds)).thenReturn(List.of(sport));

        PlayerDto updatedPlayer = playerService.updatePlayerWithSport(playerId,sportsRequest);

        assertNotNull(updatedPlayer);
        assertEquals(1, updatedPlayer.getSports().size());
    }
    @Test
    void testGetPlayersBySportsCategory() {

        Player player = new Player();
        player.setId(1);
        player.setAge(15);
        player.setEmail("umar@test.com");
        player.setLevel(10);
        player.setGender(Gender.MALE);
        Pageable pageable = Pageable.ofSize(10);

        Page<Player> playerPageExpected = new PageImpl<>(List.of(player),pageable,100);
        Mockito.when(playerRepository
                .findAllBySportsName(any(),any()))
                .thenReturn(playerPageExpected);

        PlayerDto playerDto = PlayerDto.builder()
                .email("umar@test.com")
                .id(1)
                .age(15)
                .level(10)
                .gender(Gender.MALE)
                .build();
        Page<PlayerDto> expected = new PageImpl<>(List.of(playerDto));

        Page<PlayerDto> playerDtoPage = playerService
                .getPlayersBySportsCategory("Badminton",pageable);

        Assertions.assertEquals(expected.get().findFirst().get().getId(),playerDtoPage.get().findFirst().get().getId());


    }
}
