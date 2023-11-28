package com.systems.demo.apnewsdemo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class SportsDto {
    private final Integer id;
    private final String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<PlayerDto> players;

}
