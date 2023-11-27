package com.systems.demo.apnewsdemo.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSportsDto {
    @NotBlank(message = "Sport Name is mandatory")
    private String name;
}
