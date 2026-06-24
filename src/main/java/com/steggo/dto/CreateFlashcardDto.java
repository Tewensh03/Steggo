package com.steggo.dto;

import com.steggo.model.FlashcardOption;
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
