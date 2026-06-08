package com.example.steggo.controller;

import com.example.steggo.model.Deck;
import com.example.steggo.service.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public List<Deck> getAllDecks(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return deckService.getDecksByUsername(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> getDeckById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
         return deckService.getDeckByIdAndUsername(id, userDetails.getUsername())
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Deck createDeck(@RequestBody Deck deck, @AuthenticationPrincipal UserDetails userDetails) {
        return deckService.createDeckForUser(deck, userDetails.getUsername());
    }
}
