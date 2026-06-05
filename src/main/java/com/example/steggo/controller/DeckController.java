package com.example.steggo.controller;

import com.example.steggo.model.Deck;
import com.example.steggo.service.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
@CrossOrigin(origins = "*")
public class DeckController {
    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping
    public List<Deck> getAllCollection() {
        return deckService.getAllDecks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> getDeckById(@PathVariable Long id) {
         return deckService.getDeckById(id)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Deck createDeck(@RequestBody Deck deck) {
        return deckService.createDeck(deck);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable Long id) {
        deckService.deleteDeck(id);
        return ResponseEntity.noContent().build();
    }
}
