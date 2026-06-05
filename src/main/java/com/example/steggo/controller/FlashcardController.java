package com.example.steggo.controller;

import com.example.steggo.model.Flashcard;
import com.example.steggo.service.FlashcardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class FlashcardController {
    private final FlashcardService flashcardService;

    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping("/collections/{collectionId}/flashcards")
    public List<Flashcard> getFlashcardByCollection(@PathVariable Long collectionId) {
        return flashcardService.getFlashcardByCollection(collectionId);
    }

    @PostMapping("/collections/{collectionId}/flashcards")
    public Flashcard addFlashcardToCollection(
            @PathVariable Long collectionId,
            @RequestBody Flashcard flashcard) {
        return flashcardService.addFlashcardToCollection(collectionId, flashcard);
    }

    @DeleteMapping("/flashcards/{id}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Long id) {
        flashcardService.deleteFlashcardById(id);
        return ResponseEntity.noContent().build();
    }
}
