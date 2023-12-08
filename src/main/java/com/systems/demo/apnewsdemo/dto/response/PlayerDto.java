package com.systems.demo.apnewsdemo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.systems.demo.apnewsdemo.model.Gender;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private final List<SportsDto> sports;
}
