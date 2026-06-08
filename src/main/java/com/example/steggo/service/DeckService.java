package com.example.steggo.service;

import com.example.steggo.model.Deck;
import com.example.steggo.model.User;
import com.example.steggo.repository.DeckRepository;
import com.example.steggo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeckService {
    private final DeckRepository deckRepository;
    private final UserRepository userRepository;

    public DeckService(DeckRepository deckRepository, UserRepository userRepository) {
        this.deckRepository = deckRepository;
        this.userRepository = userRepository;
    }

    public List<Deck> getDecksByUsername(String username) {
        return deckRepository.findByUserUsername(username);
    }

    public Optional<Deck> getDeckByIdAndUsername(Long id, String username) {
        return deckRepository.findByIdAndUserUsername(id, username);
    }

    @Transactional
    public Deck createDeckForUser(Deck deck, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found."));

        deck.setUser(user);
        return deckRepository.save(deck);
    }
}
