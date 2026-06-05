package com.example.steggo.service;

import com.example.steggo.model.Deck;
import com.example.steggo.model.Flashcard;
import com.example.steggo.repository.DeckRepository;
import com.example.steggo.repository.FlashcardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;
    private final DeckRepository deckRepository;

    public FlashcardService(FlashcardRepository flashcardRepository, DeckRepository deckRepository) {
        this.flashcardRepository = flashcardRepository;
        this.deckRepository = deckRepository;
    }

    public List<Flashcard> getFlashcardByCollection(Long deckId) {
        return flashcardRepository.findByDeckId(deckId);
    }

    public Flashcard addFlashcardToCollection(Long deckId, Flashcard flashcard) {
        Deck deck = deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found with id" + deckId));

        flashcard.setDeck(deck);

        return flashcardRepository.save(flashcard);
    }

    public Optional<Flashcard> getFlashcardById(Long id) {
        return flashcardRepository.findById(id);
    }

    public void deleteFlashcardById(Long id) {
        flashcardRepository.deleteById(id);
    }
}
