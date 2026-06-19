package com.example.steggo.service;

import com.example.steggo.dto.CreateFlashcardDto;
import com.example.steggo.exception.ResourceNotFoundException;
import com.example.steggo.model.Deck;
import com.example.steggo.model.Flashcard;
import com.example.steggo.model.FlashcardOption;
import com.example.steggo.model.User;
import com.example.steggo.repository.DeckRepository;
import com.example.steggo.repository.FlashcardRepository;
import com.example.steggo.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlashcardService {
    private final UserRepository userRepository;
    private final FlashcardRepository flashcardRepository;
    private final DeckRepository deckRepository;

    public FlashcardService(
            UserRepository userRepository,
            FlashcardRepository flashcardRepository,
            DeckRepository deckRepository
    ) {
        this.userRepository = userRepository;
        this.flashcardRepository = flashcardRepository;
        this.deckRepository = deckRepository;
    }

    public List<Flashcard> getFlashcardByCollection(Long deckId) {
        return flashcardRepository.findByDeckId(deckId);
    }

    public Flashcard addFlashcardToDeck(Long deckId, CreateFlashcardDto flashcardRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResourceNotFoundException("Not authenticated.");
        }

        String email = authentication.getName();

        Deck deck = deckRepository.findByIdAndUserEmail(deckId, email)
                .orElseThrow(() -> new ResourceNotFoundException("Deck not found."));

        Flashcard flashcard = new Flashcard();
        flashcard.setQuestion(flashcardRequest.getQuestion());
        flashcard.setDeck(deck);

        if (flashcardRequest.getOptions() !=  null) {
            List<FlashcardOption> linkedOptions = flashcardRequest.getOptions().stream()
                    .map(option -> {
                        option.setFlashcard(flashcard);
                        return option;
                    }).toList();

            flashcard.setOptions(linkedOptions);
        }

        return flashcardRepository.save(flashcard);
    }

    public Optional<Flashcard> getFlashcardById(Long id) {
        return flashcardRepository.findById(id);
    }

    public void deleteFlashcardById(Long id) {
        flashcardRepository.deleteById(id);
    }
}
