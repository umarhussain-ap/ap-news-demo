package com.systems.demo.apnewsdemo;

import com.systems.demo.apnewsdemo.dto.request.CreatePlayerDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;
import com.systems.demo.apnewsdemo.model.Player;
import com.systems.demo.apnewsdemo.repository.PlayerRepository;
import com.systems.demo.apnewsdemo.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

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

        List<PlayerDto> playerDtos  = List.of(PlayerDto.builder().email("example@example.com").build());

        Mockito.lenient().when(playerRepository.getPlayerHavingNoSports()).thenReturn(players);

        playerDtos = playerService.getPlayersWithNoSports();

        assertNotNull(playerDtos);
        assertEquals(players.get(0).getEmail(), playerDtos.get(0).getEmail());
    }
}
