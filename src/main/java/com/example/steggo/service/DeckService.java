package com.example.steggo.service;

import com.example.steggo.model.Deck;
import com.example.steggo.repository.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckService {
    private final DeckRepository deckRepository;

    public DeckService(DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    public Optional<Deck> getDeckById(Long id) {
        return deckRepository.findById(id);
    }

    public Deck createDeck(Deck deck) {
        return deckRepository.save(deck);
    }

    public void deleteDeck(Long id) {
        deckRepository.deleteById(id);
    }
}
