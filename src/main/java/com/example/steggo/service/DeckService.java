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

    public List<Deck> getDecksByEmail(String email) {
        return deckRepository.findByUserEmail(email);
    }

    public Optional<Deck> getDeckByIdAndEmail(Long id, String email) {
        return deckRepository.findByIdAndUserEmail(id, email);
    }

    @Transactional
    public Deck createDeckForUser(Deck deck, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        deck.setUser(user);
        return deckRepository.save(deck);
    }
}
