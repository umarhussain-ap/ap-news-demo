package com.systems.demo.apnewsdemo.service;

import com.systems.demo.apnewsdemo.dto.request.CreateSportsDto;
import com.systems.demo.apnewsdemo.dto.response.SportsDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * The interface Sports service.
 */
@Service
public interface SportsService {
    /**
     * Create sport sports dto.
     *
     * @param createSportsDto the create sports dto
     * @return the sports dto
     */
    SportsDto createSport(CreateSportsDto createSportsDto);

    /**
     * Gets sports with players greater than or equal to.
     *
     * @param playerCount the player count
     * @return the sports with players greater than or equal to
     */
    List<SportsDto> getSportsWithPlayersGreaterThanOrEqualTo(Integer playerCount);

    /**
     * Gets sport.
     *
     * @param sportId the sport id
     * @return the sport
     */
    SportsDto getSport(Integer sportId);

    /**
     * No players enlisted list.
     *
     * @return the list
     */
    List<SportsDto> noPlayersEnlisted();

    /**
     * Delete sports.
     *
     * @param id the id
     */
    void deleteSports(Integer id);

    /**
     * Gets sports by name.
     *
     * @param sportName the sport name
     * @return the sports by name
     */
    List<SportsDto> getSportsByName(Set<String> sportName);


}
