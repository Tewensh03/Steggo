package com.steggo.controller;

import com.steggo.dto.CreateDeckDto;
import com.steggo.dto.UpdateDeckDto;
import com.steggo.model.Deck;
import com.steggo.service.DeckService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/decks")
@CrossOrigin(origins = "*")
public class DeckController {
    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping
    public ResponseEntity<?> getAllDecks(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = userDetails.getUsername();
        return ResponseEntity.ok(deckService.getDecksByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> getDeckById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
         return deckService.getDeckByIdAndEmail(id, userDetails.getUsername())
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Deck> createDeck(
            @Valid @RequestBody CreateDeckDto createRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(deckService.createDeckForUser(createRequest, userDetails.getUsername()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Deck> updateDeck(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDeckDto updateRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return deckService.updateDeckForUser(id, updateRequest, userDetails.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
