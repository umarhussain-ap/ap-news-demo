package com.systems.demo.apnewsdemo.dto.response;

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
    private final List<PlayerDto> players;
}
