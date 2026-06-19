package com.example.steggo.dto;

import com.example.steggo.model.FlashcardOption;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateFlashcardDto {
    private Long deckId;
    private String question;
    private List<FlashcardOption> options;
}
