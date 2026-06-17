package com.example.steggo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDeckDto {
    @NotBlank(message = "Deck title cannot be blank.")
    private String title;
    private String description;
}
