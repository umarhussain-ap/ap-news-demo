package com.systems.demo.apnewsdemo.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UpdatePlayerSportsDto {

    @NotEmpty(message = "sportsIds cannot be empty")
    private Set<Integer> sportIds;
}
