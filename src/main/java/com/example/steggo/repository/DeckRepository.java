package com.example.steggo.repository;

import com.example.steggo.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    List<Deck> findByUserUsername(String username);
    Optional<Deck> findByIdAndUserUsername(Long id, String username);
}
