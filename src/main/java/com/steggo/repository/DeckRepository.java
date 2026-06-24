package com.steggo.repository;

import com.steggo.model.Deck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    List<Deck> findByUserEmail(String email);
    Optional<Deck> findByIdAndUserEmail(Long id, String email);
}
