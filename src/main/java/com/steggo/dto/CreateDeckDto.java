package com.steggo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDeckDto {
    @NotBlank(message = "Deck title cannot be blank.")
    @Size(max = 50, message = "Title must be under 50 characters.")
    private String title;

    @Size(max = 250, message = "Description must be under 250 characters.")
    private String description;
}
