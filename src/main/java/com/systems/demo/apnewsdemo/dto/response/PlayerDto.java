package com.systems.demo.apnewsdemo.dto.response;

import com.systems.demo.apnewsdemo.model.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class PlayerDto {
    private final Integer id;
    private final String email;
    private final Integer age;
    private final Integer level;
    private final Gender gender;
    private final List<SportsDto> sports;
}
