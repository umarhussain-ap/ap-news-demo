package com.systems.demo.apnewsdemo.dto.request;

import jakarta.validation.constraints.NotEmpty;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/** The type Update player sports dto. */
@Getter
@Setter
public class UpdatePlayerSportsDto {
  @NotEmpty(message = "sportsIds cannot be empty")
  private Set<Integer> sportIds;
}
