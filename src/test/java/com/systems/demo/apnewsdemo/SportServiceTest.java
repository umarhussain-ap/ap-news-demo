package com.systems.demo.apnewsdemo;

import com.systems.demo.apnewsdemo.dto.request.CreateSportsDto;
import com.systems.demo.apnewsdemo.dto.response.PlayerDto;
import com.systems.demo.apnewsdemo.dto.response.SportsDto;
import com.systems.demo.apnewsdemo.model.Gender;
import com.systems.demo.apnewsdemo.model.Player;
import com.systems.demo.apnewsdemo.model.SportPlayer;
import com.systems.demo.apnewsdemo.model.Sport;
import com.systems.demo.apnewsdemo.repository.SportRepository;
import com.systems.demo.apnewsdemo.service.impl.SportsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class SportServiceTest {

    @Mock
    private SportRepository sportRepository;

    @InjectMocks
    private SportsServiceImpl sportsService;

    @Test
    void testGetSportsWithPlayersGreaterThanOrEqualTo() {
        Sport sport = new Sport();

        sport.setId(1);
        sport.setName("Cricket");

        SportPlayer sportPlayer = new SportPlayer();
        sportPlayer.setSport(sport);



        Player player = new Player();
        player.setId(1);
        player.setEmail("test@test.com");
        player.setAge(15);
        player.setGender(Gender.MALE);
        player.setLevel(10);
        sportPlayer.setPlayer(player);
        sport.setPlayers(Set.of(sportPlayer));

        SportsDto sportsDtoExpected = SportsDto
                .builder()
                .id(1)
                .name("Cricket")
                .players(List.of(PlayerDto
                        .builder()
                        .id(1)
                        .email("test@test.com")
                        .gender(Gender.MALE)
                        .level(10)
                        .age(15)
                        .build()))
                .build();

        Mockito.when(sportRepository.getSportsHavingPlayerGreaterThan(any())).thenReturn(List.of(sport));
        List<SportsDto> sports = sportsService.getSportsWithPlayersGreaterThanOrEqualTo(1);

        Assertions.assertEquals(sportsDtoExpected.getName(),sports.get(0).getName());

        Assertions.assertEquals(sportsDtoExpected.getPlayers().get(0).getEmail()
                ,sports.get(0).getPlayers().get(0).getEmail());

        Assertions.assertEquals(sportsDtoExpected.getPlayers().get(0).getId()
                ,sports.get(0).getPlayers().get(0).getId());

        Assertions.assertEquals(sportsDtoExpected.getPlayers().get(0).getGender()
                ,sports.get(0).getPlayers().get(0).getGender());


    }

    @Test
    void testCreateSport() {
        CreateSportsDto createSportsDto = new CreateSportsDto();
        createSportsDto.setName("Cricket");

        Sport sport = new Sport();
        sport.setName("Cricket");
        sport.setId(1);
        Mockito.when(sportRepository.save(any())).thenReturn(sport);

        SportsDto expected = SportsDto
                .builder()
                .id(1)
                .name("Cricket").build();

        SportsDto actual = sportsService.createSport(createSportsDto);
        Assertions.assertEquals(expected.getName(),actual.getName());

    }
    @Test
    void testGetSportsByName(){
        Set<String> sportNames =
                new LinkedHashSet<>(Set.of("Cricket","Badminton","Hockey"));



        Sport cricket = new Sport();
        cricket.setId(1);
        cricket.setName("Cricket");

        Sport badminton = new Sport();
        badminton.setId(2);
        badminton.setName("badminton");

        Player player = new Player();
        player.setId(1);
        player.setEmail("test@test.com");
        player.setAge(15);
        player.setGender(Gender.MALE);
        player.setLevel(10);

        SportPlayer cricketPlayer = new SportPlayer();
        cricketPlayer.setSport(cricket);
        cricketPlayer.setPlayer(player);
        cricket.setPlayers(Set.of(cricketPlayer));

        SportPlayer badmintonPlayer = new SportPlayer();
        badmintonPlayer.setSport(badminton);
        badmintonPlayer.setPlayer(player);
        badminton.setPlayers(Set.of(badmintonPlayer));

        Sport tableTennis = new Sport();
        tableTennis.setId(3);
        tableTennis.setName("tableTennis");


        Set<Sport> sports = Set.of(cricket,badminton,tableTennis);
        Mockito.when(sportRepository.findSportsByNameIn(any())).thenReturn(sports);
        List<SportsDto> actual = sportsService.getSportsByName(sportNames);

        Assertions.assertTrue(actual.size()>0);

    }

}
