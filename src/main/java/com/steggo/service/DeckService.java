package com.steggo.service;

import com.steggo.dto.CreateDeckDto;
import com.steggo.dto.UpdateDeckDto;
import com.steggo.model.Deck;
import com.steggo.model.User;
import com.steggo.repository.DeckRepository;
import com.steggo.repository.UserRepository;
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
    public Deck createDeckForUser(CreateDeckDto createRequest, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));

        Deck deck = new Deck();

        deck.setTitle(createRequest.getTitle());

        deck.setUser(user);
        return deckRepository.save(deck);
    }

    @Transactional
    public Optional<Deck> updateDeckForUser(Long id, UpdateDeckDto updateRequest, String email) {
        return deckRepository.findByIdAndUserEmail(id, email)
                .map(deck -> {
                    if (updateRequest.getTitle() != null) {
                        deck.setTitle(updateRequest.getTitle());
                    }

                    if (updateRequest.getDescription() != null) {
                        deck.setDescription(updateRequest.getDescription());
                    }

                    return deckRepository.save(deck);
                });
    }
}
