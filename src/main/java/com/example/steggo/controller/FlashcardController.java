package com.example.steggo.controller;

import com.example.steggo.dto.CreateFlashcardDto;
import com.example.steggo.model.Flashcard;
import com.example.steggo.service.FlashcardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FlashcardController {
    private final FlashcardService flashcardService;

    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping("/decks/{deckId}/flashcards")
    public List<Flashcard> getFlashcardByCollection(@PathVariable Long deckId) {
        return flashcardService.getFlashcardByCollection(deckId);
    }

    @PostMapping("/decks/{deckId}/flashcards")
    public Flashcard addFlashcardToCollection(
            @PathVariable Long deckId,
            @RequestBody CreateFlashcardDto flashcardRequest) {
        return flashcardService.addFlashcardToDeck(deckId, flashcardRequest);
    }

    @DeleteMapping("/flashcards/{id}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Long id) {
        flashcardService.deleteFlashcardById(id);
        return ResponseEntity.noContent().build();
    }
}
